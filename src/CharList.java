public class CharList {


    private long[] longs = new long[4];


    public void bitWiseOr(char number) {
        if (number < 64) {
            longs[0] |= (1L << (number));
        } else if (number < 128) {
            longs[1] |= (1L << (number - 64));
        } else if (number < 196) {
            longs[2] |= (1L << (number - 128));
        } else if (number < 256) {
            longs[3] |= (1L << (number - 192));
        }
    }


    public void addChar(char c) {
        bitWiseOr(c);
    }

    public long getNthBits(int n) {
        if (n < 0 || n > 3) {
            throw new IndexOutOfBoundsException("Tried to read a long which does not exist.");
        }
        return longs[n];
    }


    //TODO: Better counter names.
    // And also maybe don't use nested loops? idk, might be fine.
    // Also, it currently doesn't seem to want to enter the second for-loop.
    public int size() {
        int counter = 0;
        for (int i = 0; i < 4; i++) {                  //For every list of chars i
            for (int j = 0; j < longs[i]; j<<=1) {               //For every bit k
                if ((longs[i]&j) == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
