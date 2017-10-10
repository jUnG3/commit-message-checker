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
    private Map<String, CommitRule> commitRulesMap;

    /**
     * Configuration object.
     */
    private List<String> activePlugins;

    /**
     * Application constructor.
     *
     * @param fileContent File content
     * @param commitRulesMap Commit rules map
     * @param configuration Configuration object
     */
    public Application(
        List<String> fileContent,
        Map<String, CommitRule> commitRulesMap,
        Configurable configuration
    ) {
        this.fileContent = fileContent;
        this.commitRulesMap = commitRulesMap;
        this.activePlugins = configuration.getActivePlugins();
    }

    /**
     * Runs the application.
     */
    public void run() {
        this.commitRulesMap.entrySet().stream().filter(this::filterCommitRules)
            .forEach(this::setFileContentAndRunPlugins);
    }

    /**
     * Returns true if commit rule key is in the active plugins list.
     */
    private boolean filterCommitRules(Entry<String, CommitRule> ruleEntry) {
        return this.activePlugins.contains(ruleEntry.getKey());
    }

    /**
     * Sets file content to plugin and runs the plugin.
     *
     * @param ruleEntry Plugin object
     */
    private void setFileContentAndRunPlugins(Entry<String, CommitRule> ruleEntry) {
        ruleEntry.getValue().setMessage(this.fileContent);
        ruleEntry.getValue().run();
    }
}
