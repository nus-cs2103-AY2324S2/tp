package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfAdmissionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfAdmission(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfAdmission(invalidDate));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> DateOfAdmission.isValidDateOfAdmission(null));

        // invalid addresses
        assertFalse(DateOfAdmission.isValidDateOfAdmission("")); // empty string
        assertFalse(DateOfAdmission.isValidDateOfAdmission(" ")); // spaces only
        assertFalse(DateOfAdmission.isValidDateOfAdmission("13122023")); // numbers only
        assertFalse(DateOfAdmission.isValidDateOfAdmission("13/122023")); // one slash
        assertFalse(DateOfAdmission.isValidDateOfAdmission("1312/2023")); // one slash
        assertFalse(DateOfAdmission.isValidDateOfAdmission("1/3/2023")); // shortened date and month
        assertFalse(DateOfAdmission.isValidDateOfAdmission("2023/12/13")); // reverse format

        // valid addresses
        assertTrue(DateOfAdmission.isValidDateOfAdmission("13/12/2023"));
    }

    @Test
    public void equals() {
        DateOfAdmission date = new DateOfAdmission("13/12/2020");

        // same values -> returns true
        assertTrue(date.equals(new DateOfAdmission("13/12/2020")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new DateOfAdmission("13/12/2023")));
    }
}