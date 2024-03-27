package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramChatTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramChat(null));
    }

    @Test
    public void constructor_invalidTelegramChat_throwsIllegalArgumentException() {
        String invalidTelegramChat = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramChat(invalidTelegramChat));
    }

    @Test
    public void isValidTelegramChat() {
        // null telegram chat
        assertThrows(NullPointerException.class, () -> TelegramChat.isValidTelegramChat(null));

        // blank telegram chat
        assertFalse(TelegramChat.isValidTelegramChat("")); // empty string
        assertFalse(TelegramChat.isValidTelegramChat(" ")); // spaces only

        // prefix not matched
        assertFalse(TelegramChat.isValidTelegramChat("+1234")); // missing prefix
        assertFalse(TelegramChat.isValidTelegramChat("t.me/+1234")); // missing https://
        assertFalse(TelegramChat.isValidTelegramChat("http://t.me/+1234")); // missing https://
        assertFalse(TelegramChat.isValidTelegramChat("https://telegram.me/+1234")); // missing t.me

        // missing chat id
        assertFalse(TelegramChat.isValidTelegramChat("https://t.me/"));

        // invalid chat id
        assertFalse(TelegramChat.isValidTelegramChat("https://t.me/&&&&&")); // &
        assertFalse(TelegramChat.isValidTelegramChat("https://t.me/1234 5678")); // space
        assertFalse(TelegramChat.isValidTelegramChat("https://t.me/1234*5678")); // asterisk
        assertFalse(TelegramChat.isValidTelegramChat("https://t.me/+1234.5678")); // period

        // valid telegram chat
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/+")); // minimal
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/+1234")); // standard
        assertTrue(TelegramChat.isValidTelegramChat(
                "https://t.me/+1234567890123456789012345678901234567890")); // long chat id
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/+1234_5678")); // underscore
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/+1234-5678")); // hyphen
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/1234+5678")); // plus
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/+abcdz")); // lower case
        assertTrue(TelegramChat.isValidTelegramChat("https://t.me/+ABCDZ")); // upper case
    }

    @Test
    public void equals() {
        TelegramChat telegramChat = new TelegramChat("https://t.me/+1234");

        // same values -> returns true
        assertTrue(telegramChat.equals(new TelegramChat("https://t.me/+1234")));

        // same object -> returns true
        assertTrue(telegramChat.equals(telegramChat));

        // null -> returns false
        assertFalse(telegramChat.equals(null));

        // different types -> returns false
        assertFalse(telegramChat.equals(5));

        // different telegram chat -> returns false
        assertFalse(telegramChat.equals(new TelegramChat("https://t.me/+5678")));
    }
}
