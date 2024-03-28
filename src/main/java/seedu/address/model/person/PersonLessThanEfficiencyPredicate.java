package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
/**
 * Tests that a {@code Person}'s {@code Efficiency} less than efficiency threshold given.
 */
public class PersonLessThanEfficiencyPredicate implements Predicate<Person> {
    private final Integer threshold;

    public PersonLessThanEfficiencyPredicate(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean test(Person person) {
        int effi = Integer.parseInt(person.getEfficiency().toString());
        return effi <= this.threshold;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonLessThanEfficiencyPredicate)) {
            return false;
        }

        PersonLessThanEfficiencyPredicate otherPersonLessThanEfficiency = (PersonLessThanEfficiencyPredicate) other;
        return threshold.equals(otherPersonLessThanEfficiency.threshold);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("threshold", threshold).toString();
    }
}
