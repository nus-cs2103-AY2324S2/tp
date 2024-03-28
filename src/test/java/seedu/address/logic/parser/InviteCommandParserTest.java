package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.InviteCommand;

/**
 * Contains unit tests for InviteCommandParser.
 */
public class InviteCommandParserTest {

    private final InviteCommandParser parser = new InviteCommandParser();

    @Test
    public void parse_validArgs_returnsInviteCommand() {
        assertParseSuccess(parser, "1", new InviteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                InviteCommand.MESSAGE_USAGE));
    }
}
