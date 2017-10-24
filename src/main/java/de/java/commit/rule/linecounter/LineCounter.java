package de.java.commit.rule.linecounter;

import de.java.commit.rule.CommitRule;
import de.java.commit.rule.annotation.Rule;
import de.java.commit.rule.linecounter.exceptions.ContentLineLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.ContentLineZeroException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.HeaderLineZeroException;
import de.java.commit.rule.linecounter.exceptions.MaxLinesIsZeroException;
import de.java.commit.rule.linecounter.exceptions.MaxLinesLessThanZeroException;
import de.java.commit.rule.linecounter.exceptions.MessageGreaterThanMaxLinesException;
import de.java.commit.rule.linecounter.exceptions.MessageSmallerThanMinLines;
import de.java.commit.rule.linecounter.exceptions.MinLinesGreaterThanMaxLinesException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Rule
@Component
public class LineCounter implements CommitRule {

    /**
     * Maximum amount of lines.
     */
    private int maxLines;

    /**
     * Minimum amount of lines.
     */
    private int minLines;

    /**
     * Header line length.
     */
    private int headerLineLength;

    /**
     * Content line length.
     */
    private int contentLineLength;

    /**
     * Header line position. The first index begins with 0.
     */
    private int headerLineIndex;

    /**
     * Content line position.
     */
    private int contentLineIndex;

    /**
     * Commit message content.
     */
    private List<String> message;

    /**
     * Default constructor.
     *
     * @param maxLines Reads from lineCounter.maxLines default 3
     * @param minLines Reads from lineCounter.minLines default 1
     * @param headerLineLength Reads from lineCounter.headerLineLength default 32
     * @param contentLineLength Reads from lineCounter.contentLineLength default 64
     * @param headerLineIndex Reads from lineCounter.headerLineIndex default 0
     * @param contentLineIndex Read from lineCounter.contentLineIndex default 2
     */
    public LineCounter(
        @Value("${lineCounter.maxLines:3}") int maxLines,
        @Value("${lineCounter.minLines:1}") int minLines,
        @Value("${lineCounter.headerLineLength:32}") int headerLineLength,
        @Value("${lineCounter.contentLineLength:64}") int contentLineLength,
        @Value("${lineCounter.headerLineIndex:0}") int headerLineIndex,
        @Value("${lineCounter.contentLineIndex:2}") int contentLineIndex
    ) {
        this.maxLines = maxLines;
        this.minLines = minLines;
        this.headerLineLength = headerLineLength;
        this.contentLineLength = contentLineLength;
        this.headerLineIndex = headerLineIndex;
        this.contentLineIndex = contentLineIndex;
    }

    @Override
    public void setMessage(List<String> message) {
        this.message = message;
    }

    @Override
    public void run() {
        this.checkConfiguration();

        int messageSize = this.message.size();

        if (messageSize > this.maxLines) {
            throw new MessageGreaterThanMaxLinesException(messageSize, this.maxLines);
        }

        if (messageSize < this.minLines) {
            throw new MessageSmallerThanMinLines(messageSize, this.minLines);
        }
    }

    /**
     * Checks given configuration.
     */
    private void checkConfiguration() {
        if (this.maxLines == 0) {
            throw new MaxLinesIsZeroException();
        }

        if (this.maxLines < 0) {
            throw new MaxLinesLessThanZeroException();
        }

        if (this.minLines > this.maxLines) {
            throw new MinLinesGreaterThanMaxLinesException();
        }

        if (this.headerLineLength == 0) {
            throw new HeaderLineZeroException();
        }

        if (this.headerLineLength < 0) {
            throw new HeaderLineLessThanZeroException();
        }

        if (this.contentLineLength == 0) {
            throw new ContentLineZeroException();
        }

        if (this.contentLineLength < 0) {
            throw new ContentLineLessThanZeroException();
        }
    }
}
