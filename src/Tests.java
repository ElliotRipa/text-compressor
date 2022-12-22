import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tests {

    public static void main(String[] args) throws IOException {

        ArrayList<String> words = FileReader.getFile("testfile.txt");

        for (String line: words) {
            System.out.println(line);
        }

    }

}
