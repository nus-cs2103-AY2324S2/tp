package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReadCommand;

public class ReadCommandParserTest {
    private ReadCommandParser parser = new ReadCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(ReadCommand.MESSAGE_NOT_READ));
    }
    /*@Test
    public void parse_validArgs_returnsReadCommand() {

    }*/

}
