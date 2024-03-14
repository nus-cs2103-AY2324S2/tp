package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class IsSamePersonPredicate implements Predicate<Person> {
    private final Person selectedPerson;

    public IsSamePersonPredicate(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    @Override
    public boolean test(Person person) {
        return this.selectedPerson == person;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IsSamePersonPredicate)) {
            return false;
        }

        IsSamePersonPredicate otherisSamePersonPredicate = (IsSamePersonPredicate) other;
        return selectedPerson == otherisSamePersonPredicate.selectedPerson;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("selected person", selectedPerson).toString();
    }

}
