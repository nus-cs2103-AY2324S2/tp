package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.CLIENT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMeetingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteMeetingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteMeetingCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteMeetingCommandParserTest {

    private DeleteMeetingCommandParser parser = new DeleteMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        String userInput = " " + CLIENT_INDEX + MEETING_INDEX;
        assertParseSuccess(parser, userInput, new DeleteMeetingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_MEETING));
    }
}
