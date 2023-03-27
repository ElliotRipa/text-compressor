import java.util.*;

public class Main {
    public static void main(String[] args) {

        Hashtable<String, Integer> dict = new Hashtable<>();            //Creates a prevalence dictionary.

        System.out.println("What's the filepath? ");
        Scanner scanner = new Scanner(System.in);
        String filepath;
        if(!scanner.nextLine().equals("")) {
            filepath = scanner.nextLine();
        } else {
            filepath = "testfile.txt";                                 //I would put this in a separate directory but Java URLs are hell.
        }
        ArrayList<String> wordList = FileReader.getFile(filepath);      //Reads a given file.

        assert wordList != null;

        for(String word : wordList) {
             dict.merge(word, 1, Integer::sum);                         //If a word does not already exist, sets its value to 1, increments otherwise.
         }

        CharList cl = getCharList(wordList);                            //Generates a CharList for the given wordList.

        System.out.println(cl.size());

        Tupler tplr = new Tupler();
        ArrayList<Pair<String, Integer>> list = tplr.getSorted(dict);


        System.out.println("Haiii omg haiiii :3 uwuuw");                //Just so that I can set a breakpoint. I'm sorry.

        PriorityQueue<PrevalenceEntry> prevalenceEntries = makePQ(dict);


        ArrayList<PrevalenceEntry> pe = new ArrayList<>();

       while(!prevalenceEntries.isEmpty()) {
           pe.add((prevalenceEntries.remove()));
       }

    }

    private void generatePreamble(String filepath, Hashtable<String, Integer> dict) {

        ArrayList<String> file = FileReader.getFile(filepath);

    }


    private static CharList getCharList(ArrayList<String> words) {

        CharList output = new CharList();

        for(String word : words) {
            char[] chars = word.toCharArray();

            for(char c : chars) {
                output.bitWiseOr(c);
            }

        }
    return output;
    }


    private static PriorityQueue<PrevalenceEntry> makePQ(Hashtable<String, Integer> prevMap) {

        PriorityQueue<PrevalenceEntry> PQ = new PriorityQueue<>(Collections.reverseOrder());

        Set<String> keySet = prevMap.keySet();

        for(String key : keySet) {
            PrevalenceEntry PE = new PrevalenceEntry(key, prevMap.get(key));
            PQ.add(PE);
        }
        return PQ;
    }


    private void sortDictionary(Hashtable<String, Integer> dict) {

    }

}
