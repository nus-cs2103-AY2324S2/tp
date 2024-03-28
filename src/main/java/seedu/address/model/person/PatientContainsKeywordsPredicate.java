package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class PatientContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PatientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person patient) {
        if (patient.getType() != Type.PATIENT) {
            return false;
        }

        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsSubstringIgnoreCase(patient.getNric().nric, keyword)
                || StringUtil.containsSubstringIgnoreCase(patient.getName().fullName, keyword)
                || StringUtil.containsSubstringIgnoreCase(patient.getDoB().dateOfBirth.toString(), keyword)
                || StringUtil.containsSubstringIgnoreCase(patient.getPhone().value, keyword)
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientContainsKeywordsPredicate)) {
            return false;
        }

        PatientContainsKeywordsPredicate otherPredicate = (PatientContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
