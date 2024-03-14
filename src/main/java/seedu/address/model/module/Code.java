package seedu.address.model.module;

/**
 * Represents a Module's code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)}
 */
public class Code {
    private final String code;


    public Code(String code) {
        this.code = code;
    }

    // TODO(yadunut): Implement this method
    public static boolean isValidCode(String test) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getCode() {
        return code;
    }
}
