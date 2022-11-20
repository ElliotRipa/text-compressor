import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public class DiskIntArray extends AbstractList<Integer> implements AutoCloseable, RandomAccess, Iterable<Integer> {
    FileChannel channel;
    IntBuffer array;

    DiskIntArray(String path) throws IOException {
        this(Paths.get(path));
    }

    DiskIntArray(Path path) throws IOException {
        channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
        array = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size()).asIntBuffer();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return array.limit();
    }

    @Override
    public Integer get(int i) {
        return array.get(i);
    }

    @Override
    public Integer set(int i, Integer val) {
        Integer old = array.get(i);
        array.put(i, val);
        return old;
    }

    @Override
    public String toString() {
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (Integer val : this) {
            if (i++ > 0) s.append(", ");
            if (i > 20) {
                s.append("... {N = " + size() + "}");
                break;
            }
            s.append(val);
        }
        return s.toString();
    }

    @Override
    public void close() throws IOException {
        channel.close();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int pos = 0;
            public boolean hasNext() {
                return pos < size();
            }
            public Integer next() {
                if (pos >= size()) throw new NoSuchElementException();
                return get(pos++);
            }
        };
    }

    public static class Builder implements AutoCloseable {
        DataOutputStream writer;
        int size;

        Builder(String path) throws IOException {
            this(Paths.get(path));
        }

        Builder(Path path) throws IOException {
            writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path.toFile())));
            size = 0;
        }

        public void append(int val) throws IOException {
            writer.writeInt(val);
            size++;
        }

        public int size() {
            return size;
        }

        @Override
        public void close() throws IOException {
            writer.close();
        }
    }
}

