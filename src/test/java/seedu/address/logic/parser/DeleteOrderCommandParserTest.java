package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.orders.DeleteOrderCommand;

/**
 * Contains unit tests for {@code DeleteOrderCommandParser}.
 */
public class DeleteOrderCommandParserTest {

    private DeleteOrderCommandParser parser = new DeleteOrderCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteOrderCommand(INDEX_FIRST_ORDER));
    }

    @Test
    public void parse_emptyIdValue_throwsParseException() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }
}
