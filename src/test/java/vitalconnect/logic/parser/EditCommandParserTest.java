package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static vitalconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static vitalconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static vitalconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static vitalconnect.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static vitalconnect.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.Messages;
import vitalconnect.logic.commands.EditCommand;
import vitalconnect.logic.commands.EditCommand.EditPersonDescriptor;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.tag.Tag;
import vitalconnect.testutil.EditPersonDescriptorBuilder;

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_NRIC_DESC, Nric.MESSAGE_CONSTRAINTS); // invalid nric
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid name followed by valid nric
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + NRIC_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_NRIC_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + NRIC_DESC_BOB + TAG_DESC_HUSBAND
                + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withNric(VALID_NRIC_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + NRIC_DESC_BOB + NAME_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withNric(VALID_NRIC_BOB)
                .withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // nric
        userInput = targetIndex.getOneBased() + NRIC_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withNric(VALID_NRIC_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BOB + INVALID_NRIC_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + INVALID_NRIC_DESC + NAME_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + NRIC_DESC_AMY + NRIC_DESC_AMY
                + TAG_DESC_FRIEND + NRIC_DESC_AMY + TAG_DESC_FRIEND
                + NRIC_DESC_BOB + NRIC_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_NRIC_DESC + INVALID_NRIC_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
