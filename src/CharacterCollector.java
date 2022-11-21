import java.io.IOException;

public class CharacterCollector {

    public static void getCharacters(String textFile, String outputFile) throws IOException {
        try (DiskString text = new DiskString(textFile)) {
            try (DiskIntArray.Builder output = new DiskIntArray.Builder(outputFile)) {

                CharList chars;

                chars = new CharList();

                System.out.println(chars.getNthBits(0));

                //for(int i = 0 ; i < text.length() ; i++)

            }
        }

    }

}
