package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FAMILY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FAMILY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FOOD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HOBBY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FAMILY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FOOD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HOBBY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREFERRED_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFERRED_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOBBY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ID_DESC, PatientHospitalId.MESSAGE_CONSTRAINTS); // invalid id
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        // invalid preferred name
        assertParseFailure(parser, "1" + INVALID_PREFERRED_NAME_DESC, PreferredName.MESSAGE_CONSTRAINTS);
        // invalid food preference
        assertParseFailure(parser, "1" + INVALID_FOOD_DESC, FoodPreference.MESSAGE_CONSTRAINTS);
        // invalid family condition
        assertParseFailure(parser, "1" + INVALID_FAMILY_DESC, FamilyCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_HOBBY_DESC, Hobby.MESSAGE_CONSTRAINTS); // invalid hobby
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Patient} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES + TAG_EMPTY,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_DEPRESSION + TAG_EMPTY + TAG_DESC_DIABETES,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES,
            Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ID_DESC + INVALID_NAME_DESC + INVALID_PREFERRED_NAME_DESC
                + VALID_FOOD_AMY + VALID_FAMILY_CONDITION_AMY + VALID_HOBBY_AMY, PatientHospitalId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PATIENT;
        String userInput = targetIndex.getOneBased() + ID_DESC_AMY + NAME_DESC_AMY + TAG_DESC_DEPRESSION
                + PREFERRED_NAME_DESC_AMY + FOOD_DESC_AMY + FAMILY_DESC_AMY + HOBBY_DESC_AMY + TAG_DESC_DIABETES;

        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
            .withPatientHospitalId(VALID_ID_AMY).withName(VALID_NAME_AMY).withPreferredName(VALID_PREFERRED_NAME_AMY)
            .withFoodPreference(VALID_FOOD_AMY).withFamilyCondition(VALID_FAMILY_CONDITION_AMY)
            .withHobby(VALID_HOBBY_AMY).withTags(VALID_TAG_DIABETES, VALID_TAG_DEPRESSION).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + FAMILY_DESC_BOB + HOBBY_DESC_AMY;

        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
            .withFamilyCondition(VALID_FAMILY_CONDITION_BOB)
            .withHobby(VALID_HOBBY_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PATIENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditPatientDescriptor descriptor =
                new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // preferred name
        userInput = targetIndex.getOneBased() + PREFERRED_NAME_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withPreferredName(VALID_PREFERRED_NAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // food preference
        userInput = targetIndex.getOneBased() + FOOD_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withFoodPreference(VALID_FOOD_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // family condition
        userInput = targetIndex.getOneBased() + FAMILY_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withFamilyCondition(VALID_FAMILY_CONDITION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // hobby
        userInput = targetIndex.getOneBased() + HOBBY_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withHobby(VALID_HOBBY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_DEPRESSION;
        descriptor = new EditPatientDescriptorBuilder().withTags(VALID_TAG_DEPRESSION).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + INVALID_ID_DESC + ID_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + ID_DESC_BOB + INVALID_ID_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + ID_DESC_AMY + TAG_DESC_DIABETES + ID_DESC_AMY + TAG_DESC_DIABETES
            + ID_DESC_BOB + TAG_DESC_DEPRESSION + TAG_DESC_DIABETES;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_ID_DESC + INVALID_ID_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PID));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PATIENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
