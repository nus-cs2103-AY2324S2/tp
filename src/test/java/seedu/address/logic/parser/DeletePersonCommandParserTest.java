package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePersonCommand;

/**
 * Tests the DeletePersonCommandParser class.
 */
public class DeletePersonCommandParserTest {

    private DeletePersonCommandParser parser = new DeletePersonCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePersonCommand() {
        assertParseSuccess(parser, "1", new DeletePersonCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePersonCommand.MESSAGE_USAGE));
    }
}
