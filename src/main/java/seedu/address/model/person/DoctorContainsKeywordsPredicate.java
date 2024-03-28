package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class DoctorContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public DoctorContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person doctor) {
        if (doctor.getType() != Type.DOCTOR) {
            return false;
        }

        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsSubstringIgnoreCase(doctor.getNric().nric, keyword)
                || StringUtil.containsSubstringIgnoreCase(doctor.getName().fullName, keyword)
                || StringUtil.containsSubstringIgnoreCase(doctor.getDoB().dateOfBirth.toString(), keyword)
                || StringUtil.containsSubstringIgnoreCase(doctor.getPhone().value, keyword)
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoctorContainsKeywordsPredicate)) {
            return false;
        }

        DoctorContainsKeywordsPredicate otherPredicate = (DoctorContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
