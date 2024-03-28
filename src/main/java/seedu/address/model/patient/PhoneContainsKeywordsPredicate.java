package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
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

    /**
     * Checks if the current object is equal to the given object.
     *
     * @param other The object to compare against
     * @return True if the objects are equal, false otherwise
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneContainsKeywordsPredicate)) {
            return false;
        }

        PhoneContainsKeywordsPredicate otherPhoneContainsKeywordsPredicate = (PhoneContainsKeywordsPredicate) other;
        return keywords.equals(otherPhoneContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

