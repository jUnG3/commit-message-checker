package de.java.commit.rule.linecounter.exceptions;

public class ContentLineNoContentException extends RuntimeException {

    /**
     * Constructor.
     */
    public ContentLineNoContentException() {
        super("Content line is empty");
    }
}
