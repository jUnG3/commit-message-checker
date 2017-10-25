package de.java.commit.rule.linecounter.exceptions;

public class MessageSmallerThanMinLines extends RuntimeException {

    /**
     * Constructor.
     *
     * @param linesWrote Lines wrote in message
     * @param linesAllowed Lines allowed to be written
     */
    public MessageSmallerThanMinLines(int linesWrote, int linesAllowed) {
        super("Message has less lines than allowed.\nYou wrote " + linesWrote
            + ", minimum required lines are "
            + linesAllowed);
    }
}
