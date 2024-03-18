package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
/**
 * Represents a patient's ward address in the address book.
 */
public class Ward {
    public static final String MESSAGE_CONSTRAINTS = "Ward addresses can take any values, and it should not be blank";

    public final String value;

    /**
    * Constructs a {@code Ward}.
    *
    * @param value A valid ward address.
    */
    public Ward(String value) {
        requireNonNull(value);
        this.value = value;
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

        if (!(other instanceof Ward)) {
            return false;
        }

        Ward otherWard = (Ward) other;
        return value.equals(otherWard.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
