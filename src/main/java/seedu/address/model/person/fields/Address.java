package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address implements Field {

    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    private static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[^\\s].*";

    private final String address;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValid(address), MESSAGE_CONSTRAINTS);
        this.address = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    private static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code address} is invalid.
     */
    public static Address of(String address) throws IllegalArgumentException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        return new Address(trimmedAddress);
    }

    @Override
    @JsonValue
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Address)) {
            return false;
        }

        Address otherAddress = (Address) other;
        return address.equals(otherAddress.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

}
