import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {
    public static ArrayList<String> getFile(String filepath) {
        try {
            URL url = FileReader.class.getResource(filepath);
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> allWords = new ArrayList<String>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitString = data.split(" ");

                allWords.addAll(Arrays.asList(splitString));

            }

            myReader.close();
            return allWords;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
}
