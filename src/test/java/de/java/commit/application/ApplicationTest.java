package de.java.commit.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.java.commit.rule.CommitRule;
import de.java.commit.rule.beginswith.BeginsWith;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ApplicationTest {

    @Test
    public void run_setsMessageToCommitRule_expectsNoError() {
        List<String> message = Arrays.asList("Hello World commit message");
        BeginsWith mock = mock(BeginsWith.class);
        List<CommitRule> commitRules = Arrays.asList(mock, mock);
        Application application = new Application(message, commitRules);
        application.run();
        verify(mock, times(2)).setMessage(message);
    }
}
