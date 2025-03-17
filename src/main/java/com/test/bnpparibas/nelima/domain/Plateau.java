package com.test.bnpparibas.nelima.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the Plateau on which the rovers navigate.
 */
@Getter
@AllArgsConstructor
public class Plateau {
    private int maxX;

    private int maxY;

    /**
     * Checks if a given position is valid within the Plateau's boundaries.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @return  A boolean; true if the position is valid, false otherwise.
     */
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
    }
}
