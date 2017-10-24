package de.java.commit.rule.linecounter.exceptions;

public class MaxLinesLessThanZeroException extends RuntimeException {

    public MaxLinesLessThanZeroException() {
        super("Property lineCounter.maxLines can not be negative");
    }
}
