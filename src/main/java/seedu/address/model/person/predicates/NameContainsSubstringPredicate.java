package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person.PersonAttribute;

/**
 * Tests that a {@code Person}'s {@code Name} contains a given substring.
 */
public class NameContainsSubstringPredicate extends SearchPredicate<String> {
    public NameContainsSubstringPredicate(String substring) {
        super(substring, PersonAttribute.NAME);
        requireNonNull(substring);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("substring", this.getSearchValue()).toString();
    }
}
