package com.test.bnpparibas.nelima.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * InMemoryAppender is a custom Logback appender that captures log messages
 * and stores them in memory for testing purposes.
 * This appender is not thread-safe. It is designed for use in unit tests
 * where thread isolation is typically present.
 */
public class InMemoryAppender extends AppenderBase<ILoggingEvent> {
    // A list to store the formatted log messages.
    private final List<String> messages = new ArrayList<>();

    /**
     * This method is called by Logback when a log event needs to be processed.
     * It formats the logging event and adds it to the list of messages.
     *
     * @param eventObject The logging event to be appended.
     */
    @Override
    protected void append(ILoggingEvent eventObject) {
        //  Customize the format of the log message as needed.
        //  Here, we simply use the event object's toString() method.
        messages.add(eventObject.toString());
    }

    /**
     * Returns the list of captured log messages.
     *
     * @return A List of Strings, where each String represents a formatted log message.
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Checks if any of the captured log messages contain the specified string.
     *
     * @param string The string to search for within the log messages.
     * @return true if any message contains the string, false otherwise.
     */
    public boolean contains(String string) {
        return messages.stream().anyMatch(message -> message.contains(string));
    }

    /**
     * Clears the list of captured log messages.
     * This is useful for resetting the appender between tests.
     */
    public void clear() {
        messages.clear();
    }
}
