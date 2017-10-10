package de.java.commit.configration;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration implements Configurable {

    /**
     * Comma separated plugin list.
     */
    private String plugins;

    public Configuration(@Value("${rules.active}") String plugins) {
        this.plugins = plugins;
    }

    @Override
    public List<String> getActivePlugins() {
        return Arrays.asList(this.plugins.split(","));
    }
}
