package seedu.findvisor.logic.parser;

import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.findvisor.logic.commands.UnscheduleCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnscheduleCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnscheduleCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnscheduleCommandParserTest {

    private UnscheduleCommandParser parser = new UnscheduleCommandParser();

    @Test
    public void parse_validArgs_returnsUnscheduleCommand() {
        assertParseSuccess(parser, "1", new UnscheduleCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnscheduleCommand.MESSAGE_USAGE));
    }
}
