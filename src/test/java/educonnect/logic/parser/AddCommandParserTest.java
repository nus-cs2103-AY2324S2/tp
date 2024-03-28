package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_TELEGRAM_HANDLE_DESC;
import static educonnect.logic.commands.CommandTestUtil.INVALID_TIMETABLE_DESC;
import static educonnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static educonnect.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static educonnect.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static educonnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static educonnect.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.TIMETABLE_DESC_VALID1;
import static educonnect.logic.commands.CommandTestUtil.TIMETABLE_DESC_VALID2;
import static educonnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE;
import static educonnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static educonnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static educonnect.testutil.TypicalStudents.AMY;
import static educonnect.testutil.TypicalStudents.BOB;
import static educonnect.testutil.TypicalTimetableAndValues.DEFAULT_EMPTY_TIMETABLE;
import static educonnect.testutil.TypicalTimetableAndValues.VALID_TIMETABLE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import educonnect.logic.Messages;
import educonnect.logic.commands.AddCommand;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.Tag;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.timetable.Timetable;
import educonnect.testutil.StudentBuilder;
import educonnect.testutil.TypicalTimetableAndValues;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB)
                .withTags(VALID_TAG_FRIEND)
                .withTimetable(VALID_TIMETABLE_1)
                .buildNoLink();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + TAG_DESC_FRIEND + TIMETABLE_DESC_VALID1,
                new AddCommand(expectedStudent));


        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .buildNoLink();
        assertParseSuccess(parser,
                NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND + TIMETABLE_DESC_VALID2,
                new AddCommand(expectedStudentMultipleTags));

        // no timetable in command, defaults to empty timetable
        Student expectedStudentWithNoTimetable = new StudentBuilder(AMY)
                .withTimetable(DEFAULT_EMPTY_TIMETABLE).buildNoLink();
        assertParseSuccess(parser,
                NAME_DESC_AMY + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_HANDLE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedStudentWithNoTimetable));

    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedStudentString = NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + TAG_DESC_FRIEND + TIMETABLE_DESC_VALID1;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple student IDs
        assertParseFailure(parser, STUDENT_ID_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple handles
        assertParseFailure(parser, TELEGRAM_HANDLE_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM_HANDLE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedStudentString + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL, PREFIX_STUDENT_ID, PREFIX_TIMETABLE));

        // multiple timetables
        assertParseFailure(parser, TIMETABLE_DESC_VALID1 + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIMETABLE));

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid student ID
        assertParseFailure(parser, INVALID_STUDENT_ID_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // invalid handle
        assertParseFailure(parser, INVALID_TELEGRAM_HANDLE_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM_HANDLE));

        // invalid name
        assertParseFailure(parser, validExpectedStudentString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedStudentString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedStudentString + INVALID_STUDENT_ID_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // invalid handle
        assertParseFailure(parser, validExpectedStudentString + INVALID_TELEGRAM_HANDLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM_HANDLE));

        // invalid timetable
        assertParseFailure(parser, validExpectedStudentString + INVALID_TIMETABLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIMETABLE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().buildNoLink();
        assertParseSuccess(parser, NAME_DESC_AMY + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY + TELEGRAM_HANDLE_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STUDENT_ID_BOB + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + VALID_EMAIL_BOB + TELEGRAM_HANDLE_DESC_BOB,
                expectedMessage);

        // missing handle prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + VALID_TELEGRAM_HANDLE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_STUDENT_ID_BOB + VALID_EMAIL_BOB
                + VALID_TELEGRAM_HANDLE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_STUDENT_ID_DESC + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, StudentId.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_STUDENT_ID_DESC + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, StudentId.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + INVALID_EMAIL_DESC + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid telegram handle
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + INVALID_TELEGRAM_HANDLE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TelegramHandle.MESSAGE_CONSTRAINTS);

        // invalid telegram handle
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + INVALID_TELEGRAM_HANDLE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TelegramHandle.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TELEGRAM_HANDLE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void tokenizeForTimetable_emptyInput_returnsEmptyArrayList() {
        assertEquals(Stream.generate(() -> "")
                        .limit(Timetable.is7Days() ? 7 : 5)
                        .collect(Collectors.toCollection(ArrayList::new)),
                AddCommandParser.tokenizeForTimetable(""));
    }
    @Test
    public void tokenizeForTimetable_validInputs_returnsArrayList() {
        assertEquals(TypicalTimetableAndValues.VALID_TIMETABLE_INPUT_1,
                AddCommandParser.tokenizeForTimetable(
                        TypicalTimetableAndValues.VALID_ADD_COMMAND_TIMETABLE_ARGUMENTS_1));

        assertEquals(TypicalTimetableAndValues.VALID_TIMETABLE_INPUT_2,
                AddCommandParser.tokenizeForTimetable(
                        TypicalTimetableAndValues.VALID_ADD_COMMAND_TIMETABLE_ARGUMENTS_2));
    }
}
