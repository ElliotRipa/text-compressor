import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An external (on-disk) read-only string.
 * Only supports ASCII text.
 */
public class DiskString implements AutoCloseable, Comparable<DiskString>, Iterable<Character> {
    // The underlying buffer. We make sure that buffer.position() == 0
    // and buffer.limit() == buffer.capacity() always.
    // In other words, we always work with the whole of the buffer.
    private ByteBuffer buffer;
    // The underlying file, used to implement AutoCloseable.
    // Set to null in slices, so that closing a slice doesn't
    // close the parent array.
    private FileChannel channel;

    /**
     * Create a DiskString given a filename.
     *
     * @param path The file to open.
     */
    public DiskString(String path) throws IOException {
        this(Paths.get(path));
    }

    /**
     * Create a DiskString given a Path.
     *
     * @param path The file to open.
     */
    public DiskString(Path path) throws IOException {
        channel = FileChannel.open(path, StandardOpenOption.READ);
        buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

        assert buffer.isDirect() : "Buffer is not mapped direct";
        assert buffer.position() == 0 && buffer.limit() == channel.size() &&
            buffer.capacity() == channel.size() :
            "Buffer has unexpected position/limit/capacity";
    }

    // Internal constructor for creating a substring.
    private DiskString(ByteBuffer buffer) {
        this.channel = null;
        this.buffer = buffer;
    }

    @Override
    public void close() throws IOException {
        if (channel != null) channel.close();
    }

    /**
     * Returns the length of the string.
     */
    public int length() {
        return buffer.limit();
    }

    /**
     * Returns the character at a given index in the string.
     *
     * @param pos The index to be read.
     * @return The character stored at that index.
     */
    public char charAt(int pos) {
        if (pos < 0 || pos >= length())
            throw new StringIndexOutOfBoundsException();
        return (char)buffer.get(pos);
    }

    /**
     * Returns a substring, without reading it into memory.
     *
     * @param start The start position of the substring.
     * @param end The end position of the substring, plus one.
     * @return The substring {@code start..end-1}.
     */
    public DiskString substring(int start, int end) {
        if (start < 0) start = 0;
        if (end > length()) end = length();
        if (start > end) start = end;

        // Create a (shallow) copy first, so that we can safely
        // change position and limit.
        ByteBuffer slicedBuffer = buffer.slice();
        slicedBuffer.position(start);
        slicedBuffer.limit(end);
        // slice() returns the slice [position():limit()], but
        // renumbers the indexes so that it starts from index 0
        slicedBuffer = slicedBuffer.slice();
        return new DiskString(slicedBuffer);
    }

    /**
     * Returns a suffix of this string, without reading it into
     * memory.
     *
     * @param start The start position of the suffix
     * @return The suffix starting at position {@code start}.
     */
    public DiskString suffix(int start) {
        return substring(start, length());
    }


    /**
     * Returns a prefix of this string, without reading it into
     * memory.
     *
     * @param size The length of the prefix.
     * @return The first {@size} bytes of the string.
     */
    public DiskString prefix(int size) {
        size = Math.min(size, length());
        return substring(0, size);
    }

    /**
     * Reads the string into memory, as an array of bytes.
     *
     * @return The string, as a {@code byte[]}.
     */
    public byte[] getBytes() {
        byte[] result = new byte[length()];
        buffer.get(result);
        return result;
    }

    /**
     * Reads the string into memory.
     *
     * @return The string, as a {@code String}.
     */
    public String getString() {
        String invalid = Character.toString((char)65533);
        return new String(getBytes(), StandardCharsets.UTF_8).replace(invalid, "");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DiskString)) return false;
        DiskString other = (DiskString)obj;

        return buffer.equals(other.buffer);
    }

    @Override
    public int hashCode() {
        return buffer.hashCode();
    }

    @Override
    public int compareTo(DiskString other) {
        // Note: this is not the same as buffer.compareTo(other.buffer),
        // because Java bytes are signed!

        int len = Math.min(length(), other.length());
        for (int i = 0; i < len; i++) {
            int x = Byte.toUnsignedInt(buffer.get(i));
            int y = Byte.toUnsignedInt(other.buffer.get(i));
            int cmp = Integer.compare(x, y);
            if (cmp != 0) return cmp;
        }

        return Integer.compare(length(), other.length());
    }

    @Override
    public String toString() {
        if (length() <= 23)
            return getString();
        else
            return prefix(10).getString() + "..." + suffix(length()-10).getString();
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            int pos = 0;
            public boolean hasNext() {
                return pos < length();
            }
            public Character next() {
                if (pos >= length()) throw new NoSuchElementException();
                return charAt(pos++);
            }
        };
    }

}

