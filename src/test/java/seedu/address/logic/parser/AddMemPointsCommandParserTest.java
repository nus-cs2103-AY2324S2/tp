package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMSHIP_PTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMemPointsCommand;
import seedu.address.model.person.Name;
public class AddMemPointsCommandParserTest {

    private AddMemPointsCommandParser parser = new AddMemPointsCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name expectedName = new Name("Alice");
        int expectedMembershipPoints = 10;

        assertParseSuccess(parser, " n/Alice " + PREFIX_MEMSHIP_PTS + "10",
                new AddMemPointsCommand(expectedName, expectedMembershipPoints));
    }

    @Test
    public void parse_missingName_failure() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " n/ " + PREFIX_MEMSHIP_PTS + "10", expectedMessage);
    }

    @Test
    public void parse_missingMembershipPoints_failure() {
        String expectedMessage = AddMemPointsCommand.INVALID_COMMAND_FORMAT + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " n/Alice", expectedMessage);
    }

    @Test
    public void parse_invalidMembershipPoints_failure() {
        String expectedMessage = AddMemPointsCommand.MESSAGE_CONSTRAINTS + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " n/Alice " + PREFIX_MEMSHIP_PTS + "abc", expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        String expectedMessage = Name.MESSAGE_CONSTRAINTS + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        // name is space only
        assertParseFailure(parser, " n/  " + PREFIX_MEMSHIP_PTS + "10", expectedMessage);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        String expectedMessage = AddMemPointsCommand.INVALID_COMMAND_FORMAT + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " n/Alice p/10", expectedMessage);
    }

    @Test
    public void parse_pointsNoPrefix_failure() {
        String expectedMessage = AddMemPointsCommand.INVALID_COMMAND_FORMAT + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        assertParseFailure(parser, " n/Alice 10", expectedMessage);
    }

    @Test
    public void parse_noInput_failure() {
        String expectedMessage = AddMemPointsCommand.INVALID_COMMAND_FORMAT + "\n" + AddMemPointsCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "", expectedMessage);
    }

}
