package de.java.commit.rule.linecounter.exceptions;

public class ContentLineToLargeException extends RuntimeException {

    public ContentLineToLargeException(int allowedLength, int typedLength) {
        super("Content line is to large.\nAllowed are: " + allowedLength + "characters, you wrote "
            + typedLength + " characters");
    }
}
