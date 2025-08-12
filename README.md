# Telegram User Info Bot

A Telegram bot that provides user information in response to the `/start` command, built with Java and the Telegram Bots API. The bot displays user details in a formatted HTML message with block quotes and includes a reply keyboard with a "Start" button to re-trigger the command.

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6.0 or higher
- **Telegram Bot Token**: Obtain from [BotFather](https://t.me/BotFather)

## Setup

**Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/get-user-info-bot.git
   cd get-user-info-bot
   ```

### Configure Bot Token:

**Edit** `src/main/resources/application.properties:`
```
bot.token=YOUR_BOT_TOKEN_HERE
bot.username=@YourBotUsername
```


**Replace** `YOUR_BOT_TOKEN_HERE` with the token from **BotFather** and `@YourBotUsername` with your **bot's username**.


**Ensure Log Directory:**

Create a logs/ directory in the project root:
```bash
mkdir logs
```

### Installation

Run the following command to download dependencies and build the project:
```mvn clean install```

### Start the Bot:

Run the bot using Maven:
```mvn exec:java```


Alternatively, build a JAR and run it:
```
mvn clean package
java -jar target/telegram-user-info-bot-1.0-SNAPSHOT.jar
```


### Interact with the Bot:

Open Telegram and start a chat with your bot.
Send `/start` to receive user information in a formatted message with a `"/start" button`.

**Example Output**
```code
ℹ️ User Information
| ID: 123456789
| Username: @ExampleUser
| First Name: John
| Last Name: Doe
| Language Code: EN
| Is Bot: false
```
