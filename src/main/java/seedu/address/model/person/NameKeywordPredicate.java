package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Details} matches any of the keywords given.
 */
public class NameKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public NameKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().fullName.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameKeywordPredicate)) {
            return false;
        }

        NameKeywordPredicate otherNameKeywordPredicate = (NameKeywordPredicate) other;
        return keyword.equals(otherNameKeywordPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keyword).toString();
    }
}
