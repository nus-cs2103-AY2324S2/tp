package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AllergiesTest {

    /* commented as Allergies can be null, and doesn't have regex
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Allergies(null));
    }

    @Test
    public void constructor_invalidAllergies_throwsIllegalArgumentException() {
        String invalidAllergies = "";
        assertThrows(IllegalArgumentException.class, () -> new Allergies(invalidAllergies));
    }
    */

    @Test
    public void equals() {
        Allergies allergies = new Allergies("Valid Allergy");

        // same values -> returns true
        assertTrue(allergies.equals(new Allergies("Valid Allergy")));

        // same object -> returns true
        assertTrue(allergies.equals(allergies));

        // null -> returns false
        assertFalse(allergies.equals(null));

        // different types -> returns false
        assertFalse(allergies.equals(5.0f));

        // different values -> returns false
        assertFalse(allergies.equals(new Allergies("Other Valid Allergy")));
    }
}
