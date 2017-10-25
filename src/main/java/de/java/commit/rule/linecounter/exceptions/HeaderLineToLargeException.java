package de.java.commit.rule.linecounter.exceptions;

public class HeaderLineToLargeException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param allowedLength Allowed characters
     * @param typedLength Characters typed
     */
    public HeaderLineToLargeException(int allowedLength, int typedLength) {
        super("Header line is to large.\nAllowed are: " + allowedLength + "characters, you wrote "
            + typedLength + " characters");
    }
}
