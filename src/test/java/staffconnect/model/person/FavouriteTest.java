package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FavouriteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Favourite(null));
    }

    @Test
    public void constructor_invalidFavourite_throwsIllegalArgumentException() {
        String invalidFavourite = "";
        assertThrows(IllegalArgumentException.class, () -> new Favourite(invalidFavourite));
    }

    @Test
    public void isValidFavourite() {
        // null favourite
        assertThrows(NullPointerException.class, () -> Favourite.isValidFavourite(null));

        // invalid favourite
        assertFalse(Favourite.isValidFavourite("")); // empty string
        assertFalse(Favourite.isValidFavourite(" ")); // spaces only
        assertFalse(Favourite.isValidFavourite("a")); // invalid string

        // valid favourite
        assertTrue(Favourite.isValidFavourite("Favourite"));
        assertTrue(Favourite.isValidFavourite("Not favourite"));
    }

    @Test
    void hasFavourite() {
        assertFalse(new Favourite("Not favourite").hasFavourite());
        assertTrue(new Favourite("Favourite").hasFavourite());
        assertFalse(new Favourite(false).hasFavourite());
        assertTrue(new Favourite(true).hasFavourite());
    }

    @Test
    void equals() {
        Favourite favourite = new Favourite(false);

        // same values -> return true
        assertTrue(favourite.equals(new Favourite(false)));

        // same object -> returns true
        assertTrue(favourite.equals(favourite));

        // null -> returns false
        assertFalse(favourite.equals(null));

        // different types -> returns false
        assertFalse(favourite.equals(5.0f));

        // different values -> return false
        assertFalse(favourite.equals(new Favourite(true)));
    }
}
