package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable;
 */
public class Sex {
    public static final String MESSAGE_CONSTRAINTS = "Sex should be either F or M.";
    private final SexType sex;
    /**
     * Constructs a Sex instance.
     */
    public Sex(SexType sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return this.sex.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Sex)) {
            return false;
        }

        Sex otherSex = (Sex) other;
        return sex.equals(otherSex.sex);
    }

    @Override
    public int hashCode() {
        return sex.hashCode();
    }

    /**
     * Represents sex of a person.
     */
    public enum SexType {F, M}
}
