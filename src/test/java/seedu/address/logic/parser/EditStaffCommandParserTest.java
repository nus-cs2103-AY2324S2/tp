package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMMISSION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.messages.Messages;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditStaffDescriptorBuilder;
import seedu.address.testutil.PersonUtil;

public class EditStaffCommandParserTest {
    private EditStaffCommandParser parser = new EditStaffCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + " "
            + " " + PREFIX_FIELD + "{" + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_NAME,
                Name.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_missingName_failure() {
        // no field specified
        String userInput = EditCommand.COMMAND_WORD + " "
                + " " + PREFIX_FIELD + "{" + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_MISSING_NAME,
                EditStaffCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingField_failure() {
        // no field specified
        String userInput = EditCommand.COMMAND_WORD + " " + PREFIX_NAME;
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_MISSING_FIELD,
                EditStaffCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSingleField_failure() {
        // specified invalid field (name)
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + NAME_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact name is not allowed!"));
        // specified invalid field (product)
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + PRODUCT_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact product is not allowed for staff"));
        // specified invalid field (price)
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + PRICE_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact price is not allowed for staff"));
        // specified invalid field (skill)
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + SKILL_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact skill is not allowed for staff"));
        // specified invalid field (commission)
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact commission is not allowed for staff"));
    }

    @Test
    public void checkMutipleInvalidField() {
        // specified two invalid field (price and commission)
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + PRICE_DESC_AMY + COMMISSION_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact price and commission is not allowed for staff"));
        // specified three invalid field (name, price and commission)
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY + PRICE_DESC_AMY + NAME_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact name is not allowed!"));
        // specified three invalid field (skill, price and commission)
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + COMMISSION_DESC_AMY + PRICE_DESC_AMY + SKILL_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact price, skill and commission is not allowed for staff"));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid name
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + " "
            + " " + PREFIX_FIELD + "{" + PHONE_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser,
                userInput, String.format(EditMessages.MESSAGE_EDIT_INVALID_NAME, Name.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + INVALID_PHONE_DESC + " }";
        assertParseFailure(parser, userInput,
                String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, Phone.MESSAGE_CONSTRAINTS)); // invalid phone
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + INVALID_EMAIL_DESC + " }";
        assertParseFailure(parser, userInput,
                String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, Email.MESSAGE_CONSTRAINTS)); // invalid email
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + INVALID_ADDRESS_DESC + " }";
        assertParseFailure(parser, userInput,
                EditMessages.MESSAGE_EDIT_EMPTY_FIELD); // invalid address

        // invalid phone followed by valid email
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME + "Staff1"
            + " " + PREFIX_FIELD + "{" + INVALID_PHONE_DESC + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput,
                String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, Phone.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName("Staff1")
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_STAFF).build();
        EditStaffCommand expectedCommand = new EditStaffCommand(new Name("Staff1"), descriptor);
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
            + "Staff1" + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditStaffDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_STAFF).build();
        EditStaffCommand expectedCommand = new EditStaffCommand(ALICESTAFF.getName(), descriptor);
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
            + ALICESTAFF.getName() + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditStaffDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // phone
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder()
                .withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_STAFF).build();
        EditStaffCommand expectedCommand = new EditStaffCommand(CARL.getName(), descriptor);
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditStaffDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        descriptor = new EditStaffDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_STAFF).build();
        expectedCommand = new EditStaffCommand(CARL.getName(), descriptor);
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditStaffDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        descriptor = new EditStaffDescriptorBuilder().withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_STAFF).build();
        expectedCommand = new EditStaffCommand(CARL.getName(), descriptor);
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditStaffDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        String userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + INVALID_PHONE_DESC + PHONE_DESC_BOB + " }";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid followed by invalid
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + PHONE_DESC_BOB + INVALID_PHONE_DESC + " }";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = EditStaffCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC + " }";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }
}
