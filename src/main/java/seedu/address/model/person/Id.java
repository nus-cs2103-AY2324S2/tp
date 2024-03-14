package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should only contain alphanumeric characters, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+$";
    public final String Id;
    public Id(String Id) {
        requireNonNull(Id);
        checkArgument(isValidId(Id), MESSAGE_CONSTRAINTS);
        this.Id = Id;
    }
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.Id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherName = (Id) other;
        return this.Id.equals(otherName.Id);
    }

    @Override
    public int hashCode() {
        return this.Id.hashCode();
    }


}
