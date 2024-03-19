package seedu.address.model.person;

import static seedu.address.model.person.Priority.MESSAGE_CONSTRAINTS;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the value of a person's priority in the address book.
 */
public enum PriorityValue {
    LOW, MEDIUM, HIGH, VIP;

    /**
     * Mapping of priority values to their corresponding string representations in full form.
     */
    public static final HashMap<String, PriorityValue> FULL_PRIORITY_MAP = new HashMap<>(
            Map.of("low", LOW, "medium", MEDIUM, "high", HIGH, "vip", VIP)
    );

    /**
     * Mapping of priority values to their corresponding string representations in short form.
     */
    public static final HashMap<String, PriorityValue> SHORT_PRIORITY_MAP = new HashMap<>(
            Map.of("l", LOW, "m", MEDIUM, "h", HIGH, "v", VIP)
    );

    /**
     * Returns the priority value from the input string.
     * Checks have been made to ensure that the input is valid before calling this method.
     *
     * @param priority the input string
     * @return the priority value
     */
    public static PriorityValue getPriority(String priority) throws IllegalArgumentException {
        if (priority == null) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        String lowerCaseInput = priority.toLowerCase();
        if (FULL_PRIORITY_MAP.containsKey(lowerCaseInput)) {
            return FULL_PRIORITY_MAP.get(lowerCaseInput);
        }
        if (SHORT_PRIORITY_MAP.containsKey(lowerCaseInput)) {
            return SHORT_PRIORITY_MAP.get(lowerCaseInput);
        }
        throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns the full form string representation of the priority value.
     *
     * @return the full form string representation of the priority value
     */
    public static String[] getFullPriorities() {
        return new String[]{"low", "medium", "high", "vip"};
    }

    /**
     * Returns the short form string representation of the priority value.
     *
     * @return the short form string representation of the priority value
     */
    public static String[] getShortPriorities() {
        return new String[]{"l", "m", "h", "v"};
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
