package de.java.commit;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final String commitMessageFilePath = "etc/COMMIT_MSG";

    private Path commitMessageFile;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        commitMessageFile = Paths.get(commitMessageFilePath);
    }

    @After
    public void cleanUp() throws Exception {
        System.setOut(null);
        Files.deleteIfExists(commitMessageFile);
    }

    @Test
    public void main_commitMessageRight_expectsSuccess() throws IOException {
        String[] args = {commitMessageFilePath};
        Files.write(
            commitMessageFile,
            Arrays.asList("[CMC-1] Some commit"),
            Charset.forName("UTF-8")
        );
        Main.main(args);
        assertEquals("Success\n", outContent.toString());
    }

    @Test
    public void filterCommentedLines_lineNotCommented_expectsTrue() {
        assertEquals(true, Main.filterCommentedLines("Line is not comment"));
    }

    @Test
    public void filterCommentedLines_lineCommented_expectsFalse() {
        assertEquals(false, Main.filterCommentedLines("#Line is commented"));
    }

}
