package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Subject {
    public static String MESSAGE_CONSTANTS = "Subject should be string of at least of length of 1";

    public String value;

    public Subject(String value) {
        requireNonNull(value);
        checkArgument(isValidSubject(value), MESSAGE_CONSTANTS);
        this.value = value;
    }

    public static boolean isValidSubject(String test) {
        return !test.trim().isEmpty();
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
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherPhone = (Subject) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
