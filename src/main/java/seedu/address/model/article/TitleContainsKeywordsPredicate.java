package seedu.address.model.article;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Article> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Article article) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(article.getTitle(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TitleContainsKeywordsPredicate)) {
            return false;
        }

        TitleContainsKeywordsPredicate otherTitleContainsKeywordsPredicate = (TitleContainsKeywordsPredicate) other;
        return keywords.equals(otherTitleContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
