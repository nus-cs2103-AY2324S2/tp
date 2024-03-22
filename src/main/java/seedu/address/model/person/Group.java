package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's group in the hackathon.
 */
public class Group {
    public static final String MESSAGE_CONSTRAINTS =
            "Group should be a positive integer.";
    private static int lastGroupNumber = 0;
    private final int groupNumber;


    /**
     * Constructs a {@code Group} with group number 0.
     */
    public Group() {
        this.groupNumber = 0;
    }
    /**
     * Constructs a {@code Group}.
     *
     * @param groupNumber a positive integer.
     */
    public Group(int groupNumber) {
        checkArgument(isValidGroup(groupNumber), MESSAGE_CONSTRAINTS);
        this.groupNumber = groupNumber;
    }

    /**
     * Returns true if a given string is a valid Group.
     */
    public static boolean isValidGroup(int test) {
        boolean isPositive = test > 0;
        boolean isSmallerThanLast = test <= lastGroupNumber;
        return isPositive && isSmallerThanLast;
    }

    /**
     * Sets the last group number.
     */
    public static void setLastGroupNumber(int lastGroupNumber) {
        lastGroupNumber = lastGroupNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(groupNumber);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherCategory = (Group) other;
        return groupNumber == otherCategory.groupNumber;
    }
}
