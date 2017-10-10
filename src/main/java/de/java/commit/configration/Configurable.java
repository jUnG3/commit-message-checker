package de.java.commit.configration;

import java.util.List;

public interface Configurable {

    /**
     * Returns all active plugins.
     */
    List<String> getActivePlugins();
}
