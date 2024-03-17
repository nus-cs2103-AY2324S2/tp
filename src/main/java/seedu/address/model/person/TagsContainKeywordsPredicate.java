package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

public class TagsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> tagKeywords;

    public TagsContainKeywordsPredicate(List<String> tagKeywords) {
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
        if (!(other instanceof TagsContainKeywordsPredicate)) {
            return false;
        }

        TagsContainKeywordsPredicate otherContainsTagKeywordsPredicate = (TagsContainKeywordsPredicate) other;
        return tagKeywords.equals(otherContainsTagKeywordsPredicate.tagKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tagKeywords", tagKeywords).toString();
    }
}

