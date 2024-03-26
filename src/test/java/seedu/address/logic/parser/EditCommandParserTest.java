package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FIRSTNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BANKDETAILS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FIRSTNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LASTNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYRATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LASTNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAYRATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_WAITER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRSTNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYRATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WAITER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_FIRSTNAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, VALID_PHONE_AMY, EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid number
        assertParseFailure(parser, "0" + FIRSTNAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, VALID_PHONE_AMY + "some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, VALID_PHONE_AMY + "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_FIRSTNAME_DESC,
            Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_LASTNAME_DESC,
            Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_PHONE_DESC,
            Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_ADDRESS_DESC,
            Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_TAG_DESC,
            Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_BANKDETAILS_DESC,
            BankDetails.MESSAGE_CONSTRAINTS); // invalid bank details
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_SEX_DESC,
            Sex.MESSAGE_CONSTRAINTS); // invalid sex
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_PAYRATE_DESC,
                PayRate.MESSAGE_CONSTRAINTS); // invalid employment type

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, VALID_PHONE_AMY + TAG_DESC_COOK + TAG_DESC_WAITER + TAG_EMPTY,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_PHONE_AMY + TAG_DESC_COOK + TAG_EMPTY + TAG_DESC_WAITER,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_PHONE_AMY + TAG_EMPTY + TAG_DESC_COOK + TAG_DESC_WAITER,
            Tag.MESSAGE_CONSTRAINTS);
        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_PHONE_AMY + INVALID_FIRSTNAME_DESC + INVALID_PAYRATE_DESC
                + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Phone targetPhone = new Phone(VALID_PHONE_AMY);
        String userInput = VALID_PHONE_AMY + PHONE_DESC_AMY + TAG_DESC_WAITER
                + ADDRESS_DESC_AMY + FIRSTNAME_DESC_AMY + TAG_DESC_COOK + LASTNAME_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withFirstName(VALID_FIRSTNAME_AMY)
                .withLastName(VALID_LASTNAME_AMY).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_WAITER, VALID_TAG_COOK).build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Phone targetPhone = new Phone(VALID_PHONE_BOB);
        String userInput = VALID_PHONE_BOB + PHONE_DESC_BOB + PAYRATE_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withPayRate(VALID_PAYRATE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Phone targetPhone = new Phone(VALID_PHONE_AMY);
        String userInput = VALID_PHONE_AMY + FIRSTNAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withFirstName(VALID_FIRSTNAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = VALID_PHONE_AMY + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = VALID_PHONE_AMY + TAG_DESC_COOK;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_COOK).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = VALID_PHONE_AMY + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetPhone, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Phone targetPhone = new Phone(VALID_PHONE_AMY);
        String userInput = targetPhone + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetPhone + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = targetPhone + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + TAG_DESC_COOK + PHONE_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_COOK
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_WAITER;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = targetPhone + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC
                + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_ADDRESS));
    }

    @Test
    public void parse_resetTags_success() {
        Phone targetPhone = new Phone(VALID_PHONE_AMY);
        String userInput = targetPhone + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetPhone, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
