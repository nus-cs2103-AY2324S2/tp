package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.house.Block;
import seedu.address.model.house.Level;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STREET = "t3$t!ng";
    private static final String INVALID_LEVEL = "aa";
    private static final String INVALID_UNIT_NUMBER = "1234";
    private static final String INVALID_BLOCK = "12a34";
    private static final String INVALID_POSTAL_CODE = "5678990";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_POSTAL_CODE = "654321";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_STREET = "292A East Coast Rd";
    private static final String VALID_LEVEL = "10";
    private static final String VALID_UNIT_NUMBER = "123";
    private static final String VALID_BLOCK = "205A";
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
    public void parsePostalCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePostalCode((String) null));
    }

    @Test
    public void parsePostalCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePostalCode(INVALID_POSTAL_CODE));
    }

    @Test
    public void parsePostalCode_validValueWithoutWhitespace_returnsPostalCode() throws Exception {
        PostalCode expectedPostalCode = new PostalCode(VALID_POSTAL_CODE);
        assertEquals(expectedPostalCode, ParserUtil.parsePostalCode(VALID_POSTAL_CODE));
    }

    @Test
    public void parsePostalCode_validValueWithWhitespace_returnsTrimmedPostalCode() throws Exception {
        String postalCodeWithWhitespace = WHITESPACE + VALID_POSTAL_CODE + WHITESPACE;
        PostalCode expectedPostalCode = new PostalCode(VALID_POSTAL_CODE);
        assertEquals(expectedPostalCode, ParserUtil.parsePostalCode(postalCodeWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseStreet_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStreet((String) null));
    }

    @Test
    public void parseStreet_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStreet(INVALID_STREET));
    }

    @Test
    public void parseStreet_validValueWithoutWhitespace_returnsStreet() throws Exception {
        Street expectedStreet = new Street(VALID_STREET);
        assertEquals(expectedStreet, ParserUtil.parseStreet(VALID_STREET));
    }

    @Test
    public void parseStreet_validValueWithWhitespace_returnsTrimmedStreet() throws Exception {
        String streetWithWhitespace = WHITESPACE + VALID_STREET + WHITESPACE;
        Street expectedStreet = new Street(VALID_STREET);
        assertEquals(expectedStreet, ParserUtil.parseStreet(streetWithWhitespace));
    }
    public void parseLevel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLevel((String) null));
    }

    @Test
    public void parseLevel_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLevel(INVALID_LEVEL));
    }

    @Test
    public void parseLevel_validValueWithoutWhitespace_returnsLevel() throws Exception {
        Level expectedLevel = new Level(VALID_LEVEL);
        assertEquals(expectedLevel, ParserUtil.parseLevel(VALID_LEVEL));
    }

    @Test
    public void parseLevel_validValueWithWhitespace_returnsTrimmedLevel() throws Exception {
        String levelWithWhitespace = WHITESPACE + VALID_LEVEL + WHITESPACE;
        Level expectedLevel = new Level(VALID_LEVEL);
        assertEquals(expectedLevel, ParserUtil.parseLevel(levelWithWhitespace));
    }
    public void parseUnitNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUnitNumber((String) null));
    }

    @Test
    public void parseUnitNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseUnitNumber(INVALID_UNIT_NUMBER));
    }

    @Test
    public void parseUnitNumber_validValueWithoutWhitespace_returnsUnitNumber() throws Exception {
        UnitNumber expectedUnitNumber = new UnitNumber(VALID_UNIT_NUMBER);
        assertEquals(expectedUnitNumber, ParserUtil.parseUnitNumber(VALID_UNIT_NUMBER));
    }

    @Test
    public void parseUnitNumber_validValueWithWhitespace_returnsTrimmedUnitNumber() throws Exception {
        String unitNumberWithWhitespace = WHITESPACE + VALID_UNIT_NUMBER + WHITESPACE;
        UnitNumber expectedUnitNumber = new UnitNumber(VALID_UNIT_NUMBER);
        assertEquals(expectedUnitNumber, ParserUtil.parseUnitNumber(unitNumberWithWhitespace));
    }
    public void parseBlock_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBlock((String) null));
    }

    @Test
    public void parseBlock_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBlock(INVALID_BLOCK));
    }

    @Test
    public void parseBlock_validValueWithoutWhitespace_returnsBlock() throws Exception {
        Block expectedBlock = new Block(VALID_BLOCK);
        assertEquals(expectedBlock, ParserUtil.parseBlock(VALID_BLOCK));
    }

    @Test
    public void parseBlock_validValueWithWhitespace_returnsTrimmedBlock() throws Exception {
        String blockWithWhitespace = WHITESPACE + VALID_BLOCK + WHITESPACE;
        Block expectedBlock = new Block(VALID_BLOCK);
        assertEquals(expectedBlock, ParserUtil.parseBlock(blockWithWhitespace));
    }
}
