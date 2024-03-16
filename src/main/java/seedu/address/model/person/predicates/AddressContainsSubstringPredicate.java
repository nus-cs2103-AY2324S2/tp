package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person.PersonAttribute;

/**
 * Tests that a {@code Person}'s {@code Address} contains a given substring.
 */
public class AddressContainsSubstringPredicate extends SearchPredicate<String> {
    public AddressContainsSubstringPredicate(String substring) {
        super(substring, PersonAttribute.ADDRESS);
        requireNonNull(substring);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("substring", this.getSearchValue()).toString();
    }
}
