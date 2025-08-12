package commands;

import constants.BotConstants;
import keyboard.ReplyKeyboardProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.BotService;

public class StartCommand implements Command {
    private static final Logger logger = LogManager.getLogger(StartCommand.class);
    private final BotService botService;

    public StartCommand(BotService botService) {
        this.botService = botService;
    }

    @Override
    public void execute(Update update) throws TelegramApiException {
        logger.info("Processing /start command from user: {}", update.getMessage().getFrom().getId());
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setParseMode(BotConstants.formatting);
        message.setReplyMarkup(ReplyKeyboardProvider.getStartKeyboard());

        try {
            User user = update.getMessage().getFrom();
            StringBuilder response = new StringBuilder(BotConstants.Emojis.USER).append(" <b>User Information</b>\n");

            response.append("<blockquote>");
            user.getId();
            response.append("<b>ID:</b> ")
                    .append(escapeHtml(user.getId().toString()))
                    .append("\n");
            if (user.getUserName() != null) {
                response.append("<b>Username:</b> @")
                        .append(escapeHtml(user.getUserName()))
                        .append("\n");
            }
            user.getFirstName();
            response.append("<b>First Name:</b> ")
                    .append(escapeHtml(user.getFirstName()))
                    .append("\n");
            if (user.getLastName() != null) {
                response.append("<b>Last Name:</b> ")
                        .append(escapeHtml(user.getLastName()))
                        .append("\n");
            }
            if (user.getLanguageCode() != null) {
                response.append("<b>Language Code:</b> ")
                        .append(escapeHtml(user.getLanguageCode().toUpperCase()))
                        .append("\n");
            }
            response.append("<b>Premium:</b> ")
                    .append(escapeHtml(user.getIsPremium() != null ? BotConstants.Emojis.CHECK : BotConstants.Emojis.CROSS))
                    .append("\n");
            response.append("</blockquote>");

            message.setChatId(update
                    .getMessage()
                    .getChatId()
                    .toString()
            );
            message.setText(response.toString());
            message.setParseMode(BotConstants.formatting);
            botService.sendMessage(message);
            logger.debug("Sent user info response to chat: {}", update.getMessage().getChatId());
        } catch (Exception e) {
            logger.error("Error processing user information for chat: {}", update.getMessage().getChatId(), e);
            message.setText(BotConstants.Messages.Errors.ERROR_MESSAGE);
            throw new TelegramApiException(BotConstants.Messages.Errors.ERROR_MESSAGE);
        }
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}


