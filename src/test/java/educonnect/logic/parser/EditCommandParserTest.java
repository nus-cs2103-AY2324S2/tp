package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_TELEGRAM_HANDLE_DESC;
import static educonnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static educonnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static educonnect.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static educonnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static educonnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static educonnect.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static educonnect.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static educonnect.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import educonnect.commons.core.index.Index;
import educonnect.logic.Messages;
import educonnect.logic.commands.EditCommand;
import educonnect.logic.commands.EditCommand.EditStudentDescriptor;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.StudentId;
import educonnect.model.student.Tag;
import educonnect.model.student.TelegramHandle;
import educonnect.testutil.EditStudentDescriptorBuilder;

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
        assertParseFailure(parser, "1" + INVALID_STUDENT_ID_DESC, StudentId.MESSAGE_CONSTRAINTS); // invalid student id
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_HANDLE_DESC,
            TelegramHandle.MESSAGE_CONSTRAINTS); // invalid telegram handle
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid student id followed by valid email
        assertParseFailure(parser, "1" + INVALID_STUDENT_ID_DESC + EMAIL_DESC_AMY, StudentId.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_TELEGRAM_HANDLE_AMY + VALID_STUDENT_ID_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_AMY)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student id
        userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // handle
        userInput = targetIndex.getOneBased() + TELEGRAM_HANDLE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_STUDENT_ID_DESC + STUDENT_ID_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_BOB + INVALID_STUDENT_ID_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + STUDENT_ID_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + STUDENT_ID_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + STUDENT_ID_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_STUDENT_ID_DESC
                + INVALID_TELEGRAM_HANDLE_DESC + INVALID_EMAIL_DESC
                + INVALID_STUDENT_ID_DESC + INVALID_TELEGRAM_HANDLE_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
