# Telegram Goose Bot

## Overview
Telegram Goose Bot is an interactive game bot for Telegram that allows users to level up their goose from a regular farm animal to a biological weapon by completing various tasks and earning coins. The final goal is to buy a Javelin and achieve victory.

## Features
- Interactive level-up system with tasks.
- Randomized task selection for each level.
- Inline buttons for task selection.
- Image-based level progression.
- Coin-based progression system.

## Installation
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven
- Telegram Bot API key

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/telegram-goose-bot.git
   cd telegram-goose-bot
   ```
2. Install dependencies and build the project:
   ```sh
   mvn clean install
   ```
3. Set up your Telegram bot credentials by replacing the `getBotUsername()` and `getBotToken()` values in `TelegramBot.java` with your own bot's details.
4. Run the bot:
   ```sh
   java -jar target/telegram-goose-bot.jar
   ```

## How It Works
1. Users start the bot with the `/start` command.
2. The bot sends an introductory message along with an animated image.
3. Users choose tasks using inline buttons to earn coins and level up.
4. Each new level introduces new tasks and a new goose transformation.
5. At level 4, users can buy a Javelin for 50 coins to complete the game.

## File Structure
```
telegram-goose-bot/
│-- src/main/java/org/example/
│   ├── TelegramBot.java  # Main bot logic
│-- images/               # Contains GIFs for each level
│-- pom.xml               # Maven dependencies
│-- README.md             # Project documentation
```

## Configuration
Make sure to create an `images/` folder inside the project directory and place the corresponding `.gif` images for each level.

## Contributing
Feel free to submit issues and pull requests if you find any improvements or bug fixes.

## License
This project is open-source and available under the MIT License.

