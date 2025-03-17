package com.test.bnpparibas.nelima.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlateauTests {
    private Plateau plateau;

    @BeforeEach
    void setUp() {
        // Given
        plateau = new Plateau(4, 5);
    }

    @Test
    void isValidPosition_withinBounds_returnsTrue() {
        // When
        boolean result = plateau.isValidPosition(3, 2);
        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void isValidPosition_onMinBounds_returnsTrue() {
        // When
        boolean result = plateau.isValidPosition(0, 0);
        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void isValidPosition_onMaxBounds_returnsTrue() {
        // When
        boolean result = plateau.isValidPosition(4, 5);
        // Then
        Assertions.assertTrue(result);
    }

    @Test
    void isValidPosition_xOutOfBoundsPositive_returnsFalse() {
        // When
        boolean result = plateau.isValidPosition(5, 3);
        // Then
        Assertions.assertFalse(result);
    }

    @Test
    void isValidPosition_yOutOfBoundsPositive_returnsFalse() {
        // When
        boolean result = plateau.isValidPosition(2, 6);
        // Then
        Assertions.assertFalse(result);
    }

    @Test
    void isValidPosition_xOutOfBoundsNegative_returnsFalse() {
        // When
        boolean result = plateau.isValidPosition(-1, 5);
        // Then
        Assertions.assertFalse(result);
    }

    @Test
    void isValidPosition_yOutOfBoundsNegative_returnsFalse() {
        // When
        boolean result = plateau.isValidPosition(1, -1);
        // Then
        Assertions.assertFalse(result);
    }
}
