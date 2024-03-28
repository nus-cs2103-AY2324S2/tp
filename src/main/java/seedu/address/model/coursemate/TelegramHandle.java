package seedu.address.model.coursemate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a CourseMate's telegram handle in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handles should only contain alphanumeric characters and underscores, "
            + "and should be at least 5 characters long.";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_]{5,}";
    public final String value;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param telegramHandle A valid telegram handle.
     */
    public TelegramHandle(String telegramHandle) {
        requireNonNull(telegramHandle);
        checkArgument(isValidTelegramHandle(telegramHandle), MESSAGE_CONSTRAINTS);
        value = telegramHandle;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     */
    public static boolean isValidTelegramHandle(String test) {
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
        if (!(other instanceof TelegramHandle)) {
            return false;
        }

        TelegramHandle otherTelegramHandle = (TelegramHandle) other;
        return otherTelegramHandle.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
