package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewClientCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.model.person.Person;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the ViewCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ViewCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewClientCommand() {
        assertParseSuccess(parser, "c 1", new ViewClientCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validArgs_returnsViewMeetingCommand() {
        assertParseSuccess(parser, "m 1 1", new ViewMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNumberOfArgs_viewClient_throwsParseException() {
        assertParseFailure(parser, "c", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNumberOfArgs_viewMeeting_throwsParseException() {
        assertParseFailure(parser, "m", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
    }


    // test for many white spaces
}
