package seedu.address.model.allergen;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AllergenTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Allergen(null));
    }

    @Test
    public void constructor_invalidAllergenName_throwsIllegalArgumentException() {
        String invalidAllergenName = "";
        assertThrows(IllegalArgumentException.class, () -> new Allergen(invalidAllergenName));
    }

    @Test
    public void isValidAllergenName() {
        // null allergen name
        assertThrows(NullPointerException.class, () -> Allergen.isValidAllergenName(null));
    }

}
