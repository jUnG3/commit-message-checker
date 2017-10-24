package de.java.commit.rule.linecounter.exceptions;

public class ContentLineZeroException extends RuntimeException {

    public ContentLineZeroException() {
        super("Property lineCounter.contentLineLength can not be zero");
    }
}
