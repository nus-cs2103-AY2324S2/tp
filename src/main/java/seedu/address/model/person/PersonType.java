package seedu.address.model.person;

/**
 * Represents the possible types of a contact entry in the tracker.
 * Guarantees: is valid as declared in {@link #isValidPersonType(String)}
 */
public enum PersonType {
    STUDENT,
    TA;

    public static final String MESSAGE_CONSTRAINTS = "Person type should only be 'stu' or 'ta'";

    /**
     * Returns the PersonType enum based on the given string.
     */
    public static PersonType getPersonType(String type) {
        switch (type) {
        case "stu":
            return PersonType.STUDENT;
        case "ta":
            return PersonType.TA;
        default:
            return PersonType.STUDENT;
        }
    }

    /**
     * Returns true if a given string is a valid person type.
     */
    public static boolean isValidPersonType(String type) {
        return type.equals("stu") || type.equals("ta");
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
