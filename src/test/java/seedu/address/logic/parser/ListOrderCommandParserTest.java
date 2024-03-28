package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListOrderCommand;

public class ListOrderCommandParserTest {

    private ListOrderCommandParser parser = new ListOrderCommandParser();

    @Test
    public void parse_validArgs_returnsListOrderCommand() {
        Index targetIndex = Index.fromOneBased(1);
        ListOrderCommand expectedListOrderCommand = new ListOrderCommand(targetIndex);
        assertParseSuccess(parser, "1", expectedListOrderCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test various invalid arguments
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE));
    }
}
