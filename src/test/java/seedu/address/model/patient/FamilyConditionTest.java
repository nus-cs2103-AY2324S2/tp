package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FamilyConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FamilyCondition(null));
    }

    @Test
    public void constructor_invalidFamilyCondition_throwsIllegalArgumentException() {
        String invalidFamilyCondition = "";
        assertThrows(IllegalArgumentException.class, () -> new FamilyCondition(invalidFamilyCondition));
    }

    @Test
    public void isValidFamilyCondition() {
        // null family condition
        assertThrows(NullPointerException.class, () -> FamilyCondition.isValidFamilyCondition(null));

        // invalid family condition
        assertFalse(FamilyCondition.isValidFamilyCondition("")); // empty string
        assertFalse(FamilyCondition.isValidFamilyCondition(" ")); // spaces only
        assertFalse(FamilyCondition.isValidFamilyCondition("91")); // non-alphabets
        assertFalse(FamilyCondition.isValidFamilyCondition("12hello")); // digits within alphabets

        // valid family condition
        assertTrue(FamilyCondition.isValidFamilyCondition("Wife not around")); // with spaces
        assertTrue(FamilyCondition.isValidFamilyCondition("Overseas")); // without spaces
    }

    @Test
    public void equals() {
        FamilyCondition familyCondition = new FamilyCondition("good relationship");

        // same values -> returns true
        assertTrue(familyCondition.equals(new FamilyCondition("good relationship")));

        // same object -> returns true
        assertTrue(familyCondition.equals(familyCondition));

        // null -> returns false
        assertFalse(familyCondition.equals(null));

        // different types -> returns false
        assertFalse(familyCondition.equals(5.0f));

        // different values -> returns false
        assertFalse(familyCondition.equals(new FoodPreference("son just met accident")));
    }
}
