package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IDENTITY_CARD_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IDENTITY_CARD_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditPersonDescriptorBuilder;

@Disabled("Boo")
public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private static final String IC_INVALID =
            String.format(IdentityCardNumber.MESSAGE_CONSTRAINTS);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, "S0123456A", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // too short string
        assertParseFailure(parser, "S09" + NAME_DESC_AMY, IC_INVALID);

        // Wrong format
        assertParseFailure(parser, "P0123456A" + NAME_DESC_AMY, IC_INVALID);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string", IC_INVALID);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", IC_INVALID);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "S0123456A" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "S0123456A" + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "S0123456A" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "S0123456A" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser, "S0123456A" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "S0123456A" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_IDENTITY_CARD_NUMBER_AMY + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + IC_DESC_AMY + AGE_DESC_AMY + SEX_DESC_AMY + ADDRESS_DESC_AMY;
        IdentityCardNumberMatchesPredicate ic = new IdentityCardNumberMatchesPredicate(
                new IdentityCardNumber(VALID_IDENTITY_CARD_NUMBER_AMY));

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withIC(VALID_IDENTITY_CARD_NUMBER_AMY)
                .withSex(VALID_SEX_AMY).withAge(VALID_AGE_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(ic, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = VALID_IDENTITY_CARD_NUMBER_AMY + NAME_DESC_AMY + PHONE_DESC_AMY;
        IdentityCardNumberMatchesPredicate ic = new IdentityCardNumberMatchesPredicate(
                new IdentityCardNumber(VALID_IDENTITY_CARD_NUMBER_AMY));

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).build();
        EditCommand expectedCommand = new EditCommand(ic, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        IdentityCardNumberMatchesPredicate ic = new IdentityCardNumberMatchesPredicate(
                new IdentityCardNumber(VALID_IDENTITY_CARD_NUMBER_AMY));
        // name
        String userInput = VALID_IDENTITY_CARD_NUMBER_AMY + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sex
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + SEX_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withSex(VALID_SEX_AMY).build();
        expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // age
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + AGE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAge(VALID_AGE_AMY).build();
        expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // IC number
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + IC_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withIC(VALID_IDENTITY_CARD_NUMBER_AMY).build();
        expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(ic, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        String userInput = VALID_IDENTITY_CARD_NUMBER_BOB + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = VALID_IDENTITY_CARD_NUMBER_AMY + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = VALID_IDENTITY_CARD_NUMBER_BOB + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = VALID_IDENTITY_CARD_NUMBER_BOB + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }
}