package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
/**
 * Represents a patient's ward address in the address book.
 */
public class Ward {
    public static final String MESSAGE_CONSTRAINTS = "Ward addresses can take any values, and it should not be blank";

    public final String ward;

    /**
    * Constructs a {@code Ward}.
    *
    * @param ward A valid ward address.
    */
    public Ward(String ward) {
        requireNonNull(ward);
        this.ward = ward;
    }

    @Override
    public String toString() {
        return ward;
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
        return ward.equals(otherWard.ward);
    }

    @Override
    public int hashCode() {
        return ward.hashCode();
    }
}
