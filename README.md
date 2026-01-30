# Java Battleship OOP

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)

This is a project meant for me to practice OOP and software engineering principles. It has no intention of being used for any other purpose. Completeness is not the goal, learning is.

A robust, object-oriented implementation of the classic Battleship strategy game, built with modern Java 21. Challenge the CPU in this console-based tactical warfare simulation.

## Features

- **Object-Oriented Design**: Clean, modular architecture demonstrating SOLID principles.
- **Single Player vs CPU**: Test your strategy against a randomized computer opponent.
- **Randomized Setup**: Ships are automatically placed on the board for quick game starts.
- **Interactive Console UI**: Clear, text-based interface for coordinate input and game feedback.
- **Modern Tech Stack**: Utilizes Java 21, Lombok, and Maven.

## Design Patterns

The architecture relies heavily on proven design patterns to ensure maintainability and extensibility:

-   **Observer Pattern**: Used extensively for event handling. The `Game` class observes `Player` actions, while `Player` instances observe their `Ship`s to track damage. This keeps the game logic decoupled from the UI updates.
-   **Strategy Pattern**: Implemented via the `InputHandler` interface, allowing the game to seamlessly switch between different input sources (e.g., specific strategies for the CPU vs Console input for the user).
-   **Template Method Pattern**: The `AbstractPlayer` class defines the skeleton of a player's behavior, enforcing a common structure while allowing subclasses (`Player`, `RandomPlayer`) to provide specific implementations for move generation.

## Getting Started

### Prerequisites

- **Java JDK 21** or higher
- **Maven 3.6+**

### Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/your-username/Java-battleship-oop.git
    cd Java-battleship-oop
    ```

2.  Build the project:
    ```bash
    mvn clean install
    ```

### Docker Support

Alternatively, you can build and run the application using Docker:

1.  Build the Docker image:
    ```bash
    docker build -t java-battleship-oop .
    ```

2.  Run the game container (interactive mode is required):
    ```bash
    docker run -it --rm java-battleship-oop
    ```

### Run the Game

Execute the compiled JAR file:

```bash
java -jar target/Java-battleship-oop-1.0.0.jar
```

## How to Play

1.  Run the application.
2.  The game initializes with two boards: yours and the CPU's.
3.  Enter coordinates (e.g., `A5`, `B2`) to fire upon the enemy fleet.
4.  Track hits (`X`) and misses (`O`) on the display.
5.  Sink all enemy ships to claim victory!

## Project Structure

The source code is organized into the following packages under `org.example`:

- **`board`**: Manages the grid, cells, and placement logic.
- **`ship`**: Defines ship types (Carrier, Battleship, etc.) and their behaviors.
- **`player`**: Handles player actions (Human and CPU).
- **`game`**: Core game loop and state management.
- **`input`**: Interfaces for handling user commands.
- **`output`**: Controls how the game state is rendered to the console.

## Testing

Current line coverage is at 85%.

Run unit tests to verify the integrity of the game logic:

```bash
mvn test
```

## ️Roadmap

Future releases will focus on enhancing the player experience and introducing advanced AI capabilities:

- [ ] **Advanced Game Setup**:
    -   **Manual Ship Placement**: Giving captains full control over their fleet formation.
    -   **Game Mode Selection**: Choose between **Single Player (vs CPU)** or **Hotseat Multiplayer (PvP)**.
- [ ] **AI & Integration**:
    -   **MCP Server Integration**: Expose the game via the Model Context Protocol to allow playing against Large Language Models (LLMs).
    -   **Smarter CPU**: Implement probability-based targeting algorithms.
- [ ] **UI/UX Improvements**:
    -   Color-coded console output for better visibility.
    -   Detailed end-game statistics.
- [ ] **Testing**:
    -   Add more unit tests.
    -   Add integration tests.
    -   Add end-to-end tests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---
