package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.illness.Illness;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ILLNESS = "#illness";
    private static final String INVALID_DATE = "2024-02-19";
    private static final String INVALID_TIME = "5006";
    private static final String INVALID_DESCRIPTION = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_ILLNESS_1 = "Infectious Disease";
    private static final String VALID_ILLNESS_2 = "Chronic Conditions";
    private static final String VALID_DATE = "19-02-2024";
    private static final String VALID_TIME = "1130";
    private static final String VALID_DESCRIPTION = "General Flu";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseIllness_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIllness(null));
    }

    @Test
    public void parseIllness_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIllness(INVALID_ILLNESS));
    }

    @Test
    public void parseIllness_validValueWithoutWhitespace_returnsIllness() throws Exception {
        Illness expectedIllness = new Illness(VALID_ILLNESS_1);
        assertEquals(expectedIllness, ParserUtil.parseIllness(VALID_ILLNESS_1));
    }

    @Test
    public void parseIllness_validValueWithWhitespace_returnsTrimmedIllness() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_ILLNESS_1 + WHITESPACE;
        Illness expectedIllness = new Illness(VALID_ILLNESS_1);
        assertEquals(expectedIllness, ParserUtil.parseIllness(tagWithWhitespace));
    }

    @Test
    public void parseIllnesses_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIllnesses(null));
    }

    @Test
    public void parseIllnesses_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIllnesses(Arrays.asList(VALID_ILLNESS_1, INVALID_ILLNESS)));
    }

    @Test
    public void parseIllnesses_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseIllnesses(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseIllnesses_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Illness> actualIllnessSet = ParserUtil.parseIllnesses(Arrays.asList(VALID_ILLNESS_1, VALID_ILLNESS_2));
        Set<Illness> expectedIllnessSet = new HashSet<>(Arrays.asList(new Illness(VALID_ILLNESS_1), new Illness(VALID_ILLNESS_2)));

        assertEquals(expectedIllnessSet, actualIllnessSet);
    }

    @Nested
    public class ParseLocalDateTimeTests {
        @Test
        public void parseLocalDateTime_success() throws Exception {
            LocalDateTime expectedDateTime =
                LocalDateTime.parse(VALID_DATE + " " + VALID_TIME, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            assertEquals(expectedDateTime, ParserUtil.parseLocalDateTime(VALID_DATE, VALID_TIME));
        }

        @Test
        public void parseLocalDateTime_invalidDateTime_throwsParseException() {
            assertThrows(ParseException.class, () -> ParserUtil.parseLocalDateTime(INVALID_DATE, INVALID_TIME));
        }

        @Test
        public void parseLocalDateTime_invalidDate_throwsParseException() {
            assertThrows(ParseException.class, () -> ParserUtil.parseLocalDateTime(INVALID_DATE, VALID_TIME));
        }

        @Test
        public void parseLocalDateTime_invalidTime_throwsParseException() {
            assertThrows(ParseException.class, () -> ParserUtil.parseLocalDateTime(VALID_DATE, INVALID_TIME));
        }

        @Test
        public void parseLocalDateTime_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> ParserUtil.parseLocalDateTime(null, null));
        }
    }

    @Nested
    public class ParseDescriptionTests {
        @Test
        public void parseDescription_success() throws Exception {
            Description expected = new Description(VALID_DESCRIPTION);
            assertEquals(expected, ParserUtil.parseDescription(VALID_DESCRIPTION));
        }

        @Test
        public void parseDescription_invalidInput_throwsParseException() {
            assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
        }

        @Test
        public void parseDescription_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription(null));
        }
    }
}
