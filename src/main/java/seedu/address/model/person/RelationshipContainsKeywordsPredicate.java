package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class RelationshipContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public RelationshipContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RelationshipContainsKeywordsPredicate)) {
            return false;
        }

        RelationshipContainsKeywordsPredicate otherRelationshipContainsKeywordsPredicate = (RelationshipContainsKeywordsPredicate) other;
        return keywords.equals(otherRelationshipContainsKeywordsPredicate.keywords);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getRelationship().value, keyword));
    }
}
