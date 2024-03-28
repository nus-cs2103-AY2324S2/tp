package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String GROUP_EMPTY = " " + PREFIX_GROUP;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "E0123456", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "E0123456 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "E0123456 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "E0123456" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "E0123456" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "E0123456" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "E0123456" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "E0123456" + INVALID_GROUP_DESC, Group.MESSAGE_CONSTRAINTS); // invalid group

        // invalid phone followed by valid email
        assertParseFailure(parser, "E0123456" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_GROUP} alone will reset the groups of the {@code Person} being edited,
        // parsing it together with a valid group results in error
        assertParseFailure(parser, "E0123456" + GROUP_DESC_FRIEND + GROUP_DESC_HUSBAND
                + GROUP_EMPTY, Group.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "E0123456" + GROUP_DESC_FRIEND + GROUP_EMPTY
                + GROUP_DESC_HUSBAND, Group.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "E0123456" + GROUP_EMPTY + GROUP_DESC_FRIEND
                + GROUP_DESC_HUSBAND, Group.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "E0123456" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_TAG_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        //Index targetIndex = INDEX_SECOND_PERSON;
        String defaultNusId = "E1234567";
        NusId nusId = new NusId(defaultNusId);

        String userInput = defaultNusId + PHONE_DESC_BOB + GROUP_DESC_HUSBAND
                + EMAIL_DESC_AMY + TAG_DESC_AMY + NAME_DESC_AMY + GROUP_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withTag(VALID_TAG_AMY)
                .withGroups(VALID_GROUP_HUSBAND, VALID_GROUP_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(nusId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        //Index targetIndex = INDEX_FIRST_PERSON;
        String defaultNusId = "E1234567";
        NusId nusId = new NusId(defaultNusId);
        String userInput = defaultNusId + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(nusId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        String defaultNusId = "E1234567";
        NusId nusId = new NusId(defaultNusId);

        // name
        //Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = defaultNusId + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(nusId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = defaultNusId + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(nusId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = defaultNusId + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(nusId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tag
        userInput = defaultNusId + TAG_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTag(VALID_TAG_AMY).build();
        expectedCommand = new EditCommand(nusId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // groups
        userInput = defaultNusId + GROUP_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withGroups(VALID_GROUP_FRIEND).build();
        expectedCommand = new EditCommand(nusId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonGroupValue_failure()

        String defaultNusId = "E1234567";

        // valid followed by invalid
        //Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = defaultNusId + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = defaultNusId + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated
        userInput = defaultNusId + PHONE_DESC_AMY + TAG_DESC_AMY + EMAIL_DESC_AMY
                + GROUP_DESC_FRIEND + PHONE_DESC_AMY + TAG_DESC_AMY + EMAIL_DESC_AMY + GROUP_DESC_FRIEND
                + PHONE_DESC_BOB + TAG_DESC_BOB + EMAIL_DESC_BOB + GROUP_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG));

        // multiple invalid values
        userInput = defaultNusId + INVALID_PHONE_DESC + INVALID_TAG_DESC + INVALID_EMAIL_DESC
                + INVALID_PHONE_DESC + INVALID_TAG_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG));
    }

    @Test
    public void parse_resetGroups_success() {
        String defaultNusId = "E1234567";
        NusId nusId = new NusId(defaultNusId);
        //Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = defaultNusId + GROUP_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGroups().build();
        EditCommand expectedCommand = new EditCommand(nusId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
