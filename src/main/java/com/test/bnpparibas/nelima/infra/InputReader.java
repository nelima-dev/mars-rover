package com.test.bnpparibas.nelima.infra;

import com.test.bnpparibas.nelima.domain.Plateau;
import com.test.bnpparibas.nelima.domain.Rover;
import com.test.bnpparibas.nelima.exeption.RoverException;
import com.test.bnpparibas.nelima.utils.ErrorMessageConstants;
import com.test.bnpparibas.nelima.utils.InputData;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads input data from a file.
 */
@AllArgsConstructor
public class InputReader {
    private final String filename;

    /**
     * Reads input data from a file, parses it, and constructs an InputData object.
     *
     * @return An InputData object containing the parsed plateau, rovers, and instructions.
     */
    public InputData readInput() {
        File file = new File(filename);
        // Check if the file exists and is not empty
        if (!file.exists() || file.length() == 0) {
            throw new RoverException(ErrorMessageConstants.EMPTY_OR_INVALID_FILE);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Get the plateau dimensions from the first line
            Plateau plateau = getPlateau(reader.readLine());

            List<Rover> rovers = new ArrayList<>();
            List<String> instructionsList = new ArrayList<>();

            String line;
            int lineNumber = 2; // Start from line 2 after the plateau dimensions.
            while ((line = reader.readLine()) != null) {
                String[] roverData = line.split(" ");
                // Check if the rover data format is valid
                if (roverData.length != 3) {
                    throw new RoverException(ErrorMessageConstants.INVALID_ROVER_DATA_FORMAT + " " + lineNumber);
                }

                // Parse the rover data and add it to the list
                rovers.add(parseRoverData(roverData, lineNumber));
                // Read the rover instructions
                String instructions = reader.readLine();
                // Check if instructions are present
                if (instructions == null || instructions.trim().isEmpty()) {
                    throw new RoverException(ErrorMessageConstants.MISSING_INSTRUCTION_FOR_ROVER + " " + lineNumber);
                }
                instructionsList.add(instructions);
                lineNumber += 2; // Increment by 2 for rover data and instructions
            }

            return new InputData(plateau, rovers, instructionsList);
        } catch (IOException e) {
            throw new RoverException(ErrorMessageConstants.READ_FILE_ERROR + ": " + e.getMessage());
        }
    }

    /**
     * Parses the plateau dimensions from the first line of the input file.
     *
     * @param firstLine The first line of the input file containing plateau dimensions.
     * @return A Plateau object created from the parsed dimensions.
     */
    private static Plateau getPlateau(String firstLine) {
        // Check if the first line is empty or null
        if (firstLine == null || firstLine.trim().isEmpty()) {
            throw new RoverException(ErrorMessageConstants.EMPTY_PLATEAU_DIMENSION);
        }

        String[] plateauDimensions = firstLine.split(" ");
        // Check if the plateau dimensions format is valid
        if (plateauDimensions.length != 2) {
            throw new RoverException(ErrorMessageConstants.INVALID_PLATEAU_DIMENSION_FORMAT);
        }

        try {
            // Parse the plateau dimensions as integers
            int maxX = Integer.parseInt(plateauDimensions[0]);
            int maxY = Integer.parseInt(plateauDimensions[1]);
            return new Plateau(maxX, maxY);
        } catch (NumberFormatException e) {
            throw new RoverException(ErrorMessageConstants.NAN_PLATEAU_DIMENSION);
        }
    }

    /**
     * Validates and converts the provided rover data into a Rover object.
     *
     * @param roverData  Array of strings representing the rover's data.
     * @param lineNumber The line number in the file where the data was read from.
     * @return A new Rover object based on the parsed data.
     */
    private Rover parseRoverData(String[] roverData, int lineNumber) {
        try {
            // Parse the rover data as integers and characters
            int x = Integer.parseInt(roverData[0]);
            int y = Integer.parseInt(roverData[1]);
            char orientation = roverData[2].charAt(0);
            return new Rover(x, y, orientation);
        } catch (NumberFormatException e) {
            throw new RoverException(ErrorMessageConstants.NAN_ROVER_DATA + " " + lineNumber);
        }
    }
}
