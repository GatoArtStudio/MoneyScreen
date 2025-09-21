# MoneyScreen Mod

MoneyScreen is a simple server-side Fabric mod for Minecraft that allows an operator to display a custom screen to all online players. This screen shows a configurable amount of money and a player count.

## Features

*   **Custom Screen:** Displays a clean, bordered screen with important information.
*   **Real-time Updates:** Push updates to all players simultaneously.
*   **Easy to Use:** A simple command allows for quick updates.

## Usage

To display or update the screen for all players, use the following command:

```bash
/moneyscreen <money> <players>
```

-   `<money>`: The amount of money to display.
-   `<players>`: The number of players to display.

### Example

```bash
/moneyscreen 15000 42
```

This command will show a screen to all players indicating that there are 42 players remaining and the prize is 15,000 Colombian Pesos.

## Building from Source

To build the mod from the source code, you will need to have the Java Development Kit (JDK) installed.

1.  Clone the repository:
    ```bash
    git clone https://github.com/GatoArtStudio/MoneyScreen.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd MoneyScreen
    ```
3.  Run the Gradle build command:
    ```bash
    ./gradlew build
    ```

The compiled `.jar` file will be located in the `build/libs/` directory.

## License

Copyright (c) 2025 GatoArtStudio. All rights reserved.
