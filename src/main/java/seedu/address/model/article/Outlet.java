package seedu.address.model.article;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the news outlet an Article is published by
 */
public class Outlet {
    public static final String MESSAGE_CONSTRAINTS = "Outlet names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String outletName;


    /**
     * Constructs a {@code outletName}.
     *
     * @param outletName A valid outlet name.
     */
    public Outlet(String outletName) {
        requireNonNull(outletName);
        checkArgument(isValidOutletName(outletName), MESSAGE_CONSTRAINTS);
        this.outletName = outletName;
    }

    /**
     * Returns true if a given string is a valid outlet name.
     */
    public static boolean isValidOutletName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Outlet)) {
            return false;
        }

        Outlet otherOutlet = (Outlet) other;
        return outletName.equals(otherOutlet.outletName);
    }

    @Override
    public int hashCode() {
        return outletName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + outletName + ']';
    }
}
