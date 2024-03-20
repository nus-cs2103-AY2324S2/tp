package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Tags} or {@code Assets} matches any of the keywords given.
 */
public class PersonMatchesKeywordPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public PersonMatchesKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                       .anyMatch(keyword -> {
                           boolean nameContainsKeyword =
                               StringUtil.containsWordIgnoreCase(person.getName().toString(), keyword);
                           boolean tagsContainKeyword = person.getTags()
                                   .stream()
                                   .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.get(), keyword));
                           boolean assetsContainKeyword = person.getAssets()
                                   .stream()
                                   .anyMatch(asset -> StringUtil.containsWordIgnoreCase(asset.get(), keyword));
                           return nameContainsKeyword || tagsContainKeyword || assetsContainKeyword;
                       });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonMatchesKeywordPredicate)) {
            return false;
        }

        PersonMatchesKeywordPredicate otherPersonMatchesKeywordPredicate = (PersonMatchesKeywordPredicate) other;
        return keywords.equals(otherPersonMatchesKeywordPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
