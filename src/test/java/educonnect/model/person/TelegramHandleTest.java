package educonnect.model.person;

import static educonnect.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null telegram handle
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid phone numbers
        assertFalse(TelegramHandle.isValidTelegramHandle(""));
        assertFalse(TelegramHandle.isValidTelegramHandle(" "));
        assertFalse(TelegramHandle.isValidTelegramHandle("91"));
        assertFalse(TelegramHandle.isValidTelegramHandle("phone"));
        assertFalse(TelegramHandle.isValidTelegramHandle("9011p041"));
        assertFalse(TelegramHandle.isValidTelegramHandle("9312 1534"));

        // valid phone numbers
        assertTrue(TelegramHandle.isValidTelegramHandle("@911"));
        assertTrue(TelegramHandle.isValidTelegramHandle("@bobthebuilder"));
        assertTrue(TelegramHandle.isValidTelegramHandle("@hello123goodbye456"));
    }

    @Test
    public void equals() {
        TelegramHandle handle = new TelegramHandle("@linus");

        // same values -> returns true
        assertTrue(handle.equals(new TelegramHandle("@linus")));

        // same object -> returns true
        assertTrue(handle.equals(handle));

        // null -> returns false
        assertFalse(handle.equals(null));

        // different types -> returns false
        assertFalse(handle.equals(5.0f));

        // different values -> returns false
        assertFalse(handle.equals(new TelegramHandle("@linux")));
    }
}
