package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.StringUtil;
import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A predicate for evaluating if a {@link Person}'s address contains (case-insensitive) a given keyword.
 * This is used to filter for persons based on their address attribute.
 */
public class PersonAddressPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs an {@code AddressContainsKeywordPredicate} with the specified keyword.
     *
     * @param keyword The keyword to be matched against the person's address. The match is case-insensitive.
     */
    public PersonAddressPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getAddress().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonAddressPredicate)) {
            return false;
        }

        PersonAddressPredicate otherAddressContainsKeywordsPredicate = (PersonAddressPredicate) other;
        return keyword.equals(otherAddressContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("address", keyword).toString();
    }
}
