package seedu.address.model.internship;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s fields matches any of the keywords given.
 */
public class InternshipContainsKeywordsPredicate implements Predicate<Internship> {
    private final boolean isMatchAll;
    private final Set<String> companyNameKeywords;
    private final Set<String> contactNameKeywords;
    private final Set<String> contactEmailKeywords;
    private final Set<String> contactPhoneKeywords;
    private final Set<String> locationKeywords;
    private final Set<String> statusKeywords;
    private final Set<String> descriptionKeywords;
    private final Set<String> roleKeywords;

    /**
     * Creates a predicate that checks if an internship's fields contain any of the keywords specified for that field.
     * Additionally, the isMatchAll parameter specifies if all fields and keywords must be matched.
     *
     * @param companyNames  A string of company names separated by whitespace
     * @param contactNames  A string of contact names separated by whitespace
     * @param contactEmails A string of contact emails separated by whitespace
     * @param contactPhones A string of contact phones separated by whitespace
     * @param locations     A string of locations separated by whitespace
     * @param statuses      A string of statuses separated by whitespace
     * @param descriptions  A string of descriptions separated by whitespace
     * @param roles         A string of roles separated by whitespace
     * @param isMatchAll    A boolean to indicate if all keywords must be matched
     */
    public InternshipContainsKeywordsPredicate(String companyNames, String contactNames, String contactEmails,
                                               String contactPhones, String locations, String statuses,
                                               String descriptions, String roles, boolean isMatchAll) {
        this.companyNameKeywords = getKeywords(companyNames);
        this.contactNameKeywords = getKeywords(contactNames);
        this.contactEmailKeywords = getKeywords(contactEmails);
        this.contactPhoneKeywords = getKeywords(contactPhones);
        this.locationKeywords = getKeywords(locations);
        this.statusKeywords = getKeywords(statuses);
        this.descriptionKeywords = getKeywords(descriptions);
        this.roleKeywords = getKeywords(roles);
        this.isMatchAll = isMatchAll;
    }

    @Override
    public boolean test(Internship internship) {
        boolean foundInCompanyName = companyNameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getCompanyName().companyName,
                        keyword));
        boolean foundInContactName = contactNameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getContactName().contactName,
                        keyword));
        boolean foundInContactEmail = contactEmailKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getContactEmail().value,
                        keyword));
        boolean foundInContactPhone = contactPhoneKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getContactNumber().value,
                        keyword));
        boolean foundInLocation = locationKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getLocation().toString(),
                        keyword));
        boolean foundInStatus = statusKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getApplicationStatus().toString(),
                        keyword));
        boolean foundInDescription = descriptionKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getDescription().description,
                        keyword));
        boolean foundInRole = roleKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getRole().role,
                        keyword));
        if (isMatchAll) {
            return foundInCompanyName && foundInContactName && foundInContactEmail && foundInContactPhone
                    && foundInLocation && foundInStatus && foundInDescription && foundInRole;
        } else { // match any
            return foundInCompanyName || foundInContactName || foundInContactEmail || foundInContactPhone
                    || foundInLocation || foundInStatus || foundInDescription || foundInRole;
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
                && this.contactEmailKeywords.equals(otherInternshipPredicate.contactEmailKeywords)
                && this.contactPhoneKeywords.equals(otherInternshipPredicate.contactPhoneKeywords)
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
                .add(" contactEmailKeywords", contactEmailKeywords)
                .add(" contactPhoneKeywords", contactPhoneKeywords)
                .add(" locationKeywords", locationKeywords)
                .add(" statusKeywords", statusKeywords)
                .add(" descriptionKeywords", descriptionKeywords)
                .add(" roleKeywords", roleKeywords).toString();
    }

    protected Set<String> getKeywords(String keywords) {
        return Set.of(keywords.split("\\s+"));
    }
}
