package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Priority in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priority should be one of the following values: "
            + String.join(", ", PriorityValue.getFullPriorities()) + " or "
            + String.join(", ", PriorityValue.getShortPriorities());
    public final PriorityValue value;

    /**
     * Constructs an {@code Priority}.
     *
     * @param priority A valid priority string input.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = PriorityValue.getPriority(priority);
    }

    /**
     * Returns if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return PriorityValue.FULL_PRIORITY_MAP.containsKey(test.toLowerCase())
                || PriorityValue.SHORT_PRIORITY_MAP.containsKey(test.toLowerCase());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Priority)) {
            return false;
        }

        Priority otherPriority = (Priority) other;
        return value.equals(otherPriority.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
