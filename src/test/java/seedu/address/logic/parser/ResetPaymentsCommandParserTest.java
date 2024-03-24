package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ResetPaymentsCommand;
import seedu.address.model.person.Id;

public class ResetPaymentsCommandParserTest {

    private ResetPaymentsCommandParser parser = new ResetPaymentsCommandParser();

    @Test
    public void parse_validArgs_returnsResetPaymentsCommand() {
        
        // test with valid ID
        assertParseSuccess(parser, " -id 000003",
                new ResetPaymentsCommand(new Id("000003")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // missing ID value
        assertParseFailure(parser, " -id ", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetPaymentsCommand.MESSAGE_USAGE));

        // extra invalid arguments
        assertParseFailure(parser, " -id 000003 extra-arg", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetPaymentsCommand.MESSAGE_USAGE));

        // invalid ID format
        assertParseFailure(parser, " -id abc", 
                Id.MESSAGE_CONSTRAINTS);
    }
}
