package de.java.commit.rule.linecounter;

import de.java.commit.rule.CommitRule;
import de.java.commit.rule.linecounter.exceptions.ContentLineLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.ContentLineZeroException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineZeroException;
import de.java.commit.rule.linecounter.exceptions.MaxLinesIsZeroException;
import de.java.commit.rule.linecounter.exceptions.MaxLinesLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.MinLinesGreaterThanMaxLinesException;
import java.util.ArrayList;
import org.junit.Test;

public class LineCounterTest {

    @Test(expected = MaxLinesIsZeroException.class)
    public void run_maxLinesZero_throwsException() {
        CommitRule lineCounter = new LineCounter(0, 3, 32, 64, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test
    public void run_maxLinesGreaterThanZero_doesNotThrowException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 64, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test(expected = MaxLinesLessThanZeroException.class)
    public void run_maxLinesLessThanZero_throwsException() {
        CommitRule lineCounter = new LineCounter(-11, 3, 32, 64, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test(expected = MinLinesGreaterThanMaxLinesException.class)
    public void run_minLinesGreaterThanMaxLines_throwsException() {
        CommitRule lineCounter = new LineCounter(2, 3, 32, 64, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test(expected = HeaderLineZeroException.class)
    public void run_headerLineLengthIsZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 0, 64, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test(expected = HeaderLineLessThanZeroException.class)
    public void run_headerLineLengthIsLessThanZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, -12, 64, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test(expected = ContentLineZeroException.class)
    public void run_contentLineIsZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, 0, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

    @Test(expected = ContentLineLessThanZeroException.class)
    public void run_contentLineIsLessThanZero_throwsException() {
        CommitRule lineCounter = new LineCounter(3, 1, 32, -12, 0, 2);
        lineCounter.setMessage(new ArrayList<>());
        lineCounter.run();
    }

}
