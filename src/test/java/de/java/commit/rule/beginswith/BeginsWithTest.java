package de.java.commit.rule.beginswith;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class BeginsWithTest {

    @Test
    public void run_patternMatches_noExceptionThrown() {
        BeginsWith beginsWith = new BeginsWith("^\\[[A-Z]+-[0-9]+\\]");
        List<String> message = new ArrayList<>();
        message.add("[CMS-123] Hello World");

        beginsWith.setMessage(message);
        boolean exceptionRaised = false;
        try {
            beginsWith.run();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionRaised = true;
        }
        assertEquals(false, exceptionRaised);
    }

    @Test
    public void run_patternMatches_exceptionThrown() {
        BeginsWith beginsWith = new BeginsWith("^\\[[A-Z]+-[0-9]+\\]");
        List<String> message = new ArrayList<>();
        message.add("CMS-123 Hello World");

        beginsWith.setMessage(message);
        boolean exceptionRaised = false;
        try {
            beginsWith.run();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionRaised = true;
        }
        assertEquals(true, exceptionRaised);
    }

}
