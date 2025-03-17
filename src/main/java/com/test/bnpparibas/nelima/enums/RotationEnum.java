package com.test.bnpparibas.nelima.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing the possible rotation directions.
 */
@Getter
@AllArgsConstructor
public enum RotationEnum {
    LEFT(-1),

    RIGHT(1);

    private final int value;
}
