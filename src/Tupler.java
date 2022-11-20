import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Set;

public class Tupler {

    public ArrayList<Pair<String, Integer>> getSorted(Hashtable<String, Integer> dict) {
        ArrayList<Pair<String, Integer>> array = makeDictTuple(dict);

        quickSort(array, 0, array.size());

        return array;

    }


    Comparator<? super Integer> comparator;


    private ArrayList<Pair<String, Integer>> makeDictTuple(Hashtable<String, Integer> dict) {
        ArrayList<Pair<String, Integer>> result = new ArrayList<>();
        Set<String> setOfKeys = dict.keySet();
        for(String key : setOfKeys) {
            Pair<String, Integer> word = new Pair<>(key, dict.get(key));
            result.add(word);
        }
        return result;
    }

    private void quickSort(ArrayList<Pair<String, Integer>> array, int lo, int hi) {

        Util.CountingComparator<Integer> cmp = new Util.CountingComparator<Integer>(Comparator.naturalOrder());
        this.comparator = cmp;

        // Base case
        int size = hi - lo;
        if (size <= 1)
            return;
        int partitionIndex = partition(array, lo, hi);
        quickSort(array, lo, partitionIndex);
        quickSort(array, partitionIndex+1, hi);
        return;
    }

    private int partition(ArrayList<Pair<String, Integer>> array, int lo, int hi) {
        // pivotIndex is the index of the element that should be used
        // as the pivot.
        int pivotIndex = lo;
        Util.swap(array, lo, pivotIndex);
        pivotIndex = lo;
        Pair<String, Integer> pivot = array.get(pivotIndex);
        lo++;
        hi--;

        try {
            while (lo <= hi) {      //TODO: Not get stuck in infinite loop.

                if ((comparator.compare(array.get(lo).getValue(), pivot.getValue())) != -1) {
                    lo++;

                } else if ((comparator.compare(array.get(hi).getValue(), pivot.getValue())) == -1) {
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
