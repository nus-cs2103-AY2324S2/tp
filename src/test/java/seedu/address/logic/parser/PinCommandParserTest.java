package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.messages.PinMessages;
import seedu.address.model.person.Name;

public class PinCommandParserTest {
    private PinCommandParser parser = new PinCommandParser();

    @Test
    public void parse_missingNamePrefix_failure() {
        // no field specified
        String userInput = PinCommand.COMMAND_WORD + " "
                + "Alice Pauline";
        assertParseFailure(parser, userInput, String.format(PinMessages.MESSAGE_PIN_MISSING_NAME,
                PinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsPinCommand() {
        String userInput = PinCommand.COMMAND_WORD + " " + PREFIX_NAME
                + "Alice Pauline";
        assertParseSuccess(parser, userInput,
                new PinCommand(new Name("Alice Pauline")));
    }
}
