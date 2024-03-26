package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.SelectExamCommand;
import seedu.address.model.exam.Exam;

public class SelectExamCommandParserTest {

    private SelectExamCommandParser parser = new SelectExamCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, " " + PREFIX_NAME + "Midterm", new SelectExamCommand("Midterm"));
    }

    @Test
    public void parse_validArgs_returnsSelectCommand2() {
        assertParseSuccess(parser, " " + PREFIX_NAME + "Finals", new SelectExamCommand("Finals"));
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // multiple names
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm " + PREFIX_NAME + "Finals",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectExamCommand.MESSAGE_USAGE);

        // no name prefix
        assertParseFailure(parser, "", expectedMessage);

        // empty name
        assertParseFailure(parser, " " + PREFIX_NAME + " ", Exam.MESSAGE_CONSTRAINTS);
    }
}
