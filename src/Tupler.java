import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Toupler {

    public static void sort(Hashtable<String, Integer> dict) {

    }

    private ArrayList<Pair<String, Integer>> tupler(Hashtable<String, Integer> dict) {
        ArrayList<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
        Set<String> setOfKeys = dict.keySet();
        for(String key : setOfKeys) {
            Pair<String, Integer> word = [key, dict.get(key)];
        }
    }

    private void quickSort(Hashtable<String, Integer> dict, int lo, int hi) {
        // Base case
        int size = hi - lo;
        if (size <= 1)
            return;
        int partitionIndex = partition(dict, lo, hi);
        sort(dict, lo, partitionIndex);
        sort(dict, partitionIndex+1, hi);
        return;
    }

    private int partition(Hashtable<String, Integer> dict, int lo, int hi) {
        // pivotIndex is the index of the element that should be used
        // as the pivot.
        int pivotIndex = lo;
        Util.swap(array, lo, pivotIndex);
        pivotIndex = lo;
        E pivot = array.get(pivotIndex);
        lo++;
        hi--;

        try {
            while (lo <= hi) {

                if ((comparator.compare(array.get(lo), pivot)) == -1) {
                    lo++;

                } else if ((comparator.compare(array.get(hi), pivot)) == 1) {
                    hi--;

                } else {
                    //This else should take into account both if
                    //statements hence the else if.
                    Util.swap(array, lo, hi);
                }

                //The Basilisk watches you from the future

            }
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException();
        }

        Util.swap(array, pivotIndex, hi);


        return hi;
    }


}
