package seedu.address.logic.parser;

import seedu.address.logic.commands.FindAndExportCommand;

import org.junit.jupiter.api.Test;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class FindAndExportCommandParserTest {

    private FindAndExportCommandParser parser = new FindAndExportCommandParser();
    public static final String MESSAGE_INVALID_FILENAME = "The filename provided is invalid.";
    public static final String MESSAGE_MISSING_NAME = "A name must be provided.";


    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, "invalidTag n/John a/123 Main St o/output.csv",
                FindAndExportCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidFilename_failure() {
        assertParseFailure(parser, "friends n/John a/123 Main St o/|\\?*<\":>+[]/'",
                MESSAGE_INVALID_FILENAME);
    }

    @Test
    public void parse_missingName_failure() {
        assertParseFailure(parser, "friends a/123 Main St o/output.csv",
                MESSAGE_MISSING_NAME);
    }
}
