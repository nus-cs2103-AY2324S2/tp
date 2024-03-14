package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FoodPreferenceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FoodPreference(null));
    }

    @Test
    public void constructor_invalidFoodPreference_throwsIllegalArgumentException() {
        String invalidFoodPreference = "";
        assertThrows(IllegalArgumentException.class, () -> new FoodPreference(invalidFoodPreference));
    }

    @Test
    public void isValidFoodPreference() {
        // null food preference
        assertThrows(NullPointerException.class, () -> FoodPreference.isValidFood(null));

        // invalid food preference
        assertFalse(FoodPreference.isValidFood("")); // empty string
        assertFalse(FoodPreference.isValidFood(" ")); // spaces onl

        // valid food preference
        assertTrue(FoodPreference.isValidFood("chicken rice")); // with spaces
        assertTrue(FoodPreference.isValidFood("pasta")); // without spaces
    }

    @Test
    public void equals() {
        FoodPreference foodPreference = new FoodPreference("pasta");

        // same values -> returns true
        assertTrue(foodPreference.equals(new FoodPreference("pasta")));

        // same object -> returns true
        assertTrue(foodPreference.equals(foodPreference));

        // null -> returns false
        assertFalse(foodPreference.equals(null));

        // different types -> returns false
        assertFalse(foodPreference.equals(5.0f));

        // different values -> returns false
        assertFalse(foodPreference.equals(new FoodPreference("spaghetti")));
    }
}
