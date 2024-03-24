package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_IC = "T11222222222222Y";
    private static final String INVALID_WARD = "+";
    private static final String INVALID_ADMISSION_DATE = "123/12/20300";
    private static final String INVALID_TAG = "#Diabetes";
    private static final String INVALID_DOB_FUTURE = LocalDate.now().plusDays(1)
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // A future date
    private static final String INVALID_DOB_FORMAT = "12-31-2000"; // Wrong format

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_IC = "T1234567Y";
    private static final String VALID_WARD = "A1";
    private static final String VALID_ADMISSION_DATE = "12/12/2023";
    private static final String VALID_DOB = "12/12/1999";
    private static final String VALID_TAG_1 = "Diabetes";
    private static final String VALID_TAG_2 = "FallRisk";

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
    public void parseWard_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWard((String) null));
    }

    @Test
    public void parseWard_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWard(INVALID_WARD));
    }

    @Test
    public void parseWard_validValueWithoutWhitespace_returnsWard() throws Exception {
        Ward expectedWard = new Ward(VALID_WARD);
        assertEquals(expectedWard, ParserUtil.parseWard(VALID_WARD));
    }

    @Test
    public void parseWard_validValueWithWhitespace_returnsTrimmedWard() throws Exception {
        String wardWithWhitespace = WHITESPACE + VALID_WARD + WHITESPACE;
        Ward expectedWard = new Ward(VALID_WARD);
        assertEquals(expectedWard, ParserUtil.parseWard(wardWithWhitespace));
    }

    @Test
    public void parseIc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIc((String) null));
    }

    @Test
    public void parseIc_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIc(INVALID_IC));
    }

    @Test
    public void parseIc_validValueWithoutWhitespace_returnsIc() throws Exception {
        Ic expectedIc = new Ic(VALID_IC);
        assertEquals(expectedIc, ParserUtil.parseIc(VALID_IC));
    }

    @Test
    public void parseIc_validValueWithWhitespace_returnsTrimmedIc() throws Exception {
        String icWithWhitespace = WHITESPACE + VALID_IC + WHITESPACE;
        Ic expectedIc = new Ic(VALID_IC);
        assertEquals(expectedIc, ParserUtil.parseIc(icWithWhitespace));
    }


    @Test
    public void parseDob_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDob((String) null));
    }

    @Test
    public void parseDob_invalidValueFuture_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDob(INVALID_DOB_FUTURE));
    }

    @Test
    public void parseDob_invalidValueFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDob(INVALID_DOB_FORMAT));
    }

    @Test
    public void parseDob_validValueWithoutWhitespace_returnsDob() throws Exception {
        Dob expectedDob = new Dob(VALID_DOB);
        assertEquals(expectedDob, ParserUtil.parseDob(VALID_DOB));
    }

    @Test
    public void parseDob_validValueWithWhitespace_returnsTrimmedDob() throws Exception {
        String dobWithWhitespace = WHITESPACE + VALID_DOB + WHITESPACE;
        Dob expectedDob = new Dob(VALID_DOB.trim());
        assertEquals(expectedDob, ParserUtil.parseDob(dobWithWhitespace));
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
}
