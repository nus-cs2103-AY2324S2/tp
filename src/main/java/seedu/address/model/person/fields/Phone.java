package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone implements Field {

    private static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    private static final String VALIDATION_REGEX = "\\d{3,}";
    private final String phone;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValid(phone), MESSAGE_CONSTRAINTS);
        this.phone = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    private static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    @JsonValue
    public String toString() {
        return phone;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return phone.equals(otherPhone.phone);
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

}
