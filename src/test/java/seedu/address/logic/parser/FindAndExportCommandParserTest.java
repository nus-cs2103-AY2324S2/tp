package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAndExportCommand;

public class FindAndExportCommandParserTest {

    private final FindAndExportCommandParser parser = new FindAndExportCommandParser();

    @Test
    public void parse_validArgs_returnsFindAndExportCommand() {
        FindAndExportCommand expectedCommand = new FindAndExportCommand("friends", null, null, "default_filename.csv");

        assertParseSuccess(parser, "friends", expectedCommand);

        //expectedCommand = new FindAndExportCommand("friends", "John", "123 Main St", "output.csv");
        //assertParseSuccess(parser, "friends n/John a/123 Main St o/output.csv", expectedCommand);
    }
}
