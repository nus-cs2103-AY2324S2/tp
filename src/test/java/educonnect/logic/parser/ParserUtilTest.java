package educonnect.logic.parser;

import static educonnect.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static educonnect.testutil.Assert.assertThrows;
import static educonnect.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.timetable.Period;
import educonnect.model.student.timetable.Timetable;
import educonnect.model.student.timetable.exceptions.NumberOfDaysException;
import educonnect.model.student.timetable.exceptions.OverlapPeriodException;
import educonnect.model.tag.Tag;
import educonnect.testutil.TypicalTimetableAndValues;

public class ParserUtilTest {
    private static final String WHITESPACE = " \t\r\n";

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "B1234567X";
    private static final String INVALID_TELEGRAM_HANDLE = "johndoe";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_STUDENT_ID = "A1234567U";
    private static final String VALID_TELEGRAM_HANDLE = "@smithSon";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
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
    public void parseStudentId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentId(null));
    }

    @Test
    public void parseStudentId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentId(INVALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithoutWhitespace_returnsStudentId() throws Exception {
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(VALID_STUDENT_ID));
    }

    @Test
    public void parseStudentId_validValueWithWhitespace_returnsTrimmedStudentId() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_STUDENT_ID + WHITESPACE;
        StudentId expectedStudentId = new StudentId(VALID_STUDENT_ID);
        assertEquals(expectedStudentId, ParserUtil.parseStudentId(phoneWithWhitespace));
    }

    @Test
    public void parseTelegramHandle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTelegramHandle(null));
    }

    @Test
    public void parseTelegramHandle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegramHandle(INVALID_TELEGRAM_HANDLE));
    }

    @Test
    public void parseTelegramHandle_validValueWithoutWhitespace_returnsTelegramHandle() throws Exception {
        TelegramHandle expectedTelegramHandle = new TelegramHandle(VALID_TELEGRAM_HANDLE);
        assertEquals(expectedTelegramHandle, ParserUtil.parseTelegramHandle(VALID_TELEGRAM_HANDLE));
    }

    @Test
    public void parseTelegramHandle_validValueWithWhitespace_returnsTrimmedTelegramHandle() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_TELEGRAM_HANDLE + WHITESPACE;
        TelegramHandle expectedTelegramHandle = new TelegramHandle(VALID_TELEGRAM_HANDLE);
        assertEquals(expectedTelegramHandle, ParserUtil.parseTelegramHandle(addressWithWhitespace));
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
    public void parsePeriod_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePeriod(null));
    }

    @Test
    public void parsePeriod_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePeriod(TypicalTimetableAndValues.INVALID_PERIOD1));
        assertThrows(ParseException.class, () -> ParserUtil.parsePeriod(TypicalTimetableAndValues.INVALID_PERIOD2));
    }

    @Test
    public void parsePeriod_validValueWithoutWhitespace_returnsPeriod() throws Exception {
        Period actualPeriod1 = ParserUtil.parsePeriod(TypicalTimetableAndValues.VALID_PERIOD1);
        Period actualPeriod2 = ParserUtil.parsePeriod(TypicalTimetableAndValues.VALID_PERIOD3);

        assertEquals(TypicalTimetableAndValues.EXPECTED_PERIOD_1, actualPeriod1);
        assertEquals(TypicalTimetableAndValues.EXPECTED_PERIOD_3, actualPeriod2);
    }

    @Test
    public void parsePeriod_validValueWithWhitespace_returnsPeriodWithTrimmedName() throws Exception {
        Period actualPeriod1 = ParserUtil.parsePeriod(
                WHITESPACE + TypicalTimetableAndValues.VALID_PERIOD1 + WHITESPACE);
        Period actualPeriod2 = ParserUtil.parsePeriod(
                WHITESPACE + TypicalTimetableAndValues.VALID_PERIOD2 + WHITESPACE);

        assertEquals(TypicalTimetableAndValues.EXPECTED_PERIOD_1, actualPeriod1);
        assertEquals(TypicalTimetableAndValues.EXPECTED_PERIOD_2, actualPeriod2);
    }

    @Test
    public void parsePeriods_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePeriods(null));
    }

    @Test
    public void parsePeriods_emptyString_returnsOptionalEmpty() throws Exception {
        String empty = "";

        assertEquals(Optional.empty(), ParserUtil.parsePeriods(empty));
        assertEquals(Optional.empty(), ParserUtil.parsePeriods(WHITESPACE));
    }
    @Test
    public void parsePeriods_stringWithInvalidValues_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () ->
                ParserUtil.parsePeriods(TypicalTimetableAndValues.FULL_STRING_WITH_INVALID_INPUT));
    }

    @Test
    public void parsePeriods_stringWithValidValuesWithoutWhitespace_returnsOptionalArrayList() throws Exception {
        assertEquals(TypicalTimetableAndValues.VALID_PERIOD_OPTIONAL_ARRAYLIST,
                ParserUtil.parsePeriods(TypicalTimetableAndValues.FULL_STRING_VALID_INPUT));
    }

    @Test
    public void parsePeriods_stringWithValidValuesWithWhitespace_returnsOptionalArrayListWithTrimmedPeriods()
            throws Exception {
        assertEquals(TypicalTimetableAndValues.VALID_PERIOD_OPTIONAL_ARRAYLIST,
                ParserUtil.parsePeriods(TypicalTimetableAndValues.FULL_STRING_WITH_WHITESPACE_VALID_INPUT));
    }

    @Test
    public void parseDay_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDay(1, null, null));
    }

    @Test
    public void parseDay_invalidInputs() throws OverlapPeriodException {
        Timetable timetable = new Timetable();

        // invalid inputs, day cannot be 0 -> returns false
        assertThrows(NumberOfDaysException.class, () ->
                ParserUtil.parseDay(0, timetable,
                        TypicalTimetableAndValues.INVALID_PERIOD_OPTIONAL_ARRAYLIST));

        // invalid inputs, allPeriods contains invalid inputs (overlap periods) -> returns false
        assertThrows(OverlapPeriodException.class, () ->
                ParserUtil.parseDay(1, timetable,
                        TypicalTimetableAndValues.INVALID_PERIOD_OPTIONAL_ARRAYLIST));
    }

    @Test
    public void parseDay_validInputs_returnsTimetableWithUpdatedDay() throws OverlapPeriodException {
        Timetable timetable = new Timetable();

        String expectedTimetable = "Timetable\n"
                                   + "For MONDAY, schedule is:\n"
                                   + "Period period: (13:00 to 15:00)\n"
                                   + "Period period: (16:00 to 18:00)\n\n"
                                   + "For TUESDAY, schedule is:\n\n"
                                   + "For WEDNESDAY, schedule is:\n\n"
                                   + "For THURSDAY, schedule is:\n\n"
                                   + "For FRIDAY, schedule is:\n"
                                   + "Period period: (13:00 to 15:00)\n"
                                   + "Period period: (16:00 to 18:00)\n\n";

        if (Timetable.is7Days()) {
            expectedTimetable += "For SATURDAY, schedule is:\n\n"
                                 + "For SUNDAY, schedule is:\n\n";
        }

        // valid inputs, successfully added -> returns true
        assertTrue(ParserUtil.parseDay(1, timetable, TypicalTimetableAndValues.VALID_PERIOD_OPTIONAL_ARRAYLIST));
        assertTrue(ParserUtil.parseDay(5, timetable, TypicalTimetableAndValues.VALID_PERIOD_OPTIONAL_ARRAYLIST));
        assertEquals(expectedTimetable, timetable.toString());
    }

    @Test
    public void parseTimetable_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimetable(null));
    }

    @Test
    public void parseTimetable_validInputs_returnsCompletedTimetable() throws Exception {
        assertEquals(TypicalTimetableAndValues.VALID_TIMETABLE_1.toString(),
                ParserUtil.parseTimetable(TypicalTimetableAndValues.VALID_TIMETABLE_INPUT_1).toString());
        assertEquals(TypicalTimetableAndValues.VALID_TIMETABLE_2.toString(),
                ParserUtil.parseTimetable(TypicalTimetableAndValues.VALID_TIMETABLE_INPUT_2).toString());
    }
}
