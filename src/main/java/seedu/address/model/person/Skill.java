package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Supplier's product in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSkill(String)}
 */
public class Skill {
    public static final String MESSAGE_CONSTRAINTS = "Skill can take any values, and it should not be blank";

    /*
     * The first character of the skill must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs an {@code Skill}.
     *
     * @param value A valid skill.
     */
    public Skill(String value) {
        requireNonNull(value);
        checkArgument(isValidSkill(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid product.
     */
    public static boolean isValidSkill(String test) {
        return test.matches(VALIDATION_REGEX);
    }

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
        if (!(other instanceof Skill)) {
            return false;
        }

        Skill otherSkill = (Skill) other;
        return value.equals(otherSkill.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
