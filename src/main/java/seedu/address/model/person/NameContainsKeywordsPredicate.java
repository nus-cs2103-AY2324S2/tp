package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;


    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Predicate<String> personName = keyword ->
                keyword.regionMatches(true, 0, person.getName().toString(),
                        0, keyword.length());
        Predicate<String> personNumber = keyword ->
                keyword.regionMatches(true, 0, person.getPhone().toString(),
                        0, keyword.length());
        Predicate<String> personAddress = keyword ->
                keyword.regionMatches(true, 0, person.getAddress().toString(),
                        0, keyword.length());
        Predicate<String> personEmail = keyword ->
                keyword.regionMatches(true, 0, person.getEmail().toString(),
                        0, keyword.length());
        Predicate<String> personTag = keyword -> keyword.regionMatches(true, 0, person.getTags().stream()
                .map(tag -> tag.tagName).collect(Collectors.joining()), 0, keyword.length());
        Predicate<String> combinedCondition = personName.or(personNumber)
                .or(personAddress).or(personEmail).or(personTag);
        return keywords.stream()
                .anyMatch(combinedCondition);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
