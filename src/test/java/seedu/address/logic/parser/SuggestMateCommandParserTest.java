package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SuggestMateCommand;
import seedu.address.model.coursemate.Name;

public class SuggestMateCommandParserTest {
    private SuggestMateCommandParser parser = new SuggestMateCommandParser();

    @Test
    public void parse_validArgs_returnsSuggestMateCommand() {
        Name groupName = new Name("group 1");

        SuggestMateCommand targetCommand = new SuggestMateCommand(groupName);
        assertParseSuccess(parser, "group 1", targetCommand);
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        // empty input
        assertParseFailure(parser, "", Name.MESSAGE_CONSTRAINTS);

        // invalid group name
        assertParseFailure(parser, "*****", Name.MESSAGE_CONSTRAINTS);
    }
}
