package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's assigned instrument in the address book.
 * Guarantees: immutable; is always valid
 */
public class Instrument {
    public static final String MESSAGE_CONSTRAINTS = "Instrument name should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public final String value;

    /**
     * Constructs a {@code Instrument}.
     *
     * @param instrument A valid instrument name.
     */
    public Instrument(String instrument) {
        requireNonNull(instrument);
        checkArgument(isValidInstrument(instrument), MESSAGE_CONSTRAINTS);
        this.value = instrument;
    }

    /**
     * Returns true if a given string is a valid instrument.
     */
    public static boolean isValidInstrument(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Instrument // instanceof handles nulls
                && value.equals(((Instrument) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
