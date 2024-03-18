package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_ID = "a3";
    private static final String INVALID_NAME = "R@chel Lim Zhao";
    private static final String INVALID_PREFERRED_NAME = "R@chel";
    private static final String INVALID_FOOD_PREFERENCE = " ";
    private static final String INVALID_FAMILY_CONDITION = " ";
    private static final String INVALID_HOBBY = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_ID = "12344";
    private static final String VALID_NAME = "Rachel Lim Zhao";
    private static final String VALID_PREFERRED_NAME = "Rachel";
    private static final String VALID_FOOD_PREFERENCE = "Hor Fun";
    private static final String VALID_FAMILY_CONDITION = "Facing financial difficulty";
    private static final String VALID_HOBBY = "Singing";
    private static final String VALID_TAG_1 = "diabetes";
    private static final String VALID_TAG_2 = "highCholesterol";

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
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parsePatientHospitalId_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("102 a"));
    }

    @Test
    public void parsePatientHospitalId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePatientHospitalId((String) null));
    }

    @Test
    public void parsePatientHospitalId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePatientHospitalId(INVALID_ID));
    }

    @Test
    public void parseHospitalPatientId_validValueWithoutWhitespace_returnsId() throws Exception {
        PatientHospitalId expectedId = new PatientHospitalId(VALID_ID);
        assertEquals(expectedId, ParserUtil.parsePatientHospitalId(VALID_ID));
    }

    @Test
    public void parseHospitalPatientId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        PatientHospitalId expectedId = new PatientHospitalId(VALID_ID);
        assertEquals(expectedId, ParserUtil.parsePatientHospitalId(idWithWhitespace));
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
    public void parsePreferredName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePreferredName((String) null));
    }

    @Test
    public void parsePreferredName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePreferredName(INVALID_PREFERRED_NAME));
    }

    @Test
    public void parsePreferredName_validValueWithoutWhitespace_returnsPreferredName() throws Exception {
        PreferredName expectedPreferredName = new PreferredName(VALID_PREFERRED_NAME);
        assertEquals(expectedPreferredName, ParserUtil.parsePreferredName(VALID_PREFERRED_NAME));
    }

    @Test
    public void parsePreferredName_validValueWithWhitespace_returnsTrimmedPreferredName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_PREFERRED_NAME + WHITESPACE;
        PreferredName expectedPreferredName = new PreferredName(VALID_PREFERRED_NAME);
        assertEquals(expectedPreferredName, ParserUtil.parsePreferredName(nameWithWhitespace));
    }

    @Test
    public void parseFoodPreference_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFoodPreference((String) null));
    }

    @Test
    public void parseFoodPreference_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFoodPreference(INVALID_FOOD_PREFERENCE));
    }

    @Test
    public void parseFoodPreference_validValueWithoutWhitespace_returnsFoodPreference() throws Exception {
        FoodPreference expectedFoodPreference = new FoodPreference(VALID_FOOD_PREFERENCE);
        assertEquals(expectedFoodPreference, ParserUtil.parseFoodPreference(VALID_FOOD_PREFERENCE));
    }

    @Test
    public void parseFoodPreference_validValueWithWhitespace_returnsTrimmedFoodPreference() throws Exception {
        String foodPreferenceWithWhitespace = WHITESPACE + VALID_FOOD_PREFERENCE + WHITESPACE;
        FoodPreference expectedFoodPreference = new FoodPreference(VALID_FOOD_PREFERENCE);
        assertEquals(expectedFoodPreference, ParserUtil.parseFoodPreference(foodPreferenceWithWhitespace));
    }

    @Test
    public void parseFamilyCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFamilyCondition((String) null));
    }

    @Test
    public void parseFamilyCondition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFamilyCondition(INVALID_FAMILY_CONDITION));
    }

    @Test
    public void parseFamilyCondition_validValueWithoutWhitespace_returnsFamilyCondition() throws Exception {
        FamilyCondition expectedFamilyCondition = new FamilyCondition(VALID_FAMILY_CONDITION);
        assertEquals(expectedFamilyCondition, ParserUtil.parseFamilyCondition(VALID_FAMILY_CONDITION));
    }

    @Test
    public void parseFamilyCondition_validValueWithWhitespace_returnsTrimmedFamilyCondition() throws Exception {
        String familyConditionWithWhitespace = WHITESPACE + VALID_FAMILY_CONDITION + WHITESPACE;
        FamilyCondition expectedFamilyCondition = new FamilyCondition(VALID_FAMILY_CONDITION);
        assertEquals(expectedFamilyCondition, ParserUtil.parseFamilyCondition(familyConditionWithWhitespace));
    }

    @Test
    public void parseHobby_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHobby((String) null));
    }

    @Test
    public void parseHobby_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHobby(INVALID_HOBBY));
    }

    @Test
    public void parseHobby_validValueWithoutWhitespace_returnsHobby() throws Exception {
        Hobby expectedHobby = new Hobby(VALID_HOBBY);
        assertEquals(expectedHobby, ParserUtil.parseHobby(VALID_HOBBY));
    }

    @Test
    public void parseHobby_validValueWithWhitespace_returnsTrimmedHobby() throws Exception {
        String hobbyWithWhitespace = WHITESPACE + VALID_HOBBY + WHITESPACE;
        Hobby expectedHobby = new Hobby(VALID_HOBBY);
        assertEquals(expectedHobby, ParserUtil.parseHobby(hobbyWithWhitespace));
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
