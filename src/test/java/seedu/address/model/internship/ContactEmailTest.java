package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactEmail(null));
    }

    @Test
    public void constructor_invalidContactEmail_throwsIllegalArgumentException() {
        String invalidContactEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactEmail(invalidContactEmail));
    }

    @Test
    public void isValidContactEmail() {
        // null contactEmail
        assertThrows(NullPointerException.class, () -> ContactEmail.isValidContactEmail(null));

        // blank contactEmail
        assertFalse(ContactEmail.isValidContactEmail("")); // empty string
        assertFalse(ContactEmail.isValidContactEmail(" ")); // spaces only

        // missing parts
        assertFalse(ContactEmail.isValidContactEmail("@example.com")); // missing local part
        assertFalse(ContactEmail.isValidContactEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(ContactEmail.isValidContactEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(ContactEmail.isValidContactEmail("peterjack@-")); // invalid domain name
        assertFalse(ContactEmail.isValidContactEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(ContactEmail.isValidContactEmail("peter jack@example.com")); // spaces in local part
        assertFalse(ContactEmail.isValidContactEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(ContactEmail.isValidContactEmail(" peterjack@example.com")); // leading space
        assertFalse(ContactEmail.isValidContactEmail("peterjack@example.com ")); // trailing space
        assertFalse(ContactEmail.isValidContactEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(ContactEmail.isValidContactEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(ContactEmail.isValidContactEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(ContactEmail.isValidContactEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(ContactEmail.isValidContactEmail("peter..jack@example.com"));
        assertFalse(ContactEmail.isValidContactEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(ContactEmail.isValidContactEmail("peterjack@.example.com"));
        assertFalse(ContactEmail.isValidContactEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(ContactEmail.isValidContactEmail("peterjack@-example.com"));
        assertFalse(ContactEmail.isValidContactEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(ContactEmail.isValidContactEmail("peterjack@example.c"));

        // valid contactEmail
        assertTrue(ContactEmail.isValidContactEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(ContactEmail.isValidContactEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(ContactEmail.isValidContactEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(ContactEmail.isValidContactEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(ContactEmail.isValidContactEmail("a@bc")); // minimal
        assertTrue(ContactEmail.isValidContactEmail("test@localhost")); // alphabets only
        assertTrue(ContactEmail.isValidContactEmail("123@145")); // numeric local part and domain name
        assertTrue(ContactEmail.isValidContactEmail("a1+be.d@example1.com"));
        assertTrue(ContactEmail.isValidContactEmail("peter_jack@very-very-very-long-example.com"));
        assertTrue(ContactEmail.isValidContactEmail("if.you.dream.it_you.can.do.it@example.com"));
        assertTrue(ContactEmail.isValidContactEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        ContactEmail contactEmail = new ContactEmail("valid@email");

        // same values -> returns true
        assertTrue(contactEmail.equals(new ContactEmail("valid@email")));

        // same object -> returns true
        assertTrue(contactEmail.equals(contactEmail));

        // null -> returns false
        assertFalse(contactEmail.equals(null));

        // different types -> returns false
        assertFalse(contactEmail.equals(5.0f));

        // different values -> returns false
        assertFalse(contactEmail.equals(new ContactEmail("other.valid@email")));
    }
}
