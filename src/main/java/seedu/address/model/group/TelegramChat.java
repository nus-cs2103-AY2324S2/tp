package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a group's telegram chat link in the group list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramChat(String)}
 */
public class TelegramChat {

    public static final String MESSAGE_CONSTRAINTS = "Telegram chat links should be of the "
            + "format https://t.me/<chat_link>, where <chat_link> is a string of alphanumeric "
            + "characters and the special characters '+', '_', '-' (without quotes).";
    private static final String TELEGRAM_CHAT_REGEX = "https://t.me/[a-zA-Z0-9_+-]+";

    public final String value;

    /**
     * Constructs a {@code TelegramChat}.
     *
     * @param telegramChat A valid telegram chat link.
     */
    public TelegramChat(String telegramChat) {
        requireNonNull(telegramChat);
        checkArgument(isValidTelegramChat(telegramChat), MESSAGE_CONSTRAINTS);
        value = telegramChat;
    }

    /**
     * Returns if a given string is a valid telegram chat link.
     */
    public static boolean isValidTelegramChat(String test) {
        return test.matches(TELEGRAM_CHAT_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramChat)) {
            return false;
        }

        TelegramChat otherTelegramChat = (TelegramChat) other;
        return otherTelegramChat.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
