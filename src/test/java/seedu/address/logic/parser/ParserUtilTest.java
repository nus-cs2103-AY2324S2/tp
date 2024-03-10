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
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Skill;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_EMPLOYMENT = "invalid employment";
    private static final String INVALID_SALARY = "20";
    private static final String INVALID_PRODUCT = " ";
    private static final String INVALID_PRICE = "20";
    private static final String INVALID_SKILL = " ";
    private static final String INVALID_COMMISSION = "20";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_EMPLOYMENT = "part-time";
    private static final String VALID_SALARY = "$20/hr";
    private static final String VALID_PRODUCT = "pooch food";
    private static final String VALID_PRICE = "$20/bag";
    private static final String VALID_SKILL = "train dog";
    private static final String VALID_COMMISSION = "$20/hr";
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
    public void parseEmployment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmployment((String) null));
    }

    @Test
    public void parseEmployment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmployment(INVALID_EMPLOYMENT));
    }

    @Test
    public void parseEmployment_validValueWithoutWhitespace_returnsEmployment() throws Exception {
        Employment expectedEmployment = new Employment(VALID_EMPLOYMENT);
        assertEquals(expectedEmployment, ParserUtil.parseEmployment(VALID_EMPLOYMENT));
    }

    @Test
    public void parseEmployment_validValueWithWhitespace_returnsTrimmedEmployment() throws Exception {
        String employmentWithWhitespace = WHITESPACE + VALID_EMPLOYMENT + WHITESPACE;
        Employment expectedEmployment = new Employment(VALID_EMPLOYMENT);
        assertEquals(expectedEmployment, ParserUtil.parseEmployment(employmentWithWhitespace));
    }

    @Test
    public void parseSalary_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSalary((String) null));
    }

    @Test
    public void parseSalary_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSalary(INVALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithoutWhitespace_returnsSalary() throws Exception {
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(VALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithWhitespace_returnsTrimmedSalary() throws Exception {
        String salaryWithWhitespace = WHITESPACE + VALID_SALARY + WHITESPACE;
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(salaryWithWhitespace));
    }

    @Test
    public void parseProduct_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSalary((String) null));
    }

    @Test
    public void parseProduct_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProduct(INVALID_PRODUCT));
    }

    @Test
    public void parseProduct_validValueWithoutWhitespace_returnsProduct() throws Exception {
        Product expectedProduct = new Product(VALID_PRODUCT);
        assertEquals(expectedProduct, ParserUtil.parseProduct(VALID_PRODUCT));
    }

    @Test
    public void parseProduct_validValueWithWhitespace_returnsTrimmedProduct() throws Exception {
        String productWithWhitespace = WHITESPACE + VALID_PRODUCT + WHITESPACE;
        Product expectedProduct = new Product(VALID_PRODUCT);
        assertEquals(expectedProduct, ParserUtil.parseProduct(productWithWhitespace));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice((String) null));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(VALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsTrimmedPrice() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(priceWithWhitespace));
    }

    @Test
    public void parseSkill_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSkill((String) null));
    }

    @Test
    public void parseSkill_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSkill(INVALID_SKILL));
    }

    @Test
    public void parseSkill_validValueWithoutWhitespace_returnsSkill() throws Exception {
        Skill expectedSkill = new Skill(VALID_SKILL);
        assertEquals(expectedSkill, ParserUtil.parseSkill(VALID_SKILL));
    }

    @Test
    public void parseSkill_validValueWithWhitespace_returnsTrimmedSkill() throws Exception {
        String skillWithWhitespace = WHITESPACE + VALID_SKILL + WHITESPACE;
        Skill expectedSkill = new Skill(VALID_SKILL);
        assertEquals(expectedSkill, ParserUtil.parseSkill(skillWithWhitespace));
    }

    @Test
    public void parseCommission_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCommission((String) null));
    }

    @Test
    public void parseCommission_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCommission(INVALID_COMMISSION));
    }

    @Test
    public void parseCommission_validValueWithoutWhitespace_returnsCommission() throws Exception {
        Commission expectedCommission = new Commission(VALID_COMMISSION);
        assertEquals(expectedCommission, ParserUtil.parseCommission(VALID_COMMISSION));
    }

    @Test
    public void parseCommission_validValueWithWhitespace_returnsTrimmedCommission() throws Exception {
        String commissionWithWhitespace = WHITESPACE + VALID_COMMISSION + WHITESPACE;
        Commission expectedCommission = new Commission(VALID_COMMISSION);
        assertEquals(expectedCommission, ParserUtil.parseCommission(commissionWithWhitespace));
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
