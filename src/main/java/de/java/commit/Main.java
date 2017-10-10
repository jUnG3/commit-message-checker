package de.java.commit;

import de.java.commit.application.Application;
import de.java.commit.configration.Configuration;
import de.java.commit.rule.CommitRule;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
    @PropertySource("classpath:default.properties"),
    //@PropertySource("file:${APP_PROPERTIES}")
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
                .filter(line -> !line.substring(0, 1).equals("#"))
                .collect(Collectors.toList());

            //Instance application context
            ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

            //Filter commit rule beans from context
            Map<String, CommitRule> beans = ctx
                .getBeansWithAnnotation(de.java.commit.rule.annotation.Rule.class)
                .entrySet()
                .stream()
                .filter(bean -> bean.getValue() instanceof CommitRule)
                .collect(Collectors.toMap(Map.Entry::getKey, bean -> (CommitRule) bean.getValue()));

            //Instance the application
            Runnable application = new Application(fileContent, beans,
                ctx.getBean(Configuration.class));

            // Run Forrest run
            application.run();

            System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
