package com.test.bnpparibas.nelima.service;

import com.test.bnpparibas.nelima.domain.Plateau;
import com.test.bnpparibas.nelima.domain.Rover;
import com.test.bnpparibas.nelima.enums.RotationEnum;
import com.test.bnpparibas.nelima.exeption.RoverException;
import com.test.bnpparibas.nelima.utils.ErrorMessageConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for processing Rover instructions.
 */
@Slf4j
@AllArgsConstructor
public class RoverService {
    private final Plateau plateau;

    /**
     * Processes the instructions for a given Rover.
     *
     * @param rover           The Rover to process.
     * @param instructions    The instructions string (L, R, M).
     * @throws RoverException If an invalid instruction is encountered or if the Rover goes out of bounds.
     */
    public void processInstructions(Rover rover, String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'L' -> rover.turn(RotationEnum.LEFT);
                case 'R' -> rover.turn(RotationEnum.RIGHT);
                case 'M' -> {
                    int tempX = rover.getX();
                    int tempY = rover.getY();
                    rover.moveForward();
                    if (!plateau.isValidPosition(rover.getX(), rover.getY())) {
                        rover.setX(tempX);
                        rover.setY(tempY);
                        log.warn("Rover went out of bounds, reverted move: {}", rover);
                    }
                }
                default -> throw new RoverException(ErrorMessageConstants.INVALID_INSTRUCTION + ": " + instruction);
            }
        }
    }
}
