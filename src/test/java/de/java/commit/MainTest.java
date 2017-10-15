package de.java.commit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {

    @Test
    public void filterCommentedLines_lineNotCommented_expectsTrue() {
        assertEquals(true, Main.filterCommentedLines("Line is not comment"));
    }

    @Test
    public void filterCommentedLines_lineCommented_expectsFalse() {
        assertEquals(false, Main.filterCommentedLines("#Line is commented"));
    }

}
