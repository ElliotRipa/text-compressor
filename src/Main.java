import java.util.ArrayList;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {

        Hashtable<String, Integer> dict = new Hashtable<>();            //Creates a prevalence dictionary.

         ArrayList<String> file = FileReader.getFile("C:\\Users\\ellio\\IdeaProjects\\text-compressor\\src\\testfile.txt");     //Reads a given file.

        assert file != null;

        for(String word : file) {
             dict.merge(word, 1, Integer::sum);     //If a word does not already exist, sets its value to 1, increments otherwise.
         }

        Tupler tplr = new Tupler();
        ArrayList<Pair<String, Integer>> list = tplr.getSorted(dict);

        CharList al = new CharList();

        al.bitWiseOr((char) 2);

        al.bitWiseOr('?');

        System.out.println(al.size());

        System.out.println("Haiii omg haiiii :3 uwuuw");                //Just so that I can set a breakpoint. I'm sorry.

    }

    private void generatePreamble(String filepath, Hashtable<String, Integer> dict) {

        ArrayList<String> file = FileReader.getFile(filepath);

    }

    private void sortDictionary(Hashtable<String, Integer> dict) {

    }

}
