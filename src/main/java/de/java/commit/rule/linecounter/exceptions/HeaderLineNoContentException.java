package de.java.commit.rule.linecounter.exceptions;

public class HeaderLineNoContentException extends RuntimeException {

    /**
     * Constructor.
     */
    public HeaderLineNoContentException() {
        super("Header line is empty.");
    }
}
