package seedu.address.model.person;

/**
 * Represents the sort order of the client list.
 */
public enum SortOrder {
    ASC,
    DESC,
    INVALID;

    public static final String MESSAGE_CONSTRAINTS = "Sort order should be either "
            + SortOrder.ASC + " or "
            + SortOrder.DESC + ".";

    /**
     * Returns the sort order given the string.
     * Should check if the given string is a valid sort order before calling this method.
     *
     * @param test the given string
     * @return the sort order, or INVALID if the given string is not a valid sort order
     */
    public static SortOrder getSortOrder(String test) {
        if (test == null) {
            return SortOrder.INVALID;
        }
        for (SortOrder order : SortOrder.values()) {
            if (order.name().equalsIgnoreCase(test)) {
                return order;
            }
        }
        return SortOrder.INVALID;
    }

    /**
     * Returns if the given string is a valid sort order.
     *
     * @param test the given string
     * @return true if the given string is a valid sort order
     */
    public static boolean isValidSortOrder(String test) {
        if (test == null) {
            return false;
        }
        if (test.equalsIgnoreCase(SortOrder.INVALID.toString())) {
            return false;
        }
        for (SortOrder order : SortOrder.values()) {
            if (order.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the string representation of the sort order.
     *
     * @return the string representation of the sort order
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
