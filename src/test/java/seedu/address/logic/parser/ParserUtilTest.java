package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
    public void parseIndices_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndices("1, 2, c", ","));
    }

    @Test
    public void parseIndices_duplicateIndex_throwsParseException() {
        String args = "1, 4, 5, 1, 2";
        assertThrows(ParseException.class, () -> ParserUtil.parseIndices(args, ","));
    }

    @Test
    public void parseIndices_validArgsWhiteSpaceSep_success() throws Exception {
        List<Index> actualList = ParserUtil.parseIndices("1 5 3", " ");
        List<Index> expectedList = Arrays.asList(
            Index.fromOneBased(1),
            Index.fromOneBased(5),
            Index.fromOneBased(3)
        );

        assertEquals(expectedList, actualList);
    }

    @Test
    public void parseIndices_validArgsCommaSep_success() throws Exception {
        List<Index> actualList = ParserUtil.parseIndices("10,3 , 2 ", ",");
        List<Index> expectedList = Arrays.asList(
            Index.fromOneBased(10),
            Index.fromOneBased(3),
            Index.fromOneBased(2)
        );

        assertEquals(expectedList, actualList);
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
    public void filterByPrefix_emptyPrefix_returnsFullList() {
        String[] words = {"Hello", "World", "Bye", "Life"};
        List<String> actualList = ParserUtil.filterByPrefix("", words);
        List<String> expectedList = Arrays.asList(words);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void filterByPrefix_subStringPrefix_returnsOneElement() {
        String[] words = {"Hello", "World", "Bye", "Life"};

        List<String> actualList = ParserUtil.filterByPrefix("W", words);
        List<String> expectedList = Arrays.asList("World");
        assertEquals(expectedList, actualList);

        actualList = ParserUtil.filterByPrefix("Hell", words);
        expectedList = Arrays.asList("Hello");
        assertEquals(expectedList, actualList);

        actualList = ParserUtil.filterByPrefix("Li", words);
        expectedList = Arrays.asList("Life");
        assertEquals(expectedList, actualList);
    }

    @Test
    public void filterByPrefix_fullStringPrefix_returnsOneElement() {
        String[] words = {"Hello", "World", "Bye", "Life"};

        List<String> actualList = ParserUtil.filterByPrefix("World", words);
        List<String> expectedList = Arrays.asList("World");
        assertEquals(expectedList, actualList);

        actualList = ParserUtil.filterByPrefix("Hello", words);
        expectedList = Arrays.asList("Hello");
        assertEquals(expectedList, actualList);

        actualList = ParserUtil.filterByPrefix("Life", words);
        expectedList = Arrays.asList("Life");
        assertEquals(expectedList, actualList);
    }

    @Test
    public void filterByPrefix_noMatchPrefix_returnsEmptyList() {
        String[] words = {"Hel lo", "World", "Bye", "Life"};
        List<String> expectedList = Arrays.asList(); // Empty list.

        // Prefix does not match any words.
        List<String> actualList = ParserUtil.filterByPrefix("Bon", words);
        assertEquals(expectedList, actualList);

        // Prefix must be a substring.
        actualList = ParserUtil.filterByPrefix("Bye Bye", words);
        assertEquals(expectedList, actualList);

        // Strings are case sensitive.
        actualList = ParserUtil.filterByPrefix("LiF", words);
        assertEquals(expectedList, actualList);

        // Strings are space sensitive.
        actualList = ParserUtil.filterByPrefix(" Wor", words);
        assertEquals(expectedList, actualList);

        actualList = ParserUtil.filterByPrefix("Hello", words);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void filterByPrefix_commonPrefix_returnsMultipleElements() {
        String[] words = {"pointer", "Add", "Attack", "point", "Addition", "point Forward"};

        List<String> actualList = ParserUtil.filterByPrefix("A", words);
        List<String> expectedList = Arrays.asList("Add", "Attack", "Addition");
        assertEquals(expectedList, actualList);

        actualList = ParserUtil.filterByPrefix("point", words);
        expectedList = Arrays.asList("pointer", "point", "point Forward");
        assertEquals(expectedList, actualList);
    }
}
