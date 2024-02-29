package seedu.address.model.person.note;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Note should not be blank";

    public final String description;

    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValid(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValid(String test) {
        return !test.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Description that = (Description) o;

        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    @Override
    public String toString() {
        return description;
    }
}
