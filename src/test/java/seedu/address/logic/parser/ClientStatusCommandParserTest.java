package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClientStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClientStatusCommandParserTest {

    private final ClientStatusCommandParser parser = new ClientStatusCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientStatusCommand.MESSAGE_USAGE), ()
                        -> parser.parse("     "));
    }

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_STATUS + "up";
        ClientStatusCommand expectedCommand = new ClientStatusCommand(targetIndex, "up");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientStatusCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, ClientStatusCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(
                parser,
                ClientStatusCommand.COMMAND_WORD + " " + PREFIX_STATUS + "up",
                expectedMessage);
    }
}
