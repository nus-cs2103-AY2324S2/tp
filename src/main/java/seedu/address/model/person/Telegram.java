package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Telegram ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram ID should only contain alphanumeric characters and symbols, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_.-]+$";

    public final String telegramId;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegramId A valid Telegram ID.
     */
    public Telegram(String telegramId) {
        requireNonNull(telegramId);
        checkArgument(isValidTelegram(telegramId), MESSAGE_CONSTRAINTS);
        this.telegramId = telegramId;
    }

    /**
     * Returns true if a given string is a valid Telegram ID.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return telegramId;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Telegram)) {
            return false;
        }

        Telegram otherTelegram = (Telegram) other;
        return telegramId.equals(otherTelegram.telegramId);
    }

    @Override
    public int hashCode() {
        return telegramId.hashCode();
    }

}
