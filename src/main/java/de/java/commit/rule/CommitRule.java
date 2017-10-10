package de.java.commit.rule;

import java.util.List;

public interface CommitRule extends Runnable {

    public void setMessage(List<String> message);
}
