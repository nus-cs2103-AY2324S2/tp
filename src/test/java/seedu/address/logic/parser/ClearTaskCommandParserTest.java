package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearTaskCommand;

public class ClearTaskCommandParserTest {
    private ClearTaskCommandParser parser = new ClearTaskCommandParser();

    @Test
    public void parse_validArgs_returnsClearTaskCommand() {
        assertParseSuccess(parser, "1", new ClearTaskCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ClearTaskCommand.MESSAGE_USAGE));
    }
}
