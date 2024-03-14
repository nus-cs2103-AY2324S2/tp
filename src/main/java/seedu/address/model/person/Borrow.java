package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Borrow {
    public final String value;

    public Borrow(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Borrow // instanceof handles nulls
                && value.equals(((Borrow) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
