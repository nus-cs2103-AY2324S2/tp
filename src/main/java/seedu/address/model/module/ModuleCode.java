package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)}
 */
public class ModuleCode {
    public static final String MESSAGE_CONSTRAINTS =
            "Module code %s should follow the module format, and it should not be blank";

    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,4}[0-9]{4}[a-zA-Z0-9]{0,5}";
    private final String code;


    /**
     * Constructs a {@code ModuleCode}.
     * @param code
     */
    public ModuleCode(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), String.format(MESSAGE_CONSTRAINTS, code));
        this.code = code;
    }

    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModuleCode code1 = (ModuleCode) o;

        return code.equals(code1.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
