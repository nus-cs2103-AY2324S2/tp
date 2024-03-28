package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SelectCommandParserTest {
    private SelectCommandParser selectCommandParser = new SelectCommandParser();

    @Test
    public void parse_success() {
        assertParseSuccess(selectCommandParser, "1", new SelectCommand(Index.fromZeroBased(0)));
    }

    @Test
    public void parse_failure() throws ParseException {
        assertParseFailure(selectCommandParser, "0abd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }
}
