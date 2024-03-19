package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;

/**
 * Contains unit tests for CreateGroupCommand
 */
public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser();

    @Test
    public void parse_validArgs_returnsCreateGroupCommand() {
        Name groupName = new Name("group 1");
        Set<QueryableCourseMate> courseMates =
                new HashSet<>(List.of(new QueryableCourseMate(new Name("Bob"))));

        CreateGroupCommand targetCommand = new CreateGroupCommand(groupName, courseMates);
        assertParseSuccess(parser, "group 1 -cm Bob", targetCommand);

        assertParseSuccess(parser, "group 1", new CreateGroupCommand(groupName, new HashSet<>()));
    }
}
