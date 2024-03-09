package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EmailTest {

    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_EMAIL = "example.com";
    private static final String VALID_EMAIL = "rachel@example.com";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        // blank email
        assertThrows(IllegalArgumentException.class, () -> new Email("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> new Email(" ")); // spaces only

        // missing parts
        assertThrows(IllegalArgumentException.class, () -> new Email("@example.com")); // missing local part
        assertThrows(IllegalArgumentException.class, () -> new Email("peterjackexample.com")); // missing '@' symbol
        assertThrows(IllegalArgumentException.class, () -> new Email("peterjack@")); // missing domain name

        // invalid parts
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@-")); // invalid domain name
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@exam_ple.com")); // underscore in domain name
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peter jack@example.com")); // spaces in local part
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@exam ple.com")); // spaces in domain name
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email(" peterjack@example.com")); // leading space
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@example.com ")); // trailing space
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@@example.com")); // double '@' symbol
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peter@jack@example.com")); // '@' symbol in local part
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("-peterjack@example.com")); // local part starts with a hyphen
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack-@example.com")); // local part ends with a hyphen
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peter..jack@example.com")); // local part has two consecutive periods
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@example@com")); // '@' symbol in domain name
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@.example.com")); // domain name starts with a period
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@example.com.")); // domain name ends with a period
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@-example.com")); // domain name starts with a hyphen
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@example.com-")); // domain name ends with a hyphen
        assertThrows(IllegalArgumentException.class, () ->
                                        new Email("peterjack@example.c")); // top level domain has less than two chars
    }

    @Test
    public void constructor_validEmail_throwsIllegalArgumentException() {
        assertDoesNotThrow(() -> new Email("PeterJack_1190@example.com")); // underscore in local part
        assertDoesNotThrow(() -> new Email("PeterJack.1190@example.com")); // period in local part
        assertDoesNotThrow(() -> new Email("PeterJack+1190@example.com")); // '+' symbol in local part
        assertDoesNotThrow(() -> new Email("PeterJack-1190@example.com")); // hyphen in local part
        assertDoesNotThrow(() -> new Email("a@bc")); // minimal
        assertDoesNotThrow(() -> new Email("test@localhost")); // alphabets only
        assertDoesNotThrow(() -> new Email("123@145")); // numeric local part and domain name
        assertDoesNotThrow(() -> new Email("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertDoesNotThrow(() -> new Email("peter_jack@very-very-very-long-example.com")); // long domain name
        assertDoesNotThrow(() -> new Email("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertDoesNotThrow(() -> new Email("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Email.of((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> Email.of(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, Email.of(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, Email.of(emailWithWhitespace));
    }

    @Test
    public void equals() {
        Email email = new Email("valid@email");

        // same values -> returns true
        assertTrue(email.equals(new Email("valid@email")));

        // same object -> returns true
        assertTrue(email.equals(email));

        // null -> returns false
        assertFalse(email.equals(null));

        // different types -> returns false
        assertFalse(email.equals(5.0f));

        // different values -> returns false
        assertFalse(email.equals(new Email("other.valid@email")));
    }
}
