package handlers;

import commands.Command;
import commands.StartCommand;
import constants.BotConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.BotService;

public class MessageHandler {
    private static final Logger logger = LogManager.getLogger(MessageHandler.class);
    private final BotService botService;

    public MessageHandler(BotService botService) {
        this.botService = botService;
    }

    public void handle(Update update) throws TelegramApiException {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            logger.info("Received message: {} from chat: {}", messageText, update.getMessage().getChatId());

            Command command = null;
            if (BotConstants.START_COMMAND.equals(messageText)) {
                command = new StartCommand(botService);
            }

            if (command != null) {
                command.execute(update);
            } else {
                logger.debug("No command matched for message: {}", messageText);
            }
        }
    }
}
