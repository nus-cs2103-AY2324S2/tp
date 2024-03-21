package seedu.TeachStack.logic.parser;

import static seedu.TeachStack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.TeachStack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.TeachStack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.TeachStack.testutil.TypicalStudentIds.ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.TeachStack.logic.commands.ViewCommand;

class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "A0223456X", new ViewCommand(ID_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "invalid", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }
}
