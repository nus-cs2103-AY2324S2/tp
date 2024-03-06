package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FundingStageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FundingStage(null));
    }

    @Test
    public void constructor_invalidFundingStage_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new FundingStage(invalidPhone));
    }

    @Test
    public void isValidFundingStage() {
        // null funding
        assertThrows(NullPointerException.class, () -> FundingStage.isValidFundingLevel(null));

        // invalid funding levels
        assertFalse(FundingStage.isValidFundingLevel("")); // empty string
        assertFalse(FundingStage.isValidFundingLevel(" ")); // spaces only
        assertFalse(FundingStage.isValidFundingLevel("AB")); // invalid funding level
        assertFalse(FundingStage.isValidFundingLevel("1")); // numbers
        assertFalse(FundingStage.isValidFundingLevel("D")); // invalid funding level

        // valid funding levels
        assertTrue(FundingStage.isValidFundingLevel("A"));
        assertTrue(FundingStage.isValidFundingLevel("B"));
        assertTrue(FundingStage.isValidFundingLevel("C"));
    }

    @Test
    public void equals() {
        FundingStage fundingStage = new FundingStage("A");

        // same values -> returns true
        assertTrue(fundingStage.equals(new FundingStage("A")));

        // same object -> returns true
        assertTrue(fundingStage.equals(fundingStage));

        // null -> returns false
        assertFalse(fundingStage.equals(null));

        // different types -> returns false
        assertFalse(fundingStage.equals(5.0f));

        // different values -> returns false
        assertFalse(fundingStage.equals(new FundingStage("B")));
    }
}
