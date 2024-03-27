package seedu.findvisor.model.person;

import java.util.function.Predicate;

import seedu.findvisor.commons.util.StringUtil;
import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A predicate for evaluating if a {@link Person}'s email contains (case-insensitive) a given keyword.
 * This is used to filter for persons based on their email attribute.
 */
public class PersonEmailPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs an {@code EmailContainsKeywordPredicate} with the specified keyword.
     *
     * @param keyword The keyword to be matched against the person's email. The match is case-insensitive.
     */
    public PersonEmailPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getEmail().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonEmailPredicate)) {
            return false;
        }

        PersonEmailPredicate otherEmailContainsKeywordsPredicate = (PersonEmailPredicate) other;
        return keyword.equals(otherEmailContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("email", keyword).toString();
    }
}
