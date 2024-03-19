package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@example.com")); // does not match the format e0123456@u.nus.edu
        assertFalse(Email.isValidEmail("e0123456u.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("e0123456@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("e0123456@-")); // invalid domain name
        assertFalse(Email.isValidEmail("e0123456@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("e0345 678@u.nus.edu")); // spaces in local part
        assertFalse(Email.isValidEmail("e0345678@u.nus.ed u")); // spaces in domain name
        assertFalse(Email.isValidEmail(" e0345678@u.nus.edu")); // leading space
        assertFalse(Email.isValidEmail("e0345678@u.nus.edu ")); // trailing space
        assertFalse(Email.isValidEmail("e0345678@@u.nus.edu")); // double '@' symbol
        assertFalse(Email.isValidEmail("e03@45678@u.nus.edu")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("-e0345678@u.nus.edu")); // local part starts with a hyphen
        assertFalse(Email.isValidEmail("e0345678-@u.nus.edu")); // local part ends with a hyphen
        assertFalse(Email.isValidEmail("e034..5678@u.nus.edu")); // local part has two consecutive periods
        assertFalse(Email.isValidEmail("e03456@78@u.nus.edu")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("e0345678@.u.nus.edu")); // domain name starts with a period
        assertFalse(Email.isValidEmail("e0345678@u.nus.edu.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("e0345678@-u.nus.edu")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("e0345678@u.nus.edu-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("e0345678@u.nus.e")); // top level domain has less than two chars
        assertFalse(Email.isValidEmail("y0345678@u.nus.edu")); // local part starts with an invalid char
        assertFalse(Email.isValidEmail("e03478@u.nus.edu")); // local part missing 7 digits

        // valid email
        assertTrue(Email.isValidEmail("e0345678@u.nus.edu")); // underscore in local part
    }

    @Test
    public void equals() {
        Email email = new Email("e0123456@u.nus.edu");

        // same values -> returns true
        assertTrue(email.equals(new Email("e0123456@u.nus.edu")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("e0456789@u.nus.edu")));
    }
}
