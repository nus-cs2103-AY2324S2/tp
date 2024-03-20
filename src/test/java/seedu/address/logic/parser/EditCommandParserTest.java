package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_2B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_GROUP2B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudentIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudentIds.ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudentIds.ID_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;
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
        assertParseFailure(parser, "A1234567A", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid id
        assertParseFailure(parser, "a123z" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "A0123456A some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "A0123456A i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "A1234567A" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "A1234567A" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "A1234567A" + INVALID_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS); // invalid grade
        assertParseFailure(parser, "A1234567A" + INVALID_GROUP_DESC, Group.MESSAGE_CONSTRAINTS); // invalid group
        assertParseFailure(parser, "A1234567A" + INVALID_STUDENTID_DESC,
                StudentId.MESSAGE_CONSTRAINTS); // invalid student id

        // while parsing {@code PREFIX_GROUP} alone will reset the groups of the {@code Person} being edited,
        // parsing it together with a valid group results in error
        assertParseFailure(parser, "A1234567A" + GROUP_DESC_2B + GROUP_DESC_1 + GROUP_EMPTY, Group.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "A1234567A" + GROUP_DESC_2B + GROUP_EMPTY + GROUP_DESC_1, Group.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "A1234567A" + GROUP_EMPTY + GROUP_DESC_2B + GROUP_DESC_1, Group.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "A1234567A" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_GRADE_AMY + VALID_STUDENTID_AMY, Name.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        StudentId targetId = ID_SECOND_PERSON;

        String userInput = targetId + GROUP_DESC_1 + EMAIL_DESC_AMY
                + GRADE_DESC_AMY + NAME_DESC_AMY + GROUP_DESC_2B + STUDENTID_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withStudentId(VALID_STUDENTID_AMY)
                .withGrade(VALID_GRADE_AMY).withGroups(VALID_GROUP_GROUP1, VALID_GROUP_GROUP2B).build();

        EditCommand expectedCommand = new EditCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        StudentId targetId = ID_FIRST_PERSON;
        String userInput = targetId + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        StudentId targetId = ID_THIRD_PERSON;
        String userInput = targetId + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetId + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student id
        userInput = targetId + STUDENTID_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withStudentId(VALID_STUDENTID_AMY).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // grade
        userInput = targetId + GRADE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGrade(VALID_GRADE_AMY).build();
        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // groups
        userInput = targetId + GROUP_DESC_2B;
        descriptor = new EditPersonDescriptorBuilder().withGroups(VALID_GROUP_GROUP2B).build();

        expectedCommand = new EditCommand(targetId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonGroupValue_failure()


        StudentId targetId = ID_FIRST_PERSON;

        // multiple valid fields repeated
        String userInput = targetId + EMAIL_DESC_AMY + GROUP_DESC_2B + GRADE_DESC_AMY
                + EMAIL_DESC_AMY + GROUP_DESC_2B + EMAIL_DESC_BOB + GROUP_DESC_1;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple invalid values
        userInput = targetId + INVALID_EMAIL_DESC
                + INVALID_GRADE_DESC + INVALID_STUDENTID_DESC + INVALID_GRADE_DESC
                + INVALID_EMAIL_DESC + INVALID_STUDENTID_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL, PREFIX_GRADE,
                        PREFIX_STUDENTID));

    }

    @Test
    public void parse_resetGroups_success() {
        StudentId targetId = ID_THIRD_PERSON;
        String userInput = targetId + GROUP_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withGroups().build();
        EditCommand expectedCommand = new EditCommand(targetId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
