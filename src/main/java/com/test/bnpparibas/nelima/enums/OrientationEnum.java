package com.test.bnpparibas.nelima.enums;

import com.test.bnpparibas.nelima.exeption.RoverException;
import com.test.bnpparibas.nelima.utils.ErrorMessageConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the possible orientations of a Rover.
 */
@Getter
@AllArgsConstructor
public enum OrientationEnum {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private final char code;

    /**
     * Converts a character code to the corresponding OrientationEnum.
     *
     * @param code              The character code (N, E, S, W).
     * @return                  The corresponding OrientationEnum.
     * @throws RoverException   If the orientation code is invalid.
     */
    public static OrientationEnum fromCode(char code) {
        for (OrientationEnum orientation : values()) {
            if (orientation.code == code) {
                return orientation;
            }
        }
        throw new RoverException(ErrorMessageConstants.INVALID_ORIENTATION_CODE + ": " + code);
    }

    /**
     * Rotates the orientation based on the given RotationEnum.
     *
     * @param rotation  The rotation direction (LEFT or RIGHT).
     * @return          The new OrientationEnum after rotation.
     */
    public OrientationEnum rotate(RotationEnum rotation) {
        int currentIndex = this.ordinal();
        int newIndex = (currentIndex + rotation.getValue()) % values().length;
        if (newIndex < 0) {
            newIndex += values().length;
        }
        return values()[newIndex];
    }
}
