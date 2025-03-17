# Mars Rover Project

## Description

This project simulates the movement of a rover on a Martian plateau. 
The rover can receive instructions to move forward, turn left, or turn right.

## Features

* Rover movement on a Martian plateau
* Management of movement instructions (L, R, M)
* Collision management with plateau edges
* Display of the rover's final position

## Usage

1.  Define the dimensions of the Martian plateau
2.  Define the rover's initial position
3.  Define the rover's movement instructions
4.  Run the program

## Example

5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM

Which means:

* The Martian plateau has dimensions 5x5
* The first rover is positioned at (1, 2) and oriented North
* The first rover's movement instructions are LMLMLMLMM
* The second rover is positioned at (3, 3) and oriented East
* The second rover's movement instructions are MMRMMRMRRM

## Output

1 3 N
5 1 E

Which means:

* The first rover's final position is (1, 3) and its orientation is North
* The second rover's final position is (5, 1) and its orientation is East

## Technologies Used

* Java 17
* Maven 3
* JUnit 5
* SLF4j
* Lombok
* Mockito

## Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/nelima-dev/mars-rover
    ```
2.  **Install Dependencies (using Maven):**
    * Ensure you have Maven installed.
    * Navigate to the project directory in your terminal.
    * Run the following command to download the project dependencies:
        ```bash
        mvn clean install
        ```
    * This will download libraries such as JUnit, SLF4j, and Mockito; and package the project JAR file
 
3.  **Run the program:**
    * Run the JAR file:
        ```bash
        java -jar rover.jar input.txt
        ```
        * You should replace `input.txt` with the path to your input file.

## Tests

Unit tests are written with JUnit 5.

To run the tests, use the following command:

mvn test


