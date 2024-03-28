package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's company name in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Company {
    public final String value;

    /**
     * Constructs a {@code Company}.
     *
     * @param companyName A valid name.
     */
    public Company(String companyName) {
        requireNonNull(companyName);
        value = companyName;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                && value.equals(((Company) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
