package seedu.address.model.client;
import seedu.address.commons.util.StringUtil;
import java.util.function.Predicate;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

public class TagContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return client.getTags().stream()
                .map(tag -> tag.getTagName())
                .anyMatch(tagName -> keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }
        TagContainsKeywordsPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return keywords.equals(otherTagContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}