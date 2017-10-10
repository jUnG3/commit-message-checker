package de.java.commit.configuration;

import static org.junit.Assert.assertEquals;

import de.java.commit.configration.Configuration;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ConfigurationTest {

    /**
     * Class under test.
     */
    private Configuration configuration;

    @Test
    public void getActivePlugins_onePlugin_expectedInList() {
        configuration = new Configuration("beginsWith");
        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("beginsWith");
        assertEquals(expectedResults, this.configuration.getActivePlugins());
    }

    @Test
    public void getActivePlugins_twoPlugin_expectedBothInList() {
        configuration = new Configuration("beginsWith,firstLine");
        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("beginsWith");
        expectedResults.add("firstLine");
        assertEquals(expectedResults, this.configuration.getActivePlugins());
    }

}
