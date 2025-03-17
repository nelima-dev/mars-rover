package com.test.bnpparibas.nelima;

import com.test.bnpparibas.nelima.exeption.RoverException;
import com.test.bnpparibas.nelima.infra.InputReader;
import com.test.bnpparibas.nelima.infra.OutputWriter;
import com.test.bnpparibas.nelima.service.RoverService;
import com.test.bnpparibas.nelima.utils.InputData;
import lombok.extern.slf4j.Slf4j;

/**
 * Main class to execute the Rover application.
 */
@Slf4j
public class MarsRoverApplication {
    /**
     * Main entry point of the application.
     *
     * @param args Command-line arguments. Expects one argument: the input file path.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            log.error("Usage: java -jar rover.jar <input_file>");
            System.exit(1);
        }

        String inputFilename = args[0];

        try {
            InputReader inputReader = new InputReader(inputFilename);
            InputData inputData = inputReader.readInput(); // Read input only once

            RoverService roverService = new RoverService(inputData.plateau());
            OutputWriter outputWriter = new OutputWriter();

            // Process rovers
            for (int i = 0; i < inputData.rovers().size(); i++) {
                roverService.processInstructions(inputData.rovers().get(i), inputData.instructionsList().get(i));
            }

            outputWriter.writeOutput(inputData.rovers());

        } catch (RoverException e) {
            log.error("Application error: {}", e.getMessage());
            System.exit(1);
        }
    }
}
