package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {
    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only be in the format of e/E follow by 7 digits from [0-9].\n"
            + "The local-part should not consists any special characters."
            + "e.g." + SPECIAL_CHARACTERS + "\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels.\n"
            + "The domain name must:\n"
            + "    - end with a domain label u.nus.edu\n";

    //alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "[eE]\\d{7}";

//    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
//            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
//    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "u.nus.edu";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

//    public static final String MESSAGE_CONSTRAINTS = "The email should be in the format: e0123456@u.nus.edu\n"
//            + "The first character is case insensitive, follow by 7 digits, and @u.nus.edu";
    //public static final String VALIDATION_REGEX = "[eE]\\d{7}@u.nus.edu";

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
