package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Star} is within the bounds specified..
 */
public class StarWithinBoundsPredicate implements Predicate<Person> {
    private final String operator;
    private final Integer value;

    /**
     * Initialises the operator and value that defines the bound.
     *
     * @param operator
     * @param value
     */
    public StarWithinBoundsPredicate(String operator, Integer value) {
        this.operator = operator;
        this.value = value;
    }

    @Override
    public boolean test(Person person) {
        switch (operator) {
        case "<":
            return person.getStar().numOfStars < value;
        case "<=":
            return person.getStar().numOfStars <= value;
        case ">":
            return person.getStar().numOfStars > value;
        case ">=":
            return person.getStar().numOfStars >= value;
        case "=":
            return person.getStar().numOfStars.equals(value);
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StarWithinBoundsPredicate)) {
            return false;
        }

        StarWithinBoundsPredicate otherStarWithinBoundsPredicate = (StarWithinBoundsPredicate) other;
        return value.equals(otherStarWithinBoundsPredicate.value)
                && operator.equals(otherStarWithinBoundsPredicate.operator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("value", value).toString();
    }
}

