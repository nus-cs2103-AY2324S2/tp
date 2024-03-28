package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        DeleteCommand expectedDeleteCommand = new DeleteCommand(new IdentityCardNumberMatchesPredicate(
                new IdentityCardNumber("S1234567A")));
        assertParseSuccess(parser, "S1234567A", expectedDeleteCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // IC with incorrect format
        assertParseFailure(parser, "S1234", String.format(IdentityCardNumber.MESSAGE_CONSTRAINTS));

        // IC with incorrect format and additional arguments
        assertParseFailure(parser, "S1234 extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // IC with incorrect format containing non-alphanumeric characters
        assertParseFailure(parser, "S1234$%^", String.format(IdentityCardNumber.MESSAGE_CONSTRAINTS));

        // IC with correct format but with additional arguments
        assertParseFailure(parser, "S1234567A extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
