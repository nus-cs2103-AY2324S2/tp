package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Category {
    public static final String MESSAGE_CONSTRAINTS =
            "Category should be one of participant, staff, and sponsor.";
    public final String value;

    /**
     * Constructs a {@code Category}.
     *
     * @param category one of Participant, Staff, and Sponsors.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        value = category;
    }

    /**
     * Returns true if a given string is a valid Category.
     */
    public static boolean isValidCategory(String test) {
        return Categories.contains(test);
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
        if (!(other instanceof Category)) {
            return false;
        }

        Category otherPhone = (Category) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
