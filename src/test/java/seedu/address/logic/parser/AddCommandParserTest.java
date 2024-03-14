package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FAMILY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FAMILY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HOBBY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HOBBY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREFERRED_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREFERRED_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFERRED_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FOOD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FOOD_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FOOD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FAMILY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOBBY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOBBY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.Hobby;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PatientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withTags(VALID_TAG_DEPRESSION).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB
            + FOOD_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_DEPRESSION, new AddCommand(expectedPatient));


        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PatientBuilder(BOB).withTags(VALID_TAG_DEPRESSION, VALID_TAG_DIABETES)
                .build();
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + HOBBY_DESC_BOB +TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            new AddCommand(expectedPatientMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPatientString =  ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + HOBBY_DESC_BOB + TAG_DESC_DIABETES;

        // multiple patient hospital ID
        assertParseFailure(parser, ID_DESC_AMY + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple preferred names
        assertParseFailure(parser, PREFERRED_NAME_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRENAME));

        // multiple food preference
        assertParseFailure(parser, FOOD_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FOOD));

        // multiple family condition
        assertParseFailure(parser, FAMILY_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FAMILY));

        // multiple hobbies
        assertParseFailure(parser, HOBBY_DESC_AMY + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HOBBY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPatientString + ID_DESC_AMY + NAME_DESC_AMY + PREFERRED_NAME_DESC_AMY
                    + FOOD_DESC_AMY + FAMILY_DESC_AMY + HOBBY_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID, PREFIX_NAME, PREFIX_PRENAME, PREFIX_FOOD,
                    PREFIX_FAMILY, PREFIX_HOBBY));

        // invalid value followed by valid value

        // invalid patient hospital ID
        assertParseFailure(parser, INVALID_ID_DESC + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid preferred name
        assertParseFailure(parser, INVALID_PREFERRED_NAME_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRENAME));

        // invalid food preference
        assertParseFailure(parser, INVALID_FOOD_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FOOD));

        // invalid family condition
        assertParseFailure(parser, INVALID_FAMILY_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FAMILY));

        // invalid hobby
        assertParseFailure(parser, INVALID_HOBBY_DESC + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HOBBY));

        // valid value followed by invalid value

        // invalid patient hospital ID
        assertParseFailure(parser, validExpectedPatientString + INVALID_ID_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));

        // invalid name
        assertParseFailure(parser, validExpectedPatientString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid preferred name
        assertParseFailure(parser, validExpectedPatientString + INVALID_PREFERRED_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRENAME));

        // invalid food preference
        assertParseFailure(parser, validExpectedPatientString + INVALID_FOOD_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FOOD));

        // invalid family condition
        assertParseFailure(parser, validExpectedPatientString + INVALID_FAMILY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FAMILY));

        // invalid hobby
        assertParseFailure(parser, validExpectedPatientString + INVALID_HOBBY_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_HOBBY));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Patient expectedPatient = new PatientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, ID_DESC_AMY + NAME_DESC_AMY + PREFERRED_NAME_DESC_AMY + FOOD_DESC_AMY
                + FAMILY_DESC_AMY + HOBBY_DESC_AMY, new AddCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing patient hospital ID prefix
        assertParseFailure(parser, VALID_ID_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + HOBBY_DESC_BOB,
            expectedMessage);

        // missing name prefix
        assertParseFailure(parser, ID_DESC_BOB + VALID_NAME_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + HOBBY_DESC_BOB,
                expectedMessage);

        // missing preferred name prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + VALID_PREFERRED_NAME_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + HOBBY_DESC_BOB,
                expectedMessage);

        // missing food preference prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + VALID_FOOD_BOB
                + FAMILY_DESC_BOB + HOBBY_DESC_BOB,
                expectedMessage);

        // missing family condition prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + VALID_FAMILY_CONDITION_BOB + HOBBY_DESC_BOB,
                expectedMessage);

        // missing hobby prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + VALID_HOBBY_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ID_BOB + VALID_NAME_BOB + VALID_PREFERRED_NAME_BOB + VALID_FOOD_BOB
                + VALID_FAMILY_CONDITION_BOB + VALID_HOBBY_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid patient hospital ID
        assertParseFailure(parser, INVALID_ID_DESC + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
            + FAMILY_DESC_BOB + HOBBY_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            PatientHospitalId.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, ID_DESC_BOB + INVALID_NAME_DESC + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
            + FAMILY_DESC_BOB + HOBBY_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES, Name.MESSAGE_CONSTRAINTS);

        // invalid preferred name
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + INVALID_PREFERRED_NAME_DESC + FOOD_DESC_BOB
            + FAMILY_DESC_BOB + HOBBY_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            PreferredName.MESSAGE_CONSTRAINTS);

        // invalid food preference
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + INVALID_FOOD_DESC
            + FAMILY_DESC_BOB + HOBBY_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            FoodPreference.MESSAGE_CONSTRAINTS);

        // invalid family condition
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
            + INVALID_FAMILY_DESC + HOBBY_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            FamilyCondition.MESSAGE_CONSTRAINTS);

        // invalid hobby
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
                + FAMILY_DESC_BOB + INVALID_HOBBY_DESC + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            Hobby.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
            + FAMILY_DESC_BOB + HOBBY_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_DIABETES, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ID_DESC_BOB + INVALID_NAME_DESC + PREFERRED_NAME_DESC_BOB + FOOD_DESC_BOB
            + INVALID_FAMILY_DESC + HOBBY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ID_DESC_BOB + NAME_DESC_BOB + PREFERRED_NAME_DESC_BOB
                + FOOD_DESC_BOB + FAMILY_DESC_BOB + HOBBY_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
