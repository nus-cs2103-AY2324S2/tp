package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Person}'s {@code Name} and {@code Tag} matches any of the keywords given.
 */
public class NameAndTagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> tagKeywords;

    /**
     * Constructor for NameAndTagContainsKeywordsPredicate.
     * @param nameKeywords List of name keywords to search for.
     * @param tagKeywords List of tag keywords to search for.
     */
    public NameAndTagContainsKeywordsPredicate(List<String> nameKeywords, List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        boolean matchesName = nameKeywords.stream()
                .allMatch(keyword -> StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword));
        boolean matchesTags = tagKeywords.stream()
                .allMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsSubstringIgnoreCase(tag.tagName, keyword)));
        return matchesName && matchesTags;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameAndTagContainsKeywordsPredicate)) {
            return false;
        }

        NameAndTagContainsKeywordsPredicate that = (NameAndTagContainsKeywordsPredicate) other;
        return Objects.equals(nameKeywords, that.nameKeywords)
                && Objects.equals(tagKeywords, that.tagKeywords);
    }

    @Override
    public String toString() {
        return "NameAndTagContainsKeywordsPredicate{"
                + "nameKeywords=" + nameKeywords
                + ", tagKeywords=" + tagKeywords
                + '}';
    }
}
