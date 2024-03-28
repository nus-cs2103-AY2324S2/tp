package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import org.junit.jupiter.api.Test;

import educonnect.logic.commands.DeleteCommand;
import educonnect.model.student.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        DeleteCommand.DeleteStudentDescriptor deleteStudentDescriptor = new DeleteCommand.DeleteStudentDescriptor();
        deleteStudentDescriptor.setStudentId(new StudentId(VALID_STUDENT_ID_AMY));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY,
                new DeleteCommand(deleteStudentDescriptor));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }
}
