package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_LIST;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Week;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_WEEK = "1";
    private static final String INVALID_WEEK = "100";
    private static final String VALID_INDICES = "1, 2, 3";
    private static final String INVALID_INDICES = "1, 2, @";

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
        Optional<Phone> expectedPhone = Optional.of(new Phone(VALID_PHONE));
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Optional<Phone> expectedPhone = Optional.of(new Phone(VALID_PHONE));
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
    public void parseWeek_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeek((String) null));
    }

    @Test
    public void parseWeek_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeek(INVALID_WEEK));
        assertThrows(ParseException.class, () -> ParserUtil.parseWeek("not a number"));
    }

    @Test
    public void parseWeek_validValueWithoutWhitespace_returnsWeek() throws Exception {
        Index index = Index.fromOneBased(Integer.parseInt(VALID_WEEK));
        Week expectedWeek = new Week(index);
        assertEquals(expectedWeek, ParserUtil.parseWeek(VALID_WEEK));
    }

    @Test
    public void parseWeek_validValueWithWhitespace_returnsWeek() throws Exception {
        String weekWithWhitespace = WHITESPACE + VALID_WEEK + WHITESPACE;
        Index index = Index.fromOneBased(Integer.parseInt(VALID_WEEK));
        Week expectedWeek = new Week(index);
        assertEquals(expectedWeek, ParserUtil.parseWeek(weekWithWhitespace));
    }

    @Test
    public void parseIndices_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIndices(null));
    }

    @Test
    public void parseIndices_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndices("a b"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndices("1, b"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndices("1 2 3"));
    }

    @Test
    public void parseIndices_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndices(Long.toString(Integer.MAX_VALUE + 1)));

        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndices("1, " + Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndices_validInput_success() throws Exception {
        // No indices
        assertEquals(new ArrayList<Index>(), ParserUtil.parseIndices(""));

        // No whitespaces
        assertEquals(INDEX_LIST, ParserUtil.parseIndices("1,2,3"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_LIST, ParserUtil.parseIndices(" 1 , 2 , 3 "));

        // Trailing comma
        assertEquals(INDEX_LIST, ParserUtil.parseIndices(" 1 , 2 , 3, "));
    }
}
