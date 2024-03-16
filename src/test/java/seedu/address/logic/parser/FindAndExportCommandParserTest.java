package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAndExportCommand;

public class FindAndExportCommandParserTest {

    private FindAndExportCommandParser parser = new FindAndExportCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FindAndExportCommand expectedCommand = new FindAndExportCommand("friends", "John", "123 Main St", "output.csv");
        assertParseSuccess(parser, "friends n/John a/123 Main St o/output.csv", expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        FindAndExportCommand expectedCommand = new FindAndExportCommand("friends", null, null, "default.csv");
        assertParseSuccess(parser, "friends", expectedCommand);
    }

    @Test
    public void parse_missingTag_failure() {
        assertParseFailure(parser, "", FindAndExportCommand.MESSAGE_USAGE);
    }
}
