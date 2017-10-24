package de.java.commit.rule.linecounter.exceptions;

public class HeaderLineZeroException extends RuntimeException {

    public HeaderLineZeroException() {
        super("Property lineCounter.headerLineLength can not be zero");
    }
}
