package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

public class ExportCommandParserTest {

    private final ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_emptyArg_returnsDefaultExportCommand() {
        assertParseSuccess(parser, "", new ExportCommand());
    }

    @Test
    public void parse_validArgs_returnsExportCommand() {
        String filename = "test.csv";
        ExportCommand expectedExportCommand = new ExportCommand(filename);
        assertParseSuccess(parser, filename, expectedExportCommand);
    }

    @Test
    public void parse_invalidFilename_throwsParseException() {
        String invalidFilename = "invalid";
        String expectedErrorMessage = "Error: Invalid filename. Please provide a valid filename with the .csv extension.";
        assertParseFailure(parser, invalidFilename, expectedErrorMessage);
    }

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "   ", expectedErrorMessage);
    }

    @Test
    public void parse_blankArgs_throwsParseException() {
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "    ", expectedErrorMessage);
    }
}
