# Tic Tac Toe

A Java-based Tic Tac Toe game developed as the final project for the **Algorithms and Computer Programming 1 (Alprokom 1)** practicum at Institut Teknologi Sepuluh Nopember (ITS).

## Project Overview

This project provides two implementations of the classic Tic Tac Toe game:

- **CLI (Command-Line Interface):** A terminal-based game built using Java.
- **GUI (Graphical User Interface):** A desktop application built using Java Swing.

Both implementations support playing against another player or a computer opponent, with three available board sizes: 3×3, 4×4, and 5×5.

## Features

### CLI Version
- Player vs. Player mode.
- Player vs. Bot mode with random moves.
- Three board sizes: 3×3, 4×4, and 5×5.
- Input validation and win/draw detection.

### GUI Version
- Player vs. Player mode.
- Player vs. Bot mode.
- Three bot difficulty levels:
  - **Easy:** Random moves.
  - **Medium:** Blocking moves.
  - **Hard:** Minimax algorithm.
- Three board sizes: 3×3, 4×4, and 5×5.
- Interactive graphical interface with win/draw detection.

## Technologies

- Java
- Java Swing
- Java AWT

## Project Structure

```text
.
├── src/
│   ├── CLI/
│   │   └── TicTacToeCLI.java
│   └── GUI/
│       └── TicTacToeApp.java
├── .gitignore
└── README.md
```

## Requirements

- Java Development Kit (JDK)

## How to Run

Compile and run the CLI version:

```bash
javac -d out src/CLI/TicTacToeCLI.java
java -cp out CLI.TicTacToeCLI
```

Compile and run the GUI version:

```bash
javac -d out src/GUI/TicTacToeApp.java
java -cp out GUI.TicTacToeApp
```

## Team Members

1. M. Ma'ruf Qomaruddin Kafi
2. Hanun Nabilah Zein
3. Adira Lasykaryan Nadine

## Course Information

- **Course:** Algorithms and Computer Programming 1
- **Institution:** Institut Teknologi Sepuluh Nopember (ITS)
- **Project Type:** Final Project
- **Course Component:** Practicum