package com.test.bnpparibas.nelima.utils;

import com.test.bnpparibas.nelima.domain.Plateau;
import com.test.bnpparibas.nelima.domain.Rover;

import java.util.List;

/**
 * Record to hold the input data: Plateau, Rovers, and instructions.
 */
public record InputData(Plateau plateau, List<Rover> rovers, List<String> instructionsList) {
}
