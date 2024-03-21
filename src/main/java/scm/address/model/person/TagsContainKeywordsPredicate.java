package scm.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import scm.address.commons.util.StringUtil;
import scm.address.commons.util.ToStringBuilder;

/**
 * Tests that any of a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return true;
        }

        if (person.getTags().isEmpty()) {
            return false;
        }

        return person.getTags().stream()
                .anyMatch(tag -> keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainKeywordsPredicate)) {
            return false;
        }

        TagsContainKeywordsPredicate otherTagsContainKeywordsPredicate = (TagsContainKeywordsPredicate) other;
        return keywords.equals(otherTagsContainKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

