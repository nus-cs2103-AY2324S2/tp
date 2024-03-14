package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A predicate for evaluating if a {@link Person}'s email equals (case-insensitive) a given keyword.
 * This is used to filter for persons based on their email attribute.
 */
public class EmailEqualsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs an {@code EmailEqualsKeywordPredicate} with the specified keyword.
     *
     * @param keyword The keyword to be matched against the person's email. The match is case-insensitive.
     */
    public EmailEqualsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equalsIgnoreCase(person.getEmail().value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailEqualsKeywordPredicate)) {
            return false;
        }

        EmailEqualsKeywordPredicate otherEmailContainsKeywordsPredicate = (EmailEqualsKeywordPredicate) other;
        return keyword.equals(otherEmailContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("email", keyword).toString();
    }
}
