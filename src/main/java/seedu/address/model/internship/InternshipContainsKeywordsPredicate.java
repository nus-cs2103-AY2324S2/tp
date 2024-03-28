package seedu.address.model.internship;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s fields matches any of the keywords given.
 */
public class InternshipContainsKeywordsPredicate implements Predicate<Internship> {
    private final boolean isMatchAll;
    private final Optional<Set<String>> companyNameKeywords;
    private final Optional<Set<String>> contactNameKeywords;
    private final Optional<Set<String>> locationKeywords;
    private final Optional<Set<String>> statusKeywords;
    private final Optional<Set<String>> descriptionKeywords;
    private final Optional<Set<String>> roleKeywords;

    /**
     * Creates a predicate that checks if an internship's fields contain any of the keywords specified for that field.
     * Additionally, the isMatchAll parameter specifies if all fields and keywords must be matched.
     *
     * @param companyNames  A string of company names separated by whitespace
     * @param contactNames  A string of contact names separated by whitespace
     * @param locations     A string of locations separated by whitespace
     * @param statuses      A string of statuses separated by whitespace
     * @param descriptions  A string of descriptions separated by whitespace
     * @param roles         A string of roles separated by whitespace
     * @param isMatchAll    A boolean to indicate if all keywords must be matched
     */
    public InternshipContainsKeywordsPredicate(String companyNames, String contactNames, String locations,
                                               String statuses, String descriptions, String roles, boolean isMatchAll) {
        this.companyNameKeywords = getKeywords(companyNames);
        this.contactNameKeywords = getKeywords(contactNames);
        this.locationKeywords = getKeywords(locations);
        this.statusKeywords = getKeywords(statuses);
        this.descriptionKeywords = getKeywords(descriptions);
        this.roleKeywords = getKeywords(roles);
        this.isMatchAll = isMatchAll;
    }

    /**
     * Tests if the given internship contains any of the keywords specified for all fields (when isMatchAll is true) or
     * any of the keywords specified for any field (when isMatchAll is false).
     */
    @Override
    public boolean test(Internship internship) {
        boolean foundInCompanyName = companyNameKeywords.stream()
                .map(set -> set.stream().anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(internship.getCompanyName().companyName, keyword)))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInContactName = contactNameKeywords.stream()
                .map(set -> set.stream().anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(internship.getContactName().contactName, keyword)))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInLocation = locationKeywords.stream()
                .map(set -> set.stream().anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(internship.getLocation().toString(), keyword)))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInStatus = statusKeywords.stream()
                .map(set -> set.stream().anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(internship.getApplicationStatus().toString(), keyword)))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInDescription = descriptionKeywords.stream()
                .map(set -> set.stream().anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(internship.getDescription().description, keyword)))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInRole = roleKeywords.stream()
                .map(set -> set.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        internship.getRole().role, keyword)))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        if (isMatchAll) {
            return foundInCompanyName && foundInContactName && foundInLocation
                    && foundInStatus && foundInDescription && foundInRole;
        } else { // match any
            return foundInCompanyName || foundInContactName || foundInLocation
                    || foundInStatus || foundInDescription || foundInRole;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipContainsKeywordsPredicate)) {
            return false;
        }

        InternshipContainsKeywordsPredicate otherInternshipPredicate =
                (InternshipContainsKeywordsPredicate) other;
        return this.companyNameKeywords.equals(otherInternshipPredicate.companyNameKeywords)
                && this.contactNameKeywords.equals(otherInternshipPredicate.contactNameKeywords)
                && this.locationKeywords.equals(otherInternshipPredicate.locationKeywords)
                && this.statusKeywords.equals(otherInternshipPredicate.statusKeywords)
                && this.descriptionKeywords.equals(otherInternshipPredicate.descriptionKeywords)
                && this.roleKeywords.equals(otherInternshipPredicate.roleKeywords)
                && this.isMatchAll == otherInternshipPredicate.isMatchAll;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add(" companyNameKeywords", companyNameKeywords.orElse(Collections.singleton("")))
                .add(" contactNameKeywords", contactNameKeywords.orElse(Collections.singleton("")))
                .add(" locationKeywords", locationKeywords.orElse(Collections.singleton("")))
                .add(" statusKeywords", statusKeywords.orElse(Collections.singleton("")))
                .add(" descriptionKeywords", descriptionKeywords.orElse(Collections.singleton("")))
                .add(" roleKeywords", roleKeywords.orElse(Collections.singleton("")))
                .add(" isMatchAll", isMatchAll).toString();
    }

    /**
     * @param keywords A string of keywords separated by whitespace
     * @return An Optional containing a set of keywords if the input is not null or empty, else an empty Optional
     */
    protected Optional<Set<String>> getKeywords(String keywords) {
        if (keywords == null || keywords.isBlank()) {
            return Optional.empty();
        }
        String[] keywordsArr = keywords.split("\\s+");
        return Optional.of(Set.of(keywordsArr));
    }
}
