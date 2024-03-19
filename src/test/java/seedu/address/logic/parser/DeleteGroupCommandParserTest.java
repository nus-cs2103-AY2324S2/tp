package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.model.coursemate.Name;

/**
 * Contains unit tests for DeleteGroupCommandParser
 */
public class DeleteGroupCommandParserTest {
    private DeleteGroupCommandParser parser = new DeleteGroupCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteGroupCommand() {
        Name groupName = new Name("group 1");
        DeleteGroupCommand targetCommand = new DeleteGroupCommand(groupName);

        assertParseSuccess(parser, "group 1", targetCommand);
    }
}
