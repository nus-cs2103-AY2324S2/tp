package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
/**
 * Tests that a {@code Person}'s {@code Phone} matches a part of the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(patient.getPhone().toString(), keyword));
    }

    //TODO: Override toString and equals
}
