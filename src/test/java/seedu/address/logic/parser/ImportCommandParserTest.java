package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;


public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();
    @Test
    public void parse_invalidArgs_failure() {
        // filePath left empty
        assertParseFailure(
                parser, "import ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
    }
}
