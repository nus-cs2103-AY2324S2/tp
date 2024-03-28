package seedu.realodex.logic.parser;

import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.commands.HelpCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validArgs_returnsHelpCommand() throws ParseException {
        HelpCommand expectedHelpCommand = new HelpCommand("");
        assertParseSuccess(parser, "", expectedHelpCommand);

        //test for spaces in input
        HelpCommand expectedSpaceHelpCommand = new HelpCommand("");
        assertParseSuccess(parser, "   ", expectedSpaceHelpCommand);

        HelpCommand expectedAddHelpCommand = new HelpCommand("add");
        assertParseSuccess(parser, "add", expectedAddHelpCommand);

        HelpCommand expectedClearHelpCommand = new HelpCommand("clear");
        assertParseSuccess(parser, "clear", expectedClearHelpCommand);

        HelpCommand expectedDeleteHelpCommand = new HelpCommand("delete");
        assertParseSuccess(parser, "delete", expectedDeleteHelpCommand);

        HelpCommand expectedEditHelpCommand = new HelpCommand("edit");
        assertParseSuccess(parser, "edit", expectedEditHelpCommand);

        HelpCommand expectedFilterHelpCommand = new HelpCommand("filter");
        assertParseSuccess(parser, "filter", expectedFilterHelpCommand);

        HelpCommand expectedListHelpCommand = new HelpCommand("list");
        assertParseSuccess(parser, "list", expectedListHelpCommand);
    }
}
