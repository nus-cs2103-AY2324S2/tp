package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.FAMILY_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.FAMILY_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_FAMILY_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_INCOME_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.NAME_DESC_AMY_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_AMY_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.CommandTestUtil;
import seedu.realodex.logic.commands.EditCommand;
import seedu.realodex.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.tag.Tag;
import seedu.realodex.testutil.EditPersonDescriptorBuilder;


public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY_FIRST_LETTER_CAPS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY_CAPS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY_CAPS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_INCOME_DESC, Income.MESSAGE_CONSTRAINTS); // invalid income
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_FAMILY_DESC, Family.MESSAGE_CONSTRAINTS); // invalid family
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_BOB
                + CommandTestUtil.TAG_DESC_BOB + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_BOB + TAG_EMPTY
                + CommandTestUtil.TAG_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_BOB
                + CommandTestUtil.TAG_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased()
                + PHONE_DESC_BOB
                + CommandTestUtil.TAG_DESC_BOB
                + INCOME_DESC_AMY
                + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY
                + NAME_DESC_AMY_CAPS
                + FAMILY_DESC_BOB
                + TAG_DESC_BOB
                + REMARK_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY_FIRST_LETTER_CAPS)
                .withPhone(VALID_PHONE_BOB)
                .withIncome(VALID_INCOME_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withFamily(VALID_FAMILY_BOB)
                .withTags(VALID_TAG_BOB)
                .withRemark(VALID_REMARK_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY_CAPS;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(
                VALID_NAME_AMY_FIRST_LETTER_CAPS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // income
        userInput = targetIndex.getOneBased() + INCOME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withIncome(VALID_INCOME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // family
        userInput = targetIndex.getOneBased() + FAMILY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withFamily(VALID_FAMILY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // 1 tag
        userInput = targetIndex.getOneBased() + TAG_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark
        userInput = targetIndex.getOneBased() + REMARK_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withRemark(VALID_REMARK_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // 2 tags
        userInput = targetIndex.getOneBased() + TAG_DESC_BOB + TAG_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_BOB, VALID_TAG_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));


        // multiple valid fields repeated
        userInput =
                targetIndex.getOneBased()
                        + PHONE_DESC_AMY
                        + INCOME_DESC_AMY
                        + INCOME_DESC_AMY
                        + ADDRESS_DESC_AMY
                        + EMAIL_DESC_AMY
                        + FAMILY_DESC_AMY
                        + FAMILY_DESC_AMY
                        + TAG_DESC_BOB
                        + PHONE_DESC_AMY
                        + ADDRESS_DESC_AMY
                        + EMAIL_DESC_AMY
                        + TAG_DESC_BOB
                        + PHONE_DESC_BOB
                        + ADDRESS_DESC_BOB
                        + EMAIL_DESC_BOB
                        + REMARK_DESC_AMY
                        + REMARK_DESC_BOB
                        + CommandTestUtil.TAG_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                                                             PREFIX_INCOME, PREFIX_FAMILY, PREFIX_REMARK));

        // multiple invalid values
        userInput = targetIndex.getOneBased()
                + INVALID_PHONE_DESC
                + INVALID_INCOME_DESC
                + INVALID_FAMILY_DESC
                + INVALID_ADDRESS_DESC
                + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC
                + INVALID_ADDRESS_DESC
                + INVALID_EMAIL_DESC
                + INVALID_INCOME_DESC
                + INVALID_FAMILY_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                                                             PREFIX_INCOME, PREFIX_FAMILY));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();

        assertParseFailure(parser, userInput, String.format(Tag.MESSAGE_CONSTRAINTS, targetIndex.getOneBased()));
    }
}
