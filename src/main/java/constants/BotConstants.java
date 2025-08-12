package constants;

public class BotConstants {
    /* COMMANDS */
    public static final String START_COMMAND = "/start";

    /* SETTINGS */
    public static final String formatting = "HTML";

    /* MESSAGES */
    public static final class Messages {

        /* ERROR MESSAGES */
        public static final class Errors {
            public static final String ERROR_MESSAGE =
                    "An error occurred while processing your request. Try again later.";
            public static final String PROCESSING_ERROR =
                    "An error occurred while receiving the information. Try again later.";
        }
    }

    /* EMOJI */
    public static final class Emojis {
        public static final String INFO = "📋";
        public static final String USER = "👤";
        public static final String CROSS = "❌";
        public static final String CHECK = "✅";
    }

    private BotConstants() {
        throw new UnsupportedOperationException("This is a utilitarian class and cannot be instantiated.");
    }
}
