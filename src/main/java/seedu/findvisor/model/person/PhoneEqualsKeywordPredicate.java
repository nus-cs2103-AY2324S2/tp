package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A predicate for evaluating if a {@link Person}'s phone equals a given keyword.
 * This is used to filter for persons based on their phone attribute.
 */
public class PhoneEqualsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs an {@code PhoneEqualsKeywordPredicate} with the specified keyword.
     *
     * @param keyword The keyword to be used to lookup against the person's phone.
     */
    public PhoneEqualsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals(person.getPhone().value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneEqualsKeywordPredicate)) {
            return false;
        }

        PhoneEqualsKeywordPredicate otherPhoneEqualsKeywordsPredicate = (PhoneEqualsKeywordPredicate) other;
        return keyword.equals(otherPhoneEqualsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("phone", keyword).toString();
    }
}
