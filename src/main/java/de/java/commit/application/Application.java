package de.java.commit.application;

import de.java.commit.configration.Configurable;
import de.java.commit.rule.CommitRule;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Application implements Runnable {

    /**
     * Commit message file content.
     */
    private List<String> fileContent;

    /**
     * Commit rules map.
     */
    private List<CommitRule> commitRules;

    /**
     * Application constructor.
     *
     * @param fileContent File content
     * @param commitRules Commit rules map
     */
    public Application(
        List<String> fileContent,
        List<CommitRule> commitRules
    ) {
        this.fileContent = fileContent;
        this.commitRules = commitRules;
    }

    /**
     * Runs the application.
     */
    public void run() {
        this.commitRules.forEach(commitRule -> commitRule.setMessage(this.fileContent));
        this.commitRules.forEach(Runnable::run);
    }
}
