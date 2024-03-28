package seedu.address.model.person;

/**
 * Represents the sort criteria of the client list.
 */
public enum SortCriteria {
    NAME,
    PHONE,
    EMAIL,
    ADDRESS,
    BIRTHDAY,
    PRIORITY,
    LASTMET,
    SCHEDULE,
    INVALID;

    public static final String MESSAGE_CONSTRAINTS = "Sort criteria should be either "
            + SortCriteria.NAME + ", "
            + SortCriteria.PHONE + ", "
            + SortCriteria.EMAIL + ", "
            + SortCriteria.ADDRESS + ", "
            + SortCriteria.BIRTHDAY + ", "
            + SortCriteria.PRIORITY + ", "
            + SortCriteria.LASTMET + " or "
            + SortCriteria.SCHEDULE + ".";

    /**
     * Returns the sort criteria given the string.
     * Should check if the given string is a valid sort criteria before calling this method.
     *
     * @param test the given string
     * @return the sort criteria, or INVALID if the given string is not a valid sort criteria
     */
    public static SortCriteria getSortCriteria(String test) {
        if (test == null) {
            return SortCriteria.INVALID;
        }
        for (SortCriteria criteria : SortCriteria.values()) {
            if (criteria.name().equalsIgnoreCase(test)) {
                return criteria;
            }
        }
        return SortCriteria.INVALID;
    }

    /**
     * Returns if the given string is a valid sort criteria.
     *
     * @param test the given string
     * @return true if the given string is a valid sort criteria
     */
    public static boolean isValidSortCriteria(String test) {
        if (test == null) {
            return false;
        }
        if (test.equalsIgnoreCase(SortCriteria.INVALID.toString())) {
            return false;
        }
        for (SortCriteria criteria : SortCriteria.values()) {
            if (criteria.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the string representation of the sort criteria.
     *
     * @return the string representation of the sort criteria
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
