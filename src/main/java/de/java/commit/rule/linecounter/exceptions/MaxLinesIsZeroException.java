package de.java.commit.rule.linecounter.exceptions;

public class MaxLinesIsZeroException extends RuntimeException {

    /**
     * Default constructor.
     */
    public MaxLinesIsZeroException() {
        super("Property lineCounter.maxLines can not be zero");
    }
}
