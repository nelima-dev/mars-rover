package com.test.bnpparibas.nelima;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.test.bnpparibas.nelima.config.InMemoryAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarsRoverApplicationTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardErr = System.err;
    private final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();
    private InMemoryAppender appender;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errorStreamCaptor));
        // Set up Logback with the test configuration
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        try {
            configurator.doConfigure(getClass().getResourceAsStream("/logback-test.xml"));
        } catch (Exception e) {
            Assertions.fail(e);
        }

        // Get the InMemoryAppender
        Logger logger = (Logger) LoggerFactory.getLogger("com.test.bnpparibas.nelima.config");
        appender = (InMemoryAppender) logger.getAppender("InMemory");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setErr(standardErr);
        if (appender != null) {
            appender.clear();
        }
    }

    // Helper method to create a test input file in test/resources/input
    private void createTestFile(String content, String fileName) throws IOException {
        File file = new File("src/test/resources/input/" + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    @Test
    void main_validInputFile_correctOutput() throws IOException {
        createTestFile("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM", "valid_main_input.txt");
        MarsRoverApplication.main(new String[]{"src/test/resources/input/valid_main_input.txt"});
        assertTrue(outputStreamCaptor.toString().contains("1 3 N\n5 1 E\n"));
    }

    @Test
    void main_invalidInputFile_errorMessage() {
        MarsRoverApplication.main(new String[]{"src/test/resources/input/nonexistent_file.txt"});
        MarsRoverApplication.main(new String[]{"src/test/resources/input/nonexistent_file.txt"});
        assertTrue(appender.contains("Application error: Error reading file:"));
    }

    @Test
    void main_missingInputFileArgument_usageMessage() {
        MarsRoverApplication.main(new String[]{});
        assertTrue(errorStreamCaptor.toString().contains("Usage: java -jar rover.jar <input_file>"));
    }

    @Test
    void main_emptyInputFile_errorMessage() throws IOException {
        createTestFile("", "empty_main_input.txt");
        MarsRoverApplication.main(new String[]{"src/test/resources/input/empty_main_input.txt"});
        assertTrue(appender.contains("Application error: File does not contain plateau dimensions."));
    }

    @Test
    void main_invalidPlateauFormat_errorMessage() throws IOException {
        createTestFile("5\n1 2 N\nLMLMLMLMM", "invalid_plateau_format.txt");
        MarsRoverApplication.main(new String[]{"src/test/resources/input/invalid_plateau_format.txt"});
        assertTrue(appender.contains("Application error: Invalid plateau dimensions format."));
    }
}
