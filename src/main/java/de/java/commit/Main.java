package de.java.commit;

import de.java.commit.application.Application;
import de.java.commit.configration.Configurable;
import de.java.commit.configration.Configuration;
import de.java.commit.rule.CommitRule;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
    @PropertySource("classpath:default.properties")
})
@ComponentScan
public class Main {

    /**
     * Main entry point.
     *
     * @param args Command line arguments <p>First argument is the commit file location</p>
     */
    public static void main(String[] args) {

        try {
            //Convert input commit file to list and filter all commented lines
            List<String> fileContent = Files.lines(Paths.get(args[0]))
                .filter(Main::filterCommentedLines)
                .collect(Collectors.toList());

            //Instance application context
            ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

            // Get configuration bean
            Configurable configuration = ctx.getBean(Configuration.class);

            // Get all active plugins from configuration
            List<String> activePlugins = configuration.getActivePlugins();

            //Filter commit rule beans from context
            List<CommitRule> beans = ctx
                .getBeansWithAnnotation(de.java.commit.rule.annotation.Rule.class)
                .entrySet()
                .stream()
                .filter(bean -> bean.getValue() instanceof CommitRule)
                .filter(bean -> activePlugins.contains(bean.getKey()))
                .map(bean -> (CommitRule) bean.getValue())
                .collect(Collectors.toList());

            //Instance the application
            Runnable application = new Application(fileContent, beans);

            // Run Forrest run
            application.run();

            System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns true if first line does not equals "#".
     *
     * @param line Line to be filtered
     * @return true if line is not commented
     */
    public static boolean filterCommentedLines(String line) {
        return !line.substring(0, 1).equals("#");
    }
}
