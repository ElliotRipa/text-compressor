import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;

public class Util {

    ///////////////////////////////////////////////////////////////////////////
    // Characters

    /**
     * Return true if the given character is alphanumeric.
     *
     * @param c The character to test.
     * @return {@code true} if {@code c} is alphanumeric.
     */
    public static boolean isAlphanumeric(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Lists and arrays

    // Swap two positions in a list
    public static <E> void swap(List<E> array, int i, int j) {
        E tmp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, tmp);
    }

    // Fisher-Yates shuffle algorithm, but decides randomly when to swap elements
    public static void shuffleArray(List<Integer> array, float randomness) {
        assert 0.0 <= randomness && randomness <= 1.0;
        int size = array.size();
        for (int index=0; index < size; index++) {
            if (Math.random() < randomness) {
                int other = randRange(index, size);
                swap(array, index, other);
            }
        }
    }

    public static int randRange(int lo, int hi) {
        return (int) (lo + Math.random() * (hi - lo));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Comparing and sorting

    public static interface SortingAlgorithm<E> {
        public void sort(List<E> array);
    } 

    public static class CountingComparator<E> implements Comparator<E> {
        Comparator<E> cmp;
        long counter;
    
        CountingComparator(Comparator<E> cmp) {
            this.cmp = cmp;
            reset();
        }
    
        @Override
        public int compare(E o1, E o2) {
            counter++;
            return cmp.compare(o1, o2);
        }
    
        public void reset() {
            counter = 0;
        }
    
        public long comparisons() {
            return this.counter;
        }
    }

    public static <E> boolean isSorted(List<E> array, Comparator<? super E> cmp) {
        boolean sorted = true;

        // First test the list using .get(i)
        E prev = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            E next = array.get(i);
            if (cmp.compare(prev, next) > 0) {
                System.err.format("ERROR: not sorted (at position %d): \"%s\" >= \"%s\"\n", i, prev, next);
                sorted = false;
                break;
            }
            prev = next;
        }
        
/*
        // The test the list as an iterator
        prev = null;
        for (E next : array) {
            if (prev != null && cmp.compare(prev, next) > 0) {
                System.err.format("ERROR: not sorted (testing iterator): \"%s\" >= \"%s\"\n", prev, next);
                sorted = false;
                break;
            }
            prev = next;
        }
*/
        return sorted;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Timing

    public static interface Thunk<E extends Throwable> { void run() throws E; }
    public static <E extends Throwable> void time(String desc, Thunk<E> task) throws E {
        System.out.print(desc);
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        double totalTime = (double)(endTime - startTime) / 1e9;
        System.out.printf(" %5.2f s\n", totalTime);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Output redirection

    public static <E extends Throwable> void withOutput(OutputStream out, Thunk<E> task) throws E {
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        try {
            System.setOut(new PrintStream(out));
            System.setErr(new PrintStream(out));
            task.run();
        } finally {
            System.setOut(oldOut);
            System.setErr(oldErr);
        }
    }
}

