import java.util.ArrayList;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {

        Hashtable<String, Integer> dict = new Hashtable<>();            //Creates a prevalence dictionary.

         ArrayList<String> file = FileReader.getFile("C:\\Users\\ellio\\IdeaProjects\\text-compressor\\src\\filename.txt");     //Reads a given file.

        assert file != null;

        for(String word : file) {
             dict.merge(word, 1, Integer::sum);     //If a word does not already exist, sets its value to 1, increments otherwise.
         }

        Tupler tplr = new Tupler();
        tplr.getSorted(dict);

        System.out.println("Haiii omg haiiii :3 uwuuw");

    }

    private void generatePreamble(String filepath, Hashtable<String, Integer> dict) {

        ArrayList<String> file = FileReader.getFile(filepath);

    }

    private void sortDictionary(Hashtable<String, Integer> dict) {

    }

}
