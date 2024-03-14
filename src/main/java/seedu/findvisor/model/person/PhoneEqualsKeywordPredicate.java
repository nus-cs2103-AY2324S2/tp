package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.ToStringBuilder;

public class PhoneEqualsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

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
