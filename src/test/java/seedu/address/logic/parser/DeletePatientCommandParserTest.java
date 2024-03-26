package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.model.patient.Nric;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeletePatientCommand code. For example, inputs "T0123456A" and "S9876543K"
 * take the same path through the DeletePatientCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePatientCommandParserTest {

    private DeletePatientCommandParser parser = new DeletePatientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_NRIC_AMY, new DeletePatientCommand(new Nric(VALID_NRIC_AMY)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePatientCommand.MESSAGE_USAGE));
    }
}
