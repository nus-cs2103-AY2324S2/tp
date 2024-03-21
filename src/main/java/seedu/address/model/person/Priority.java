package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's priority level in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Priority {
    public final String value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priorityLevel A valid priority level, which can be "high", "med", or empty.
     * @throws IllegalArgumentException if the priority level is not "high", "med", or empty.
     */
    public Priority(String priorityLevel) {
        requireNonNull(priorityLevel);
        if (!priorityLevel.equals("high") && !priorityLevel.equals("med") && !priorityLevel.isEmpty()) {
            throw new IllegalArgumentException("Priority level must be 'high', 'med', or empty");
        }
        value = priorityLevel;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && value.equals(((Priority) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
