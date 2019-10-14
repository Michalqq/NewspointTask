import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public Scanner readFile(String path) throws FileNotFoundException {
        return new Scanner(new File(path));
    }
}
