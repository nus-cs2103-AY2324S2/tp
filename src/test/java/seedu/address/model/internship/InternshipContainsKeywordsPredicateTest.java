package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywords = "first";
        String secondPredicateKeywords = "first second";

        InternshipContainsKeywordsPredicate firstPredicate =
                new InternshipContainsKeywordsPredicate(firstPredicateKeywords, null, null,
                null, null, null, false);
        InternshipContainsKeywordsPredicate secondPredicate =
                new InternshipContainsKeywordsPredicate(secondPredicateKeywords, null, null,
                null, null, null, false);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InternshipContainsKeywordsPredicate firstPredicateCopy =
                new InternshipContainsKeywordsPredicate(firstPredicateKeywords, null, null,
                        null, null, null, false);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyNameContainsKeywords_returnsTrue() {
        // One keyword
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate("Google", null, null,
                        null, null, null, false);
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Google").build()));

        // Multiple keywords
        predicate = new InternshipContainsKeywordsPredicate("Hewlett Packard", null, null,
                null, null, null, false);
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Hewlett Packard").build()));

        // Only one matching keyword
        predicate = new InternshipContainsKeywordsPredicate("Microsoft Google", null, null,
                null, null, null, false);
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Microsoft Facebook").build()));

        // Mixed-case keywords
        predicate = new InternshipContainsKeywordsPredicate("MicrOSoFt GOOgle", null, null,
                null, null, null, false);
        assertTrue(predicate.test(new InternshipBuilder().withCompanyName("Microsoft Google").build()));
    }
    @Test
    public void toStringMethod() {
        String companyNameKeywords = "Google";
        String contactNameKeywords = "John";
        String locationKeywords = "Singapore";
        String statusKeywords = "accepted";
        String descriptionKeywords = "AI";
        String roleKeywords = "developer";
        boolean isMatchAll = false;
        InternshipContainsKeywordsPredicate predicate = new InternshipContainsKeywordsPredicate(
                companyNameKeywords, contactNameKeywords, locationKeywords,
                statusKeywords, descriptionKeywords, roleKeywords, isMatchAll);

        String expected = InternshipContainsKeywordsPredicate.class.getCanonicalName() + "{"
                + " companyNameKeywords=[" + companyNameKeywords + "], "
                + " contactNameKeywords=[" + contactNameKeywords + "], "
                + " locationKeywords=[" + locationKeywords + "], "
                + " statusKeywords=[" + statusKeywords + "], "
                + " descriptionKeywords=[" + descriptionKeywords + "], "
                + " roleKeywords=[" + roleKeywords + "], "
                + " isMatchAll=" + isMatchAll + "}";
        assertEquals(expected, predicate.toString());
    }
}
