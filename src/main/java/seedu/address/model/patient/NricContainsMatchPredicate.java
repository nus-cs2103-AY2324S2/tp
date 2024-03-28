package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code Nric} matches the given Nric prefix
 */
public class NricContainsMatchPredicate implements Predicate<Patient> {
    private final String prefixToMatch;

    /**
     * Constructs an {@code NricContainsMatchPredicate}.
     *
     * @param prefixToMatch A non-null, non-empty value of nric to match
     */
    public NricContainsMatchPredicate(String prefixToMatch) {
        requireNonNull(prefixToMatch);
        this.prefixToMatch = prefixToMatch;
    }

    @Override
    public boolean test(Patient patient) {
        return StringUtil.startsWithWordIgnoreCase(patient.getNric().value, prefixToMatch);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NricContainsMatchPredicate)) {
            return false;
        }

        NricContainsMatchPredicate otherNricContainsMatchPredicate = (NricContainsMatchPredicate) other;
        return prefixToMatch.equals(otherNricContainsMatchPredicate.prefixToMatch);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("nricPrefixToMatch", prefixToMatch).toString();
    }
}
