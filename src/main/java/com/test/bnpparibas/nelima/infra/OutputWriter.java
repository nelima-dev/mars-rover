package com.test.bnpparibas.nelima.infra;

import com.test.bnpparibas.nelima.domain.Rover;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Writes the output data to the console.
 */
@Slf4j
public class OutputWriter {
    /**
     * Writes the final position and orientation of each Rover to the console.
     *
     * @param rovers The list of Rovers.
     */
    public void writeOutput(List<Rover> rovers) {
        StringBuilder output = new StringBuilder("Output:\n");
        rovers.forEach(rover -> output.append(rover.toString()).append("\n"));
        log.info(output.toString());
    }
}
