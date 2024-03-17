package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> tagKeywords;

    public TagsContainsKeywordsPredicate(List<String> tagKeywords) {
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        return tagKeywords.stream()
                .allMatch(keyword ->
                        person.getTags().stream()
                                .anyMatch(tag ->
                                        StringUtil.containsWordIgnoreCase(tag.tagName, keyword)
                                )
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainsKeywordsPredicate)) {
            return false;
        }

        TagsContainsKeywordsPredicate otherContainsTagKeywordsPredicate = (TagsContainsKeywordsPredicate) other;
        return tagKeywords.equals(otherContainsTagKeywordsPredicate.tagKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tagKeywords", tagKeywords).toString();
    }
}

