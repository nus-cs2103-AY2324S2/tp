package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company Contact's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContactName(String)}
 */
public class ContactName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String contactName;

    /**
     * Constructs a {@code ContactName}.
     *
     * @param contactName A valid contactName.
     */
    public ContactName(String contactName) {
        requireNonNull(contactName);
        checkArgument(isValidContactName(contactName), MESSAGE_CONSTRAINTS);
        this.contactName = contactName;
    }

    /**
     * Returns true if a given string is a valid contactName.
     */
    public static boolean isValidContactName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return contactName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactName)) {
            return false;
        }

        ContactName otherName = (ContactName) other;
        return contactName.equals(otherName.contactName);
    }

    @Override
    public int hashCode() {
        return contactName.hashCode();
    }
}
