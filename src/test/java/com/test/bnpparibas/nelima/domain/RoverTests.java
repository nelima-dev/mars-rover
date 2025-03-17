package com.test.bnpparibas.nelima.domain;

import com.test.bnpparibas.nelima.enums.OrientationEnum;
import com.test.bnpparibas.nelima.enums.RotationEnum;
import com.test.bnpparibas.nelima.exeption.RoverException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RoverTests {
    private static Stream<Arguments> turnLeftArguments() {
        return Stream.of(
                Arguments.of('N', OrientationEnum.WEST),
                Arguments.of('E', OrientationEnum.NORTH),
                Arguments.of('S', OrientationEnum.EAST),
                Arguments.of('W', OrientationEnum.SOUTH)
        );
    }

    private static Stream<Arguments> turnRightArguments() {
        return Stream.of(
                Arguments.of('N', OrientationEnum.EAST),
                Arguments.of('E', OrientationEnum.SOUTH),
                Arguments.of('S', OrientationEnum.WEST),
                Arguments.of('W', OrientationEnum.NORTH)
        );
    }

    private static Stream<Arguments> moveForwardArguments() {
        return Stream.of(
                Arguments.of('N', 1, 3),
                Arguments.of('E', 2, 2),
                Arguments.of('S', 1, 1),
                Arguments.of('W', 0, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("turnLeftArguments")
    void turn_left_correctOrientation(char initialOrientation, OrientationEnum expectedOrientation) {
        // Given
        Rover rover = new Rover(1, 2, initialOrientation);
        // When
        rover.turn(RotationEnum.LEFT);
        // Then
        Assertions.assertEquals(expectedOrientation, rover.getOrientation());
    }

    @ParameterizedTest
    @MethodSource("turnRightArguments")
    void turn_right_correctOrientation(char initialOrientation, OrientationEnum expectedOrientation) {
        // Given
        Rover rover = new Rover(1, 2, initialOrientation);
        // When
        rover.turn(RotationEnum.RIGHT);
        // Then
        Assertions.assertEquals(expectedOrientation, rover.getOrientation());
    }

    @Test
    void invalidCode_throwsRoverException() {
        // Given: Invalid orientation 'X'
        // When: Creating a Rover with the invalid orientation
        // Then: A RoverException should be thrown
        Assertions.assertThrows(RoverException.class, () -> new Rover(1, 2, 'X'));
    }

    @ParameterizedTest
    @MethodSource("moveForwardArguments")
    void moveForward_correctPositionChange(char initialOrientation, int expectedX, int expectedY) {
        // Given
        Rover rover = new Rover(1, 2, initialOrientation);
        // When
        rover.moveForward();
        // Then
        Assertions.assertEquals(expectedX, rover.getX());
        Assertions.assertEquals(expectedY, rover.getY());
    }

    @Test
    void toString_returnsCorrectFormat() {
        // Given
        Rover rover = new Rover(1, 2, 'E');
        // When
        String roverString = rover.toString();
        // Then
        Assertions.assertEquals("1 2 E", roverString);
    }
}
