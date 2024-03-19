package scrolls.elder.logic.parser;

import static scrolls.elder.logic.parser.CommandParserTestUtil.assertParseFailure;
import static scrolls.elder.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.PairCommand;
import scrolls.elder.testutil.TypicalIndexes;

class PairCommandParserTest {

    private PairCommandParser parser = new PairCommandParser();

    @Test
    void parse_validArgs_returnsPairCommand() {
        assertParseSuccess(parser, "1 5",
                new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIFTH_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "pe",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, PairCommand.MESSAGE_USAGE));
    }
}
