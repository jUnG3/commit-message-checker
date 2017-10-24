package de.java.commit.rule.linecounter.exceptions;

public class ContentLineLessThanZeroException extends RuntimeException {

    public ContentLineLessThanZeroException() {
        super("Property lineCounter.contentLineLength can not be less then zero");
    }
}
