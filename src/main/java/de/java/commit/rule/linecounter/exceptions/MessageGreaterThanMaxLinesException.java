package de.java.commit.rule.linecounter.exceptions;

public class MessageGreaterThanMaxLinesException extends RuntimeException {

    public MessageGreaterThanMaxLinesException(int linesWrote, int linesAllowed) {
        super("Message has more lines than allowed.\nYou Wrote " + linesWrote
            + ", allowed are maximum " + linesAllowed);
    }
}
