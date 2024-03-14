package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s year joined matches the given year.
 */
public class YearJoinedEqualsPredicate implements Predicate<Person> {
    private final String year;

    public YearJoinedEqualsPredicate(String year) {
        this.year = year;
    }

    @Override
    public boolean test(Person person) {
        return person.getYearJoined().toString().equals(year);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof YearJoinedEqualsPredicate)) {
            return false;
        }

        YearJoinedEqualsPredicate otherYearJoinedEqualsPredicate = (YearJoinedEqualsPredicate) other;
        return year.equals(otherYearJoinedEqualsPredicate.year);
    }
}
