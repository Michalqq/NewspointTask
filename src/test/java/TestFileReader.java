import org.junit.Test;

import java.io.FileNotFoundException;

public class TestFileReader {

    @Test(expected = FileNotFoundException.class)
    public void fileNotFound() throws FileNotFoundException {
       new FileReader().readFile("....");
    }
}

