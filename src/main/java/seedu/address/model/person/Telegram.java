package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram handle in the logbook.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {
    /**
     * Message that displays if telegram username input is invalid.
     */
    public static final String MESSAGE_CONSTRAINTS = "Telegram usernames can only contain "
            + "case-insensitive letters A-Z, digits 0-9, and underscores, with a length between 5 and 32 characters, "
            + "and it should not be blank.";

    /**
     * Regex for telegram username validation.
     * Case-insensitive letters A-Z, digits 0-9, underscores, length between 5 and 32 characters
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9_]{5,32}$";

    public final String value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param telegram A valid telegram.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    /**
     * Returns true if a given string is a valid telegram.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Telegram)) {
            return false;
        }

        Telegram otherTelegram = (Telegram) other;
        return value.equals(otherTelegram.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
