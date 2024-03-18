package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTagCommand;

public class FindTagCommandParserTest {

    private final FindTagCommandParser parser = new FindTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FindTagCommand expectedFindTagCommand =
                new FindTagCommand(Arrays.asList("School", "Work"));
        assertParseSuccess(parser, "School Work", expectedFindTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n School \n \t Work  \t", expectedFindTagCommand);
    }

}
