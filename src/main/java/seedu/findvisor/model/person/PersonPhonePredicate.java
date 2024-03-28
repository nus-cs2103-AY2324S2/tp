package seedu.findvisor.model.person;

import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A predicate for evaluating if a {@link Person}'s phone contains a given keyword.
 * This is used to filter for persons based on their phone attribute.
 */
public class PersonPhonePredicate implements PersonPredicate {
    private final String keyword;

    /**
     * Constructs an {@code PhoneEqualsKeywordPredicate} with the specified keyword.
     *
     * @param keyword The keyword to be used to lookup against the person's phone.
     */
    public PersonPhonePredicate(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the description of this predicate, indicating the phone keyword criteria.
     *
     * @return A string describing the predicate
     */
    public String getPredicateDescription() {
        return String.format("Phone = \"%1$s\"", keyword);
    }

    @Override
    public boolean test(Person person) {
        String personPhoneString = person.getPhone().value;
        return personPhoneString.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonPhonePredicate)) {
            return false;
        }

        PersonPhonePredicate otherPhoneEqualsKeywordsPredicate = (PersonPhonePredicate) other;
        return keyword.equals(otherPhoneEqualsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("phone", keyword).toString();
    }
}
