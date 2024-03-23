package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATEOFBIRTH_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdatePersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.testutil.UpdatePersonDescriptorBuilder;

public class UpdateCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no nric specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        /* no field specified
        assertParseFailure(parser, VALID_NRIC_AMY, UpdateCommand.MESSAGE_NOT_UPDATED);
         */

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // one lowercase letter
        assertParseFailure(parser, "t0182991C" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "T0182991c" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // 2 lowercase letters
        assertParseFailure(parser, "t0182991c" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "T0182991C" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid name
        assertParseFailure(parser, "T0182991C" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "T0182991C" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "T0182991C" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);
        // invalid address

        // invalid phone followed by valid email
        assertParseFailure(
                parser, "T0182991C" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        /*
        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(
                parser, "T0182991C" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, "T0182991C" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, "T0182991C" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
         */

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "T0182991C" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = VALID_NRIC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + DATEOFBIRTH_DESC_AMY + SEX_DESC_AMY + STATUS_DESC_AMY;

        UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder().withNric(VALID_NRIC_AMY)
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY)
                .withDateOfBirth(VALID_DATEOFBIRTH_AMY).withSex(VALID_SEX_AMY).withStatus(VALID_STATUS_AMY).build();
        UpdateCommand expectedCommand = new UpdateCommand(new Nric(VALID_NRIC_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = VALID_NRIC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY;

        UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder().withNric(VALID_NRIC_AMY)
                .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY).build();
        UpdateCommand expectedCommand = new UpdateCommand(new Nric(VALID_NRIC_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = VALID_NRIC_AMY + NAME_DESC_AMY;
        UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder()
                .withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY).build();
        UpdateCommand expectedCommand = new UpdateCommand(new Nric(VALID_NRIC_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = VALID_NRIC_AMY + PHONE_DESC_AMY;
        descriptor = new UpdatePersonDescriptorBuilder().withNric(VALID_NRIC_AMY).withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new UpdateCommand(new Nric(VALID_NRIC_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = VALID_NRIC_AMY + ADDRESS_DESC_AMY;
        descriptor = new UpdatePersonDescriptorBuilder().withNric(VALID_NRIC_AMY)
                .withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new UpdateCommand(new Nric(VALID_NRIC_AMY), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        String userInput = VALID_NRIC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = VALID_NRIC_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = VALID_NRIC_BOB + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = VALID_NRIC_BOB + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }

    /*
    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder().withTags().build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
     */
}
