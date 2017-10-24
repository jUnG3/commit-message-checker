package de.java.commit.rule.linecounter.exceptions;

public class MinLinesGreaterThanMaxLinesException extends RuntimeException {

    public MinLinesGreaterThanMaxLinesException() {
        super("Property lineCounter.maxLines must be greater or equal to lineCounter.minLines");
    }
}
