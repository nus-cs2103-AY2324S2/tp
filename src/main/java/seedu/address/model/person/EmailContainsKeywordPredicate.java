package seedu.address.model.person;


import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Email} matches a part of the keywords given.
 */
public class EmailContainsKeywordPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public EmailContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(person.getEmail().value, keyword));
    }

    // TODO: Override toString and equals
}
