package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Skills;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DEPARTMENT = "InvalidDepartment&^";
    private static final String INVALID_JOB_TITLE = "InvalidJobTitle&^";
    private static final String INVALID_PRODUCT = "Invalid Product!@#";
    private static final String INVALID_SKILLS = "Invalid Skills!@#";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_DEPARTMENT = "ValidDepartment";
    private static final String VALID_JOB_TITLE = "ValidJobTitle";
    private static final String VALID_PRODUCT_1_STRING = "Product 1";
    private static final String VALID_PRODUCT_2_STRING = "Product 2";
    private static final String VALID_PRODUCT_3_STRING = "Product 3";
    private static final String VALID_SKILLS_1 = "Java";
    private static final String VALID_SKILLS_2 = "Python";
    private static final String VALID_SKILLS_3 = "C++";

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
    public void parseId_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId("0"));
        assertThrows(ParseException.class, () -> ParserUtil.parseId("a"));
    }

    @Test
    public void parseId_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, Id.MESSAGE_CONSTRAINTS, ()
                -> ParserUtil.parseId(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseId_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(ID_FIRST_PERSON, ParserUtil.parseId("1"));

        // Leading and trailing whitespaces
        assertEquals(ID_FIRST_PERSON, ParserUtil.parseId("  1  "));
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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
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
    public void parseDepartment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDepartment(null));
    }

    @Test
    public void parseDepartment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDepartment(INVALID_DEPARTMENT));
    }

    @Test
    public void parseDepartment_validValueWithoutWhitespace_returnsDepartment() throws Exception {
        Department expectedDepartment = new Department(VALID_DEPARTMENT);
        assertEquals(expectedDepartment, ParserUtil.parseDepartment(VALID_DEPARTMENT));
    }

    @Test
    public void parseDepartment_validValueWithWhitespace_returnsTrimmedDepartment() throws Exception {
        String departmentWithWhitespace = "  " + VALID_DEPARTMENT + "  ";
        Department expectedDepartment = new Department(VALID_DEPARTMENT);
        assertEquals(expectedDepartment, ParserUtil.parseDepartment(departmentWithWhitespace));
    }

    @Test
    public void parseJobTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJobTitle(null));
    }

    @Test
    public void parseJobTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle(INVALID_JOB_TITLE));
    }

    @Test
    public void parseJobTitle_validValueWithoutWhitespace_returnsJobTitle() throws Exception {
        JobTitle expectedJobTitle = new JobTitle(VALID_JOB_TITLE);
        assertEquals(expectedJobTitle, ParserUtil.parseJobTitle(VALID_JOB_TITLE));
    }

    @Test
    public void parseJobTitle_validValueWithWhitespace_returnsTrimmedJobTitle() throws Exception {
        String jobTitleWithWhitespace = "  " + VALID_JOB_TITLE + "  ";
        JobTitle expectedJobTitle = new JobTitle(VALID_JOB_TITLE);
        assertEquals(expectedJobTitle, ParserUtil.parseJobTitle(jobTitleWithWhitespace));
    }

    @Test
    public void parseProducts_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProducts(null));
    }

    @Test
    public void parseProducts_invalidProducts_throwsParseException() {
        List<String> invalidProducts = Arrays.asList(VALID_PRODUCT_1_STRING, INVALID_PRODUCT, VALID_PRODUCT_2_STRING);
        assertThrows(ParseException.class, () -> ParserUtil.parseProducts(invalidProducts));
    }

    @Test
    public void parseProducts_validProducts_returnsProducts() throws Exception {
        List<String> validProducts = Arrays.asList(VALID_PRODUCT_1_STRING, VALID_PRODUCT_2_STRING,
                VALID_PRODUCT_3_STRING);
        Products expectedProducts = new Products(validProducts);
        assertEquals(expectedProducts, ParserUtil.parseProducts(validProducts));
    }

    @Test
    public void parseSkillsCollection_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSkills((Collection<String>) null));
    }

    @Test
    public void parseSkills_emptyCollection_returnsEmptySkills() throws Exception {
        Skills expectedSkills = new Skills(Collections.emptySet());
        assertEquals(expectedSkills, ParserUtil.parseSkills(Collections.emptyList()));
    }

    @Test
    public void parseSkills_collectionWithValidSkills_returnsSkills() throws Exception {
        Set<String> skills = new HashSet<>(Arrays.asList(VALID_SKILLS_1, VALID_SKILLS_2, VALID_SKILLS_3));
        Skills expectedSkills = new Skills(skills);
        assertEquals(expectedSkills, ParserUtil.parseSkills(skills));
    }

    @Test
    public void parseSkills_collectionWithInvalidSkills_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSkills(
                Arrays.asList(VALID_SKILLS_1, INVALID_SKILLS, VALID_SKILLS_2)));
    }

    @Test
    public void parseSkillsLists_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSkills(null));
    }

    @Test
    public void parseSkills_emptyList_returnsEmptySkills() throws Exception {
        List<String> emptyList = Collections.emptyList();
        Skills expectedSkills = new Skills(Collections.emptySet());
        assertEquals(expectedSkills, ParserUtil.parseSkills(emptyList));
    }

    @Test
    public void parseSkills_validSkills_returnsSkills() throws Exception {
        List<String> skillsList = Arrays.asList(VALID_SKILLS_1, VALID_SKILLS_2, VALID_SKILLS_3);
        Set<String> expectedSkillsSet = new HashSet<>(Arrays.asList(VALID_SKILLS_1, VALID_SKILLS_2, VALID_SKILLS_3));
        Skills expectedSkills = new Skills(expectedSkillsSet);
        assertEquals(expectedSkills, ParserUtil.parseSkills(skillsList));
    }

    @Test
    public void parseSkills_duplicateSkills_returnsUniqueSkills() throws Exception {
        List<String> skillsList = Arrays.asList(VALID_SKILLS_1, VALID_SKILLS_1, VALID_SKILLS_2);
        Set<String> expectedSkillsSet = new HashSet<>(Arrays.asList(VALID_SKILLS_1, VALID_SKILLS_2));
        Skills expectedSkills = new Skills(expectedSkillsSet);
        assertEquals(expectedSkills, ParserUtil.parseSkills(skillsList));
    }

    @Test
    public void parseSkills_invalidSkill_throwsParseException() {
        List<String> skillsList = Arrays.asList(VALID_SKILLS_1, INVALID_SKILLS, VALID_SKILLS_2);
        assertThrows(ParseException.class, () -> ParserUtil.parseSkills(skillsList));
    }

    @Test
    public void parsePreferences_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreferences(null));
    }

    @Test
    public void parseTermsOfService_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTermsOfService(null));
    }

}
