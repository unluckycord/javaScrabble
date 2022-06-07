import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assets {
    // this stores a text file to be loaded at the start
    public static ArrayList<String> logo = new ArrayList<String>();
    public static ArrayList<String> Ainames = new ArrayList<String>();
    public static ArrayList<String> dictionary = new ArrayList<String>();

    protected static void loadAssets() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Assets/logo.ans"))) {
            String line = br.readLine();
            while (line != null) {
                logo.add(line);
                line = br.readLine();
            }
        }
    }

    protected static void loadAinames() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Assets/Ainames.txt"))) {
            String line = br.readLine();
            while (line != null) {
                Ainames.add(line);
                line = br.readLine();
            }
        }
    }
}
