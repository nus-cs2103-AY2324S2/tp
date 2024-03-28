package scrolls.elder.logic.parser;

import org.junit.jupiter.api.Test;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.CommandTestUtil;
import scrolls.elder.logic.commands.EditCommand;
import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Role;
import scrolls.elder.model.tag.Tag;
import scrolls.elder.testutil.EditPersonDescriptorBuilder;
import scrolls.elder.testutil.TypicalIndexes;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String ROLE_VOLUNTEER = " r/volunteer ";
    private static final String ROLE_BEFRIENDEE = " r/befriendee ";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no role specified
        CommandParserTestUtil.assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER, EditCommand.MESSAGE_NOT_EDITED);

        // no index, no role, no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5" + ROLE_VOLUNTEER + CommandTestUtil.NAME_DESC_AMY,
                MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0" + ROLE_VOLUNTEER + CommandTestUtil.NAME_DESC_AMY,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string" + ROLE_VOLUNTEER,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string" + ROLE_VOLUNTEER,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name

        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone

        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                + CommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email

        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                + CommandTestUtil.INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_ROLE_DESC,
                Role.MESSAGE_CONSTRAINTS); // invalid role

        // invalid phone followed by valid email
        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + ROLE_VOLUNTEER + CommandTestUtil.TAG_DESC_FRIEND
                        + CommandTestUtil.TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);

        CommandParserTestUtil.assertParseFailure(parser,
                "1" + ROLE_VOLUNTEER + CommandTestUtil.TAG_DESC_FRIEND + TAG_EMPTY
                        + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        CommandParserTestUtil.assertParseFailure(parser,
                "1" + ROLE_VOLUNTEER + TAG_EMPTY + CommandTestUtil.TAG_DESC_FRIEND
                        + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, "1" + ROLE_VOLUNTEER
                        + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_EMAIL_DESC
                        + CommandTestUtil.VALID_ADDRESS_AMY + CommandTestUtil.VALID_PHONE_AMY,
                        Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_PERSON;

        String userInput = targetIndex.getOneBased() + ROLE_VOLUNTEER
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND;

        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder()
                        .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER)
                        .withName(CommandTestUtil.VALID_NAME_AMY)
                        .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                        .withEmail(CommandTestUtil.VALID_EMAIL_AMY)
                        .withAddress(CommandTestUtil.VALID_ADDRESS_AMY)
                        .withTags(CommandTestUtil.VALID_TAG_HUSBAND, CommandTestUtil.VALID_TAG_FRIEND)
                        .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + ROLE_VOLUNTEER
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY;

        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder()
                        .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER)
                        .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                        .withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + ROLE_VOLUNTEER + CommandTestUtil.NAME_DESC_AMY;
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER)
                .withName(CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + ROLE_VOLUNTEER + CommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder()
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER)
                .withPhone(CommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + ROLE_VOLUNTEER + CommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder()
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER)
                .withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ROLE_BEFRIENDEE + CommandTestUtil.ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder()
                .withRole(CommandTestUtil.VALID_ROLE_BEFRIENDEE)
                .withAddress(CommandTestUtil.VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + ROLE_BEFRIENDEE + CommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder()
                .withRole(CommandTestUtil.VALID_ROLE_BEFRIENDEE)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput =
                targetIndex.getOneBased() + ROLE_BEFRIENDEE
                        + CommandTestUtil.INVALID_PHONE_DESC
                        + CommandTestUtil.PHONE_DESC_BOB;
        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + ROLE_BEFRIENDEE
                + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.INVALID_PHONE_DESC;
        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput =
                targetIndex.getOneBased() + ROLE_BEFRIENDEE
                        + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.ADDRESS_DESC_AMY
                        + CommandTestUtil.EMAIL_DESC_AMY
                        + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.EMAIL_DESC_AMY
                        + CommandTestUtil.TAG_DESC_FRIEND
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                        + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND;

        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS));

        // multiple invalid values
        userInput =
                targetIndex.getOneBased() + ROLE_BEFRIENDEE
                        + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.INVALID_ADDRESS_DESC
                        + CommandTestUtil.INVALID_EMAIL_DESC
                        + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.INVALID_ADDRESS_DESC
                        + CommandTestUtil.INVALID_EMAIL_DESC;
        CommandParserTestUtil.assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + ROLE_VOLUNTEER + TAG_EMPTY;

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER).withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
