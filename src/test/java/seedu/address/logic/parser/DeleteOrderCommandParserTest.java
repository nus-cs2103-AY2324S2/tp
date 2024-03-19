package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.orders.DeleteOrderCommand;

/**
 * Contains unit tests for {@code DeleteOrderCommandParser}.
 */
public class DeleteOrderCommandParserTest {

    private DeleteOrderCommandParser parser = new DeleteOrderCommandParser();

    @Test
    public void parse_missingIdPrefix_throwsParseException() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1", expectedMessage);
    }

    @Test
    public void parse_emptyIdValue_throwsParseException() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "" + PREFIX_ID, expectedMessage);
    }
}
