package com.test.bnpparibas.nelima.domain;

import com.test.bnpparibas.nelima.enums.OrientationEnum;
import com.test.bnpparibas.nelima.enums.RotationEnum;
import com.test.bnpparibas.nelima.exeption.RoverException;
import com.test.bnpparibas.nelima.utils.ErrorMessageConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Rover exploring the Plateau.
 */
@Getter
@Setter
@NoArgsConstructor
public class Rover {
    private int x;

    private int y;

    private OrientationEnum orientation;

    /**
     * Constructor for creating a Rover with initial position and orientation.
     *
     * @param x             The initial x-coordinate.
     * @param y             The initial y-coordinate.
     * @param orientation   The initial orientation (N, E, S, W).
     */
    public Rover(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = OrientationEnum.fromCode(orientation);
    }

    /**
     * Turns the Rover left or right.
     *
     * @param rotation The rotation direction (LEFT or RIGHT).
     */
    public void turn(RotationEnum rotation) {
        this.orientation = this.orientation.rotate(rotation);
    }

    /**
     * Moves the Rover forward one unit in its current orientation.
     *
     * @throws RoverException If the orientation is invalid.
     */
    public void moveForward() {
        switch (orientation) {
            case NORTH -> y++;
            case EAST -> x++;
            case SOUTH -> y--;
            case WEST -> x--;
            default -> throw new RoverException(ErrorMessageConstants.INVALID_ORIENTATION_CODE + ": " + orientation);
        }
    }

    /**
     * Returns a string representation of the Rover's current position and orientation.
     *
     * @return The Rover's position and orientation as a string.
     */
    @Override
    public String toString() {
        return String.format("%d %d %c", x, y, orientation.getCode());
    }
}
