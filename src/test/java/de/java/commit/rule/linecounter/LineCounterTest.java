package de.java.commit.rule.linecounter;

import de.java.commit.rule.CommitRule;
import de.java.commit.rule.linecounter.exceptions.ContentLineLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.ContentLineNoContentException;
import de.java.commit.rule.linecounter.exceptions.ContentLineToLargeException;
import de.java.commit.rule.linecounter.exceptions.ContentLineZeroException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineNoContentException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineToLargeException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineZeroException;
import de.java.commit.rule.linecounter.exceptions.MaxLinesIsZeroException;
import de.java.commit.rule.linecounter.exceptions.MaxLinesLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.MessageGreaterThanMaxLinesException;
import de.java.commit.rule.linecounter.exceptions.MessageSmallerThanMinLines;
import de.java.commit.rule.linecounter.exceptions.MinLinesGreaterThanMaxLinesException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LineCounterTest {

    private List<String> message;

    /**
     * Sets up message.
     */
    @Before
    public void setupMessage() {
        this.message = new ArrayList<>();
        this.message.add("[CMC-7] This is an header line");
        this.message.add("");
        this.message.add("This is a content line");
    }

    @Test(expected = MaxLinesIsZeroException.class)
    public void run_maxLinesZero_throwsException() {
        CommitRule lineCounter = new LineCounter(0, 3, 32, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test
    public void run_maxLinesGreaterThanZero_doesNotThrowException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = MaxLinesLessThanZeroException.class)
    public void run_maxLinesLessThanZero_throwsException() {
        CommitRule lineCounter = new LineCounter(-11, 3, 32, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = MinLinesGreaterThanMaxLinesException.class)
    public void run_minLinesGreaterThanMaxLines_throwsException() {
        CommitRule lineCounter = new LineCounter(2, 3, 32, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = HeaderLineZeroException.class)
    public void run_headerLineLengthIsZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 0, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = HeaderLineLessThanZeroException.class)
    public void run_headerLineLengthIsLessThanZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, -12, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = ContentLineZeroException.class)
    public void run_contentLineIsZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 0, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = ContentLineLessThanZeroException.class)
    public void run_contentLineIsLessThanZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, -12, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = MessageGreaterThanMaxLinesException.class)
    public void run_moreLinesThanAllowed_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 64, 0, 2);
        this.message.add("Extra stuff");
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = MessageSmallerThanMinLines.class)
    public void run_lessLinesThanRequired_throwsException() {
        CommitRule lineCounter = new LineCounter(6, 4, 32, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = HeaderLineToLargeException.class)
    public void run_headerLineToLargeException_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 12, 64, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = HeaderLineNoContentException.class)
    public void run_headerLineEmpty_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 12, 64, 0, 2);
        this.message.set(0, "");
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = ContentLineToLargeException.class)
    public void run_contentLineToLarge_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 5, 0, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test
    public void run_contentLineIndexIsEqualToHeaderLineIndex_goesTrough() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 64, 0, 0);
        lineCounter.setMessage(this.message);
        lineCounter.run();

        lineCounter = new LineCounter(3, 1, 32, 64, 2, 2);
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

    @Test(expected = ContentLineNoContentException.class)
    public void run_contentLineEmpty_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 64, 0, 2);
        this.message.set(2, "");
        lineCounter.setMessage(this.message);
        lineCounter.run();
    }

}
