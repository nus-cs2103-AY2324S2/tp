package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.Model;

/**
 * Represents a Person's ID in the address book.
 * Guarantees: immutable;
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should only contain numbers and it must be between 100000 to 999999 inclusive.";

    public final int value;

    /**
     * Constructs a {@code Id}.
     *
     * @param model The model to get the last ID from.
     * @param yearJoined A valid year joined.
     */
    public Id(Model model, YearJoined yearJoined) {
        requireNonNull(yearJoined);
        int lastId = model.getLastIdOnYear(yearJoined);
        int currentId = lastId + 1;
        checkArgument(isValidId(currentId), MESSAGE_CONSTRAINTS);
        value = currentId;
    }

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid ID.
     */
    public Id(int id) {
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given integer is a valid ID.
     */
    public static boolean isValidId(int test) {
        return test > 100000 && test < 1000000;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return value == otherId.value;
    }
}
