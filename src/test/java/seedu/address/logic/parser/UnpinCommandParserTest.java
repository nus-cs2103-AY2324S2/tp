package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnpinCommand;
import seedu.address.logic.messages.UnpinMessages;
import seedu.address.model.person.Name;

public class UnpinCommandParserTest {
    private UnpinCommandParser parser = new UnpinCommandParser();

    @Test
    public void parse_missingNamePrefix_failure() {
        // no field specified
        String userInput = UnpinCommand.COMMAND_WORD + " "
                + "Alice Pauline";
        assertParseFailure(parser, userInput, String.format(UnpinMessages.MESSAGE_UNPIN_MISSING_NAME,
                UnpinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsPinCommand() {
        String userInput = UnpinCommand.COMMAND_WORD + " " + PREFIX_NAME
                + "Alice Pauline";
        assertParseSuccess(parser, userInput,
                new UnpinCommand(new Name("Alice Pauline")));
    }
}
