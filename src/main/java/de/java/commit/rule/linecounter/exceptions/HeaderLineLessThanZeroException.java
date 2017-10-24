package de.java.commit.rule.linecounter.exceptions;

public class HeaderLineLessThanZeroException extends RuntimeException {

    public HeaderLineLessThanZeroException() {
        super("Property lineCounter.headerLineLength can not be less than zero");
    }
}
