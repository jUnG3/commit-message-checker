package de.java.commit.rule.beginswith;

import de.java.commit.rule.CommitRule;
import de.java.commit.rule.annotation.Rule;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Rule
public class BeginsWith implements CommitRule {

    /**
     * Message file content.
     */
    private List<String> message;

    /**
     * Regular expression for validating the begin of the first line.
     */
    private String regex;

    /**
     * Begins with constructor.
     *
     * @param regex Regular expression string
     */
    public BeginsWith(@Value("${beginsWith.pattern}") String regex) {
        this.regex = regex;
    }

    @Override
    public void run() {
        Pattern pattern = Pattern.compile(this.regex);

        if (!pattern.matcher(this.message.get(0)).find()) {
            throw new RuntimeException(
                "The message " + this.message.get(0) + " does not match the given pattern "
                    + this.regex);
        }
    }

    @Override
    public void setMessage(List<String> message) {
        this.message = message;
    }
}
