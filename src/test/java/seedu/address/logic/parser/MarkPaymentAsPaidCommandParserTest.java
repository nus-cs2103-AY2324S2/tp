package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkPaymentAsPaidCommand;

import seedu.address.model.person.Id;
import seedu.address.model.person.Payment;

public class MarkPaymentAsPaidCommandParserTest {

    private MarkPaymentAsPaidCommandParser parser = new MarkPaymentAsPaidCommandParser();

    @Test
    public void parse_validArgs_returnsMarkPaymentAsPaidCommand() {
        
        // test with valid ID and payment amount
        assertParseSuccess(parser, " -id 000002 -payment 50",
                new MarkPaymentAsPaidCommand(new Id("000002"), 50.0));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // missing both ID and payment prefix
        assertParseFailure(parser, "000002 50", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkPaymentAsPaidCommand.MESSAGE_USAGE));

        // missing payment amount
        assertParseFailure(parser, " -id 000002 -payment ", 
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkPaymentAsPaidCommand.MESSAGE_USAGE));

        // non-numeric payment amount
        assertParseFailure(parser, " -id 000002 -payment xyz", 
                Payment.MESSAGE_INVALID_PAYMENT);

        // missing ID value
        assertParseFailure(parser, " -id  -payment 50", 
                Id.MESSAGE_CONSTRAINTS);

        // invalid ID format
        assertParseFailure(parser, " -id abc -payment 50", 
                Id.MESSAGE_CONSTRAINTS);
    }
}
