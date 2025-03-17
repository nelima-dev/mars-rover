package com.test.bnpparibas.nelima.infra;

import com.test.bnpparibas.nelima.exeption.RoverException;
import com.test.bnpparibas.nelima.utils.InputData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class InputReaderTests {
    private static final String TEST_FILE = "input.txt";

    // Helper method to create a test input file
    private void createTestFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            writer.write(content);
        }
    }

    @Test
    void readInput_validInput_returnsInputData() throws IOException {
        // Given
        createTestFile("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM");
        InputReader inputReader = new InputReader(TEST_FILE);
        // When
        InputData inputData = inputReader.readInput();
        // Then
        Assertions.assertEquals(5, inputData.plateau().getMaxX());
        Assertions.assertEquals(5, inputData.plateau().getMaxY());
        Assertions.assertEquals(2, inputData.rovers().size());
        Assertions.assertEquals("LMLMLMLMM", inputData.instructionsList().get(0));
    }

    // Cases:
    // empty_file, missing_plateau, invalid_plateau_format
    // invalid_rover_data_format, invalid_rover_coordinates
    // missing_instructions
    @ParameterizedTest
    @ValueSource(strings = {"", "1 2 N\nLMLMLMLMM", "5\n1 2 N\nLMLMLMLMM",
            "5 5\n1 2\nLMLMLMLMM", "5 5\na 2 N\nLMLMLMLMM", "5 5\n1 2 N"})
    void readInput_emptyFile_throwsRoverException(String file) throws IOException {
        createTestFile(file);
        InputReader inputReader = new InputReader(TEST_FILE);
        Assertions.assertThrows(RoverException.class, inputReader::readInput);
    }
}
