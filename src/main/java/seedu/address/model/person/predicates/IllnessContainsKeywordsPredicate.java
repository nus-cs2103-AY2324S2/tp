package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Illness} matches any of the keywords given.
 */
public class IllnessContainsKeywordsPredicate extends ContainsKeywordsPredicate {
    public IllnessContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        String illnessString = person.getIllnesses().toString().replaceAll("[\\[\\],]", "");
        System.out.println(illnessString);

        return super.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(illnessString, keyword));
    }
}
