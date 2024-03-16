package seedu.address.model.module;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents a Module's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
class Description {
    private final String value;

    @JsonCreator
    Description(String value) {
        this.value = value;
    }

    //    TODO(yadunut): Add validation for description
    public static boolean isValidDescription(String test) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getValue() {
        return value;
    }
}
