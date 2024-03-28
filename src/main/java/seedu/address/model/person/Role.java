package seedu.address.model.person;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public enum Role {
    STUDENT,
    TA,
    PROFESSOR;

    public static final String MESSAGE_CONSTRAINTS =
            "Roles should be either 'STUDENT', 'TA', or 'PROFESSOR'.";

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        try {
            valueOf(test); // This will throw an IllegalArgumentException if the value is not a valid role
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
