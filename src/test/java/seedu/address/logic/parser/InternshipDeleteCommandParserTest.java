package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipCommand;
import seedu.address.logic.commands.InternshipDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the InternshipDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the InternshipDeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */

public class InternshipDeleteCommandParserTest {

    private InternshipDeleteCommandParser parser = new InternshipDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsInternshipDeleteCommand() {
        assertParseSuccess((InternshipParser<? extends InternshipCommand>) parser, "1", new InternshipDeleteCommand(INDEX_FIRST_INTERNSHIP));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure((InternshipParser<? extends InternshipCommand>) parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipDeleteCommand.MESSAGE_USAGE));
    }
}
