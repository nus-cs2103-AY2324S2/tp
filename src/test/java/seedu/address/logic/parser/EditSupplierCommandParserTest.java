package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditSupplierDescriptorBuilder;
import seedu.address.testutil.PersonUtil;

public class EditSupplierCommandParserTest {
    private EditSupplierCommandParser parser = new EditSupplierCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + " "
            + " " + PREFIX_FIELD + "{" + " }";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid name
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + " "
            + " " + PREFIX_FIELD + "{" + PHONE_DESC_AMY
            + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + "Supplier1"
            + " " + PREFIX_FIELD + "{" + INVALID_PHONE_DESC + " }";
        assertParseFailure(parser, userInput, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + "Supplier1"
            + " " + PREFIX_FIELD + "{" + INVALID_EMAIL_DESC + " }";
        assertParseFailure(parser, userInput, Email.MESSAGE_CONSTRAINTS); // invalid email
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + "Supplier1"
            + " " + PREFIX_FIELD + "{" + INVALID_ADDRESS_DESC + " }";
        assertParseFailure(parser, userInput, EditSupplierCommand.MESSAGE_NOT_EDITED); // invalid address

        // invalid phone followed by valid email
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + "Supplier1"
            + " " + PREFIX_FIELD + "{" + INVALID_PHONE_DESC + EMAIL_DESC_AMY + " }";
        assertParseFailure(parser, userInput, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME + "Supplier1"
            + " " + PREFIX_FIELD + "{" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
            + VALID_ADDRESS_AMY + VALID_PHONE_AMY + " }";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withName("Supplier1")
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_SUPPLIER).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(new Name("Supplier1"), descriptor);
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
            + "Supplier1" + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditSupplierDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_SUPPLIER).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(ALICESUPPLIER.getName(), descriptor);
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
            + ALICESUPPLIER.getName() + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditSupplierDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // phone
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder()
                .withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_SUPPLIER).build();
        EditSupplierCommand expectedCommand = new EditSupplierCommand(CARL.getName(), descriptor);
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditSupplierDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        descriptor = new EditSupplierDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_SUPPLIER).build();
        expectedCommand = new EditSupplierCommand(CARL.getName(), descriptor);
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditSupplierDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        descriptor = new EditSupplierDescriptorBuilder().withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_SUPPLIER).build();
        expectedCommand = new EditSupplierCommand(CARL.getName(), descriptor);
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditSupplierDescriptorDetails(descriptor) + "}";
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        String userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + INVALID_PHONE_DESC + PHONE_DESC_BOB + " }";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid followed by invalid
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + PHONE_DESC_BOB + INVALID_PHONE_DESC + " }";

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + " }";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = EditSupplierCommand.COMMAND_WORD + " " + PREFIX_NAME
                + CARL.getName() + " " + PREFIX_FIELD + "{"
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC + " }";

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }
}
