package seedu.address.model.internship;

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

    @Override
    public boolean test(Internship internship) {
        boolean foundInCompanyName = companyNameKeywords.stream()
                .map(set -> StringUtil.containsWordIgnoreCase(String.join(" ", set),
                        internship.getCompanyName().companyName))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        // if no keywords, return isMatchAll. For matching all predicates, no keywords have to be true
        boolean foundInContactName = contactNameKeywords.stream()
                .map(set -> StringUtil.containsWordIgnoreCase(String.join(" ", set),
                        internship.getContactName().contactName))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInLocation = locationKeywords.stream()
                .map(set -> StringUtil.containsWordIgnoreCase(String.join(" ", set),
                        internship.getLocation().toString()))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInStatus = statusKeywords.stream()
                .map(set -> StringUtil.containsWordIgnoreCase(String.join(" ", set),
                        internship.getApplicationStatus().toString()))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInDescription = descriptionKeywords.stream()
                .map(set -> StringUtil.containsWordIgnoreCase(String.join(" ", set),
                        internship.getDescription().description))
                .reduce((a, b) -> a || b).orElse(isMatchAll);
        boolean foundInRole = roleKeywords.stream()
                .map(set -> StringUtil.containsWordIgnoreCase(String.join(" ", set),
                        internship.getRole().role))
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
                && this.roleKeywords.equals(otherInternshipPredicate.roleKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add(" companyNamekeywords", companyNameKeywords)
                .add(" contactNameKeywords", contactNameKeywords)
                .add(" locationKeywords", locationKeywords)
                .add(" statusKeywords", statusKeywords)
                .add(" descriptionKeywords", descriptionKeywords)
                .add(" roleKeywords", roleKeywords).toString();
    }

    protected Optional<Set<String>> getKeywords(String keywords) {
        if (keywords == null || keywords.isBlank()) {
            return Optional.empty();
        }
        String[] keywordsArr = keywords.split("\\s+");
        return Optional.of(Set.of(keywordsArr));
    }
}
