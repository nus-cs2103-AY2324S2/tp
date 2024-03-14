package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HobbyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hobby(null));
    }

    @Test
    public void constructor_invalidHobby_throwsIllegalArgumentException() {
        String invalidHobby = "";
        assertThrows(IllegalArgumentException.class, () -> new Hobby(invalidHobby));
    }

    @Test
    public void isValidFoodPreference() {
        // null food preference
        assertThrows(NullPointerException.class, () -> FoodPreference.isValidFood(null));

        // invalid hobby
        assertFalse(Hobby.isValidHobby("")); // empty string
        assertFalse(Hobby.isValidHobby(" ")); // spaces only
        assertFalse(Hobby.isValidHobby("91")); // non-alphabets
        assertFalse(Hobby.isValidHobby("12hiking")); // digits within alphabets

        // valid hobby
        assertTrue(Hobby.isValidHobby("rock climbing")); // with spaces
        assertTrue(Hobby.isValidHobby("hiking")); // without spaces
    }

    @Test
    public void equals() {
        Hobby hobby = new Hobby("hiking");

        // same values -> returns true
        assertTrue(hobby.equals(new Hobby("hobby")));

        // same object -> returns true
        assertTrue(hobby.equals(hobby));

        // null -> returns false
        assertFalse(hobby.equals(null));

        // different types -> returns false
        assertFalse(hobby.equals(5.0f));

        // different values -> returns false
        assertFalse(hobby.equals(new Hobby("swimming")));
    }
}
