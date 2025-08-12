import config.BotConfig;
import handlers.MessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private static final Logger logger = LogManager.getLogger(Bot.class);
    private final MessageHandler messageHandler;

    public Bot() {
        this.messageHandler = new MessageHandler(new services.BotService(this));
    }

    @Override
    public String getBotUsername() {
        return BotConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return BotConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            messageHandler.handle(update);
        } catch (TelegramApiException e) {
            logger.error("Error processing update: {}", update.getUpdateId(), e);
        }
    }
}