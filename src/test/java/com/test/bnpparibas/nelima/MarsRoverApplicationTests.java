package com.test.bnpparibas.nelima;

import com.test.bnpparibas.nelima.utils.ErrorMessageConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarsRoverApplicationTests {
    private static final String FILE_DIR = "src/test/resources/input/";

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    // Helper method to create a test input file in test/resources/input
    private void createTestFile(String content, String fileName) throws IOException {
        File file = new File(FILE_DIR + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    @Test
    void main_validInputFile_correctOutput() throws IOException {
        createTestFile("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM", "valid_main_input.txt");
        MarsRoverApplication.main(new String[]{FILE_DIR + "valid_main_input.txt"});
        assertTrue(outputStreamCaptor.toString().contains("1 3 N\n5 1 E\n"));
    }

    @Test
    void main_invalidInputFile_errorMessage() {
        MarsRoverApplication.main(new String[]{FILE_DIR + "nonexistent_file.txt"});
        assertTrue(outputStreamCaptor.toString().contains(ErrorMessageConstants.APPLICATION_ERROR + ": " + ErrorMessageConstants.EMPTY_OR_INVALID_FILE));
    }

    @Test
    void main_emptyInputFile_errorMessage() throws IOException {
        createTestFile("", "empty_main_input.txt");
        MarsRoverApplication.main(new String[]{FILE_DIR + "empty_main_input.txt"});
        assertTrue(outputStreamCaptor.toString().contains(ErrorMessageConstants.APPLICATION_ERROR + ": " + ErrorMessageConstants.EMPTY_OR_INVALID_FILE));
    }

    @Test
    void main_invalidPlateauFormat_errorMessage() throws IOException {
        createTestFile("5\n1 2 N\nLMLMLMLMM", "invalid_plateau_format.txt");
        MarsRoverApplication.main(new String[]{FILE_DIR + "invalid_plateau_format.txt"});
        assertTrue(outputStreamCaptor.toString().contains(ErrorMessageConstants.APPLICATION_ERROR + ": " + ErrorMessageConstants.INVALID_PLATEAU_DIMENSION_FORMAT));
    }
}
