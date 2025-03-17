package com.test.bnpparibas.nelima.service;

import com.test.bnpparibas.nelima.domain.Plateau;
import com.test.bnpparibas.nelima.domain.Rover;
import com.test.bnpparibas.nelima.enums.OrientationEnum;
import com.test.bnpparibas.nelima.exeption.RoverException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoverServiceTests {
    private Plateau plateau;
    private RoverService roverService;
    private Rover rover;

    @BeforeEach
    void setUp() {
        plateau = mock(Plateau.class);
        roverService = new RoverService(plateau);
        rover = new Rover(1, 2, 'N');
    }

    @ParameterizedTest
    @MethodSource("instructionTestCases")
    void processInstructions_validInstructions_roverMovesAndTurnsCorrectly(
            String instructions, int expectedX, int expectedY, OrientationEnum expectedOrientation) {
        // Given
        when(plateau.isValidPosition(anyInt(), anyInt())).thenReturn(true);

        // When
        roverService.processInstructions(rover, instructions);

        // Then
        assertEquals(expectedX, rover.getX());
        assertEquals(expectedY, rover.getY());
        assertEquals(expectedOrientation, rover.getOrientation());
    }

    @Test
    void processInstructions_invalidInstruction_throwsRoverException() {
        // Given: Invalid orientation 'X'
        // When: Processing instructions with the invalid orientation
        // Then: A RoverException should be thrown
        assertThrows(RoverException.class, () -> roverService.processInstructions(rover, "X"));
    }

    static Stream<Arguments> instructionTestCases() {
        return Stream.of(
                Arguments.of("LMLMLMLMM", 1, 3, OrientationEnum.NORTH),
                Arguments.of("RMM", 3, 2, OrientationEnum.EAST),
                Arguments.of("LMM", -1, 2, OrientationEnum.WEST),
                Arguments.of("M", 1, 3, OrientationEnum.NORTH), //Single move
                Arguments.of("LLLL", 1, 2, OrientationEnum.NORTH), //Multiple turns
                Arguments.of("RRRR", 1, 2, OrientationEnum.NORTH),
                Arguments.of("LR", 1, 2, OrientationEnum.NORTH),
                Arguments.of("RL", 1, 2, OrientationEnum.NORTH)
        );
    }
}
