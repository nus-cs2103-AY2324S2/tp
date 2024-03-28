package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Module code in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {


    public static final String MESSAGE_CONSTRAINTS =
            "Module code should contain 2-4 capital letters followed by 4 digits long and at most 1 capitalised suffix";
    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,4}\\d{4}[a-zA-Z]{0,1}";

    public final String value;

    /**
     * Constructs a {@code Module}.
     *
     * @param module A valid module code.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        value = module.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid module code.
     */


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
        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return value.equals(otherModule.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
