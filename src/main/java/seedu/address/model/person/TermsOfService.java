package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Terms of Service in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTermsOfService(String)}
 */
public class TermsOfService {

    public static final String MESSAGE_CONSTRAINTS =
            "Terms of Service should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the terms of service must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String terms;

    /**
     * Constructs a {@code TermsOfService}.
     *
     * @param terms A valid terms of service.
     */
    public TermsOfService(String terms) {
        requireNonNull(terms);
        checkArgument(isValidTermsOfService(terms), MESSAGE_CONSTRAINTS);
        this.terms = terms;
    }

    /**
     * Returns true if a given string is a valid terms of service.
     */
    public static boolean isValidTermsOfService(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return terms;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TermsOfService)) {
            return false;
        }

        TermsOfService otherTermsOfService = (TermsOfService) other;
        return terms.equals(otherTermsOfService.terms);
    }

    @Override
    public int hashCode() {
        return terms.hashCode();
    }

}
