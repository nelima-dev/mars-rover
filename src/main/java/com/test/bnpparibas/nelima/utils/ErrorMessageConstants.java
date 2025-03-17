package com.test.bnpparibas.nelima.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageConstants {
    public static final String INVALID_ORIENTATION_CODE = "Invalid orientation code";

    public static final String EMPTY_OR_INVALID_FILE = "Input file is empty or invalid";

    public static final String INVALID_ROVER_DATA_FORMAT = "Invalid rover data format at line";

    public static final String MISSING_INSTRUCTION_FOR_ROVER = "Missing instructions for rover at line";

    public static final String READ_FILE_ERROR = "Error reading file";

    public static final String EMPTY_PLATEAU_DIMENSION = "File does not contain plateau dimensions";

    public static final String INVALID_PLATEAU_DIMENSION_FORMAT = "Invalid plateau dimensions format";

    public static final String NAN_PLATEAU_DIMENSION = "Invalid plateau dimensions (must be integers)";

    public static final String NAN_ROVER_DATA = "Invalid rover data (x and y must be integers) at line";

    public static final String INVALID_INSTRUCTION = "Invalid instruction";

    public static final String APPLICATION_ERROR = "Application error";
}
