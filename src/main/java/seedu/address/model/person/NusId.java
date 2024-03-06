package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Nusid in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNusId(String)}
 */
public class NusId {

    public static final String MESSAGE_CONSTRAINTS = "NusID is of the form EXXXXXXX, and it should not be blank";

    /*
     * Ensures that the input nusid is of the form: EXXXXXXX
     */
    public static final String VALIDATION_REGEX = "^E\\d{7}$\n";

    public final String value;

    /**
     * Constructs an {@code Nusid}.
     *
     * @param nusid A valid nusid.
     */
    public NusId(String nusid) {
        requireNonNull(nusid);
        checkArgument(isValidNusId(nusid), MESSAGE_CONSTRAINTS);
        value = nusid;
    }

    /**
     * Returns true if a given string is a valid nusid.
     */
    public static boolean isValidNusId(String test) {
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

        if (!(other instanceof NusId)) {
            return false;
        }

        NusId otherNusid = (NusId) other;
        return value.equals(otherNusid.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
