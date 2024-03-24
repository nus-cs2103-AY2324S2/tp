package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPaymentCommand;
import seedu.address.model.person.Id;
import seedu.address.model.person.Payment;

public class AddPaymentCommandParserTest {

    private AddPaymentCommandParser parser = new AddPaymentCommandParser();

    @Test
    public void parse_validArgs_returnsAddPaymentCommand() {
        assertParseSuccess(parser, " -id 000001 -payment 100",
                new AddPaymentCommand(new Id("000001"), 100.0));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        
        // missing ID prefix
        assertParseFailure(parser, " 000001 -payment 100", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPaymentCommand.MESSAGE_USAGE));

        // missing payment prefix
        assertParseFailure(parser, " -id 000001 100", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPaymentCommand.MESSAGE_USAGE));

        // missing ID value
        assertParseFailure(parser, " -id  -payment 100", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPaymentCommand.MESSAGE_USAGE));

        // missing payment value
        assertParseFailure(parser, " -id 000001 -payment ", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPaymentCommand.MESSAGE_USAGE));

        // non-numeric payment amount
        assertParseFailure(parser, " -id 000002 -payment xyz", 
                Payment.MESSAGE_INVALID_PAYMENT);

        // invalid ID format
        assertParseFailure(parser, " -id abc -payment 50", 
                Id.MESSAGE_CONSTRAINTS);
    }
}
