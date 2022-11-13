import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

         ArrayList<String> file = FileReader.getFile("C:\\Users\\ellio\\IdeaProjects\\text-compressor\\src\\filename.txt");

         for(String word : file) {
             System.out.println(word);
         }

    }
}
