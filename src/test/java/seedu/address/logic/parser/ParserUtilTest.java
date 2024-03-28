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
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_NUSID = "e1234567";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TAG = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GROUP = "#friend";
    private static final String INVALID_SCHEDULE = "2024-12-12";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_NUSID = "E1234567";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_TAG = "Student";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_GROUP_1 = "friend";
    private static final String VALID_GROUP_2 = "neighbour";
    private static final String VALID_SCHEDULE = "12-12-2020";
    private static final String VALID_REMARK = "I love Hong Jun";

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
    public void parseNusId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNusId((String) null));
    }

    @Test
    public void parseNusId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNusId(INVALID_NUSID));
    }

    @Test
    public void parseNusId_validValueWithoutWhitespace_returnsNusId() throws Exception {
        NusId expectedNusId = new NusId(VALID_NUSID);
        assertEquals(expectedNusId, ParserUtil.parseNusId(VALID_NUSID));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag((String) null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
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
    public void parseGroup_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroup(null));
    }

    @Test
    public void parseGroup_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroup(INVALID_GROUP));
    }

    @Test
    public void parseGroup_validValueWithoutWhitespace_returnsGroup() throws Exception {
        Group expectedGroup = new Group(VALID_GROUP_1);
        assertEquals(expectedGroup, ParserUtil.parseGroup(VALID_GROUP_1));
    }

    @Test
    public void parseGroup_validValueWithWhitespace_returnsTrimmedGroup() throws Exception {
        String groupWithWhitespace = WHITESPACE + VALID_GROUP_1 + WHITESPACE;
        Group expectedGroup = new Group(VALID_GROUP_1);
        assertEquals(expectedGroup, ParserUtil.parseGroup(groupWithWhitespace));
    }

    @Test
    public void parseGroups_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroups(null));
    }

    @Test
    public void parseGroups_collectionWithInvalidGroups_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroups(Arrays.asList(VALID_GROUP_1,
                INVALID_GROUP)));
    }

    @Test
    public void parseGroups_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseGroups(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseGroups_collectionWithValidGroups_returnsGroupSet() throws Exception {
        Set<Group> actualGroupSet = ParserUtil.parseGroups(Arrays.asList(VALID_GROUP_1,
                VALID_GROUP_2));
        Set<Group> expectedGroupSet = new HashSet<Group>(Arrays.asList(new Group(VALID_GROUP_1),
                new Group(VALID_GROUP_2)));

        assertEquals(expectedGroupSet, actualGroupSet);
    }

    @Test
    public void parseSchedule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSchedule((String) null));
    }

    @Test
    public void parseSchedule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSchedule(INVALID_SCHEDULE));
    }

    @Test
    public void parseSchedule_validValueWithoutWhitespace_returnsSchedule() throws Exception {
        Schedule expectedSchedule = new Schedule(VALID_SCHEDULE);
        assertEquals(expectedSchedule, ParserUtil.parseSchedule(VALID_SCHEDULE));
    }

    @Test
    public void parseRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }
}
