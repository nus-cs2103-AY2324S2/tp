package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */
public class CompanyName {
    public static final String MESSAGE_CONSTRAINTS =
            "CompanyName should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * Matches one or more alphanumeric characters
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String companyName;

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param companyName A valid companyName.
     */
    public CompanyName(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompanyName(companyName), MESSAGE_CONSTRAINTS);
        this.companyName = companyName;
    }

    /**
     * Returns true if a given string is a valid companyName.
     */
    public static boolean isValidCompanyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return companyName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyName)) {
            return false;
        }

        CompanyName otherName = (CompanyName) other;
        return companyName.equals(otherName.companyName);
    }

    @Override
    public int hashCode() {
        return companyName.hashCode();
    }
}
