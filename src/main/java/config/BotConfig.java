package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotConfig {
    private static final Logger logger = LogManager.getLogger(BotConfig.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = BotConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.error("Unable to find application.properties");
                throw new IOException("application.properties not found");
            }
            properties.load(input);
            logger.info("Successfully loaded application.properties");
        } catch (IOException e) {
            logger.error("Error loading application.properties", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getBotToken() {
        return properties.getProperty("bot.token");
    }

    public static String getBotUsername() {
        return properties.getProperty("bot.username");
    }
}