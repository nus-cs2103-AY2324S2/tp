package seedu.realodex.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.realodex.logic.parser.ParserUtil.parseAddressReturnStored;
import static seedu.realodex.logic.parser.ParserUtil.parseEmailReturnStored;
import static seedu.realodex.logic.parser.ParserUtil.parseFamilyReturnStored;
import static seedu.realodex.logic.parser.ParserUtil.parseIncomeReturnStored;
import static seedu.realodex.logic.parser.ParserUtil.parseNameReturnStored;
import static seedu.realodex.logic.parser.ParserUtil.parsePhoneReturnStored;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.remark.Remark;
import seedu.realodex.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME_CAPS = "D@nzel Washington Al Pacino";
    private static final String INVALID_NAME_NON_CAPS = "d@nzel washington Al Pacino";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_INCOME = "-1";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_FAMILY = "0";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG_1 = "#buyer";
    private static final String INVALID_TAG_2 = "friend";

    private static final String VALID_NAME_CAPS = "Denzel Washington Al Pacino";
    private static final String VALID_NAME_NON_CAPS_ALL = "denzel washington al pacino";
    private static final String VALID_NAME_NON_CAPS_FIRST_NAME = "denzel Washington Al Pacino";
    private static final String VALID_NAME_VARYING_CAPS = "dEnzeL waSHINgToN aL PaCINo";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_INCOME = "10000";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_FAMILY = "4";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "buyer";
    private static final String VALID_TAG_2 = "seller";

    private static final String VALID_REMARK_ONE = "I am Denzel Washington";
    private static final String VALID_REMARK_TWO = "I am Al Pacino";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInputButPositive_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_outOfRangeInputButNegative_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(-1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void capitalizeWords_nonCapitalizedFirstName_returnsNameCapitalizedName() throws Exception {
        Name expectedName = new Name(VALID_NAME_NON_CAPS_FIRST_NAME);
        assertEquals(VALID_NAME_CAPS, ParserUtil.capitalizeWords(VALID_NAME_NON_CAPS_FIRST_NAME));
    }

    @Test
    public void capitalizeWords_nonCapitalizedAllName_returnsNameCapitalizedName() throws Exception {
        Name expectedName = new Name(VALID_NAME_NON_CAPS_ALL);
        assertEquals(VALID_NAME_CAPS, ParserUtil.capitalizeWords(VALID_NAME_NON_CAPS_ALL));
    }

    @Test
    public void capitalizeWords_varyingCapitalizedName_returnsNameCapitalizedName() throws Exception {
        Name expectedName = new Name(VALID_NAME_VARYING_CAPS);
        assertEquals(VALID_NAME_CAPS, ParserUtil.capitalizeWords(VALID_NAME_NON_CAPS_ALL));
    }

    @Test
    public void capitalizeWords_nullName_returnsCapitalizedAllName() throws Exception {
        assertThrows(NullPointerException.class, ()
                -> ParserUtil.capitalizeWords(null));
    }

    @Test
    public void capitalizeWords_emptyName_returnsCapitalizedAllName() throws Exception {
        assertEquals("", ParserUtil.capitalizeWords(""));
    }


    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValueOfCapitalizedName_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME_CAPS));
    }

    @Test
    public void parseName_invalidValueOfNonCapitalizedName_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME_NON_CAPS));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME_CAPS);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME_CAPS));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME_CAPS + WHITESPACE;
        Name expectedName = new Name(VALID_NAME_CAPS);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseName_nonCapitalizedInAllPartsOfName_returnsCapitalizedName() throws Exception {
        String nonCapitalizedInBothNames = VALID_NAME_NON_CAPS_ALL;
        Name expectedName = new Name(ParserUtil.capitalizeWords(nonCapitalizedInBothNames));
        assertEquals(expectedName, ParserUtil.parseName(nonCapitalizedInBothNames));
    }

    @Test
    public void parseName_nonCapitalizedInFirstPartOfName_returnsCapitalizedName() throws Exception {
        String nonCapitalizedInFirstNames = VALID_NAME_NON_CAPS_FIRST_NAME;
        Name expectedName = new Name(ParserUtil.capitalizeWords(nonCapitalizedInFirstNames));
        assertEquals(expectedName, ParserUtil.parseName(nonCapitalizedInFirstNames));
    }

    @Test
    public void parseName_validName_returnsParserUtilName() {
        String validName = VALID_NAME_CAPS;
        ParserUtilResult<Name> nameStored = parseNameReturnStored(validName);
        assertEquals(nameStored.returnStoredResult(), new Name(validName));
        assertEquals(nameStored.returnExceptionMessage(), "");
    }

    @Test
    public void parseName_invalidName_returnsParserUtilName() {
        ParserUtilResult<Name> nameStored = parseNameReturnStored(INVALID_NAME_CAPS);
        assertEquals(nameStored.returnStoredResult(), new Name());
        assertEquals(nameStored.returnExceptionMessage(), Name.MESSAGE_CONSTRAINTS);
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
    public void parsePhone_validPhone_returnsParserUtilPhone() {
        String validPhone = VALID_INCOME;
        ParserUtilResult<Phone> phoneStored = parsePhoneReturnStored(validPhone);
        assertEquals(phoneStored.returnStoredResult(), new Phone(validPhone));
        assertEquals(phoneStored.returnExceptionMessage(), "");
    }

    @Test
    public void parsePhone_invalidPhone_returnsParserUtilPhone() {
        ParserUtilResult<Phone> phoneStored = parsePhoneReturnStored(INVALID_PHONE);
        assertEquals(phoneStored.returnStoredResult(), new Phone());
        assertEquals(phoneStored.returnExceptionMessage(), Phone.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parseIncome_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIncome((String) null));
    }

    @Test
    public void parseIncome_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIncome(INVALID_INCOME));
    }

    @Test
    public void parseIncome_validValueWithoutWhitespace_returnsIncome() throws Exception {
        Income expectedIncome = new Income(VALID_INCOME);
        assertEquals(expectedIncome, ParserUtil.parseIncome(VALID_INCOME));
    }

    @Test
    public void parseIncome_validValueWithWhitespace_returnsTrimmedIncome() throws Exception {
        String incomeWithWhitespace = WHITESPACE + VALID_INCOME + WHITESPACE;
        Income expectedIncome = new Income(VALID_INCOME);
        assertEquals(expectedIncome, ParserUtil.parseIncome(incomeWithWhitespace));
    }
    @Test
    public void parseIncome_validIncome_returnsParserUtilIncome() {
        String validIncome = VALID_INCOME;
        ParserUtilResult<Income> incomeStored = parseIncomeReturnStored(validIncome);
        assertEquals(incomeStored.returnStoredResult(), new Income(validIncome));
        assertEquals(incomeStored.returnExceptionMessage(), "");
    }

    @Test
    public void parseIncome_invalidIncome_returnsParserUtilIncome() {
        ParserUtilResult<Income> incomeStored = parseIncomeReturnStored(INVALID_INCOME);
        assertEquals(incomeStored.returnStoredResult(), new Income());
        assertEquals(incomeStored.returnExceptionMessage(), Income.MESSAGE_CONSTRAINTS);
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
    public void parseAddress_validPhone_returnsParserUtilPhone() {
        String validAddress = VALID_ADDRESS;
        ParserUtilResult<Address> addressStored = parseAddressReturnStored(validAddress);
        assertEquals(addressStored.returnStoredResult(), new Address(validAddress));
        assertEquals(addressStored.returnExceptionMessage(), "");
    }

    @Test
    public void parseAddress_invalidPhone_returnsParserUtilPhone() {
        ParserUtilResult<Address> addressStored = parseAddressReturnStored(INVALID_ADDRESS);
        assertEquals(addressStored.returnStoredResult(), new Address());
        assertEquals(addressStored.returnExceptionMessage(), Address.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parseFamily_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFamily((String) null));
    }

    @Test
    public void parseFamily_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFamily(INVALID_FAMILY));
    }

    @Test
    public void parseFamily_validValueWithoutWhitespace_returnsFamily() throws Exception {
        Family expectedFamily = new Family(VALID_FAMILY);
        assertEquals(expectedFamily, ParserUtil.parseFamily(VALID_FAMILY));
    }

    @Test
    public void parseFamily_validValueWithWhitespace_returnsTrimmedFamily() throws Exception {
        String familyWithWhitespace = WHITESPACE + VALID_FAMILY + WHITESPACE;
        Family expectedFamily = new Family(VALID_FAMILY);
        assertEquals(expectedFamily, ParserUtil.parseFamily(familyWithWhitespace));
    }

    @Test
    public void parseFamily_validFamily_returnsParserUtilFamily() {
        String validFamily = VALID_FAMILY;
        ParserUtilResult<Family> familyStored = parseFamilyReturnStored(validFamily);
        assertEquals(familyStored.returnStoredResult(), new Family(validFamily));
        assertEquals(familyStored.returnExceptionMessage(), "");
    }

    @Test
    public void parseFamily_invalidFamily_returnsParserUtilFamily() {
        ParserUtilResult<Family> familyStored = parseFamilyReturnStored(INVALID_FAMILY);
        assertEquals(familyStored.returnStoredResult(), new Family());
        assertEquals(familyStored.returnExceptionMessage(), Family.MESSAGE_CONSTRAINTS);
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
    public void parseEmail_validEmail_returnsParserUtilEmail() {
        String validEmail = VALID_EMAIL;
        ParserUtilResult<Email> emailStored = parseEmailReturnStored(validEmail);
        assertEquals(emailStored.returnStoredResult(), new Email(validEmail));
        assertEquals(emailStored.returnExceptionMessage(), "");
    }

    @Test
    public void parseEmail_invalidEmail_returnsParserUtilEmail() {
        ParserUtilResult<Email> emailStored = parseEmailReturnStored(INVALID_EMAIL);
        assertEquals(emailStored.returnStoredResult(), new Email());
        assertEquals(emailStored.returnExceptionMessage(), Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_2));
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
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG_1)));
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG_2)));
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
    public void parseTags_sinlgeDuplicateTags_ignoresDuplicates() throws Exception {
        Set<Tag> tagSetWithDuplicates = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_1));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1)));

        assertEquals(tagSetWithDuplicates, expectedTagSet);
    }

    @Test
    public void parseTags_multipleDuplicateTags_ignoresDuplicates() throws Exception {
        Set<Tag> tagSetWithMultipleDuplicates = ParserUtil.parseTags(Arrays.asList(
                VALID_TAG_1, VALID_TAG_2, VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(tagSetWithMultipleDuplicates, expectedTagSet);
    }

    @Test
    public void parseRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRemark((String) null));
    }
    @Test
    public void parseRemark_validValueWithoutWhitespace_returnsRemark() throws Exception {
        Remark expectedRemark = new Remark(VALID_REMARK_ONE);
        assertEquals(expectedRemark, ParserUtil.parseRemark(VALID_REMARK_ONE));
    }

    @Test
    public void parseRemark_validValueWithWhitespace_returnsTrimmedRemark() throws Exception {
        String remarkWithWhitespace = WHITESPACE + VALID_REMARK_ONE + WHITESPACE;
        Remark expectedRemark = new Remark(VALID_REMARK_ONE);
        assertEquals(expectedRemark, ParserUtil.parseRemark(remarkWithWhitespace));
    }
}
