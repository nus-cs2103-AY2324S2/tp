package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStarsLessThanCommand;
import seedu.address.model.person.StarsLessThanPredicate;

public class FindStarsLessThanCommandParserTest {
    private FindStarsLessThanCommandParser parser = new FindStarsLessThanCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStarsLessThanCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // valid integer
        FindStarsLessThanCommand expectedFindCommand =
                new FindStarsLessThanCommand(new StarsLessThanPredicate(2));
        assertParseSuccess(parser, "2", expectedFindCommand);

        // valid integer with whitespaces
        assertParseSuccess(parser, " 2 ", expectedFindCommand);
    }
}
