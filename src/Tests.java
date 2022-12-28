import java.io.IOException;
import java.util.ArrayList;

public class Tests {

    public static void main(String[] args) throws IOException {

        ArrayList<String> words = FileReader.getFile("testfile.txt");

        for (String line: words) {
            System.out.println(line);
        }

    }

}
