package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class StarsLessThanPredicate implements Predicate<Person> {
    private final Integer upperBound;

    public StarsLessThanPredicate(Integer upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public boolean test(Person person) {
        return person.getStar().numOfStars < upperBound;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StarsLessThanPredicate)) {
            return false;
        }

        StarsLessThanPredicate otherStarsLessThanPredicate = (StarsLessThanPredicate) other;
        return upperBound.equals(otherStarsLessThanPredicate.upperBound);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("upper bound: ", upperBound).toString();
    }
}

