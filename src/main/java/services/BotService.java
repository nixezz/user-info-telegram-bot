package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotService {
    private static final Logger logger = LogManager.getLogger(BotService.class);
    private final AbsSender bot;

    public BotService(AbsSender bot) {
        this.bot = bot;
    }

    public void sendMessage(SendMessage message) throws TelegramApiException {
        try {
            bot.execute(message);
            logger.debug("Message sent to chat: {}", message.getChatId());
        } catch (TelegramApiException e) {
            logger.error("Failed to send message to chat: {}", message.getChatId(), e);
            throw e;
        }
    }
}
