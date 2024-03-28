package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code DrugAllergy} matches any of the keywords given.
 */
public class DrugAllergyContainsKeywordsPredicate extends ContainsKeywordsPredicate {
    public DrugAllergyContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getDrugAllergy().toString(), keyword));
    }
}
