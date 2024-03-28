package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;

public class ThemeCommandParserTest {

    private final ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_noThemeSpecified_throwsParseException() {
        // Test for completely missing -bg argument
        String noThemeSpecified = "";
        assertParseFailure(parser, noThemeSpecified,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }
}
