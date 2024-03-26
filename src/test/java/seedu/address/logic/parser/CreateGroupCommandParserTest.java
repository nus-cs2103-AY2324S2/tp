package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGroups.SAMPLE_SKILL_LIST_1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.skill.Skill;

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
        Set<Skill> skillSet =
                new HashSet<>(SAMPLE_SKILL_LIST_1);

        CreateGroupCommand targetCommand = new CreateGroupCommand(groupName, courseMates, skillSet);
        assertParseSuccess(parser, "group 1 -cm Bob -s C++ -s JavaScript", targetCommand);

        assertParseSuccess(parser, "group 1",
                new CreateGroupCommand(groupName, new HashSet<>(), new HashSet<>()));
    }
}
