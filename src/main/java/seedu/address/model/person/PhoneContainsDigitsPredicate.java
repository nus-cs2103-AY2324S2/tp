package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PhoneContainsDigitsPredicate implements Predicate<Person> {
    private final List<String> phones;

    public PhoneContainsDigitsPredicate(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public boolean test(Person person) {
        return phones.stream()
                .anyMatch(phone -> StringUtil.containsWordIgnoreCase(person.getPhone().value, phone));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneContainsDigitsPredicate)) {
            return false;
        }

        PhoneContainsDigitsPredicate otherNameContainsKeywordsPredicate = (PhoneContainsDigitsPredicate) other;
        return phones.equals(otherNameContainsKeywordsPredicate.phones);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("phones", phones).toString();
    }
}
