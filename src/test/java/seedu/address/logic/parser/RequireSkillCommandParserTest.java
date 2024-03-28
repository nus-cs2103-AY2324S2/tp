package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RequireSkillCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.skill.Skill;

public class RequireSkillCommandParserTest {

    private RequireSkillCommandParser parser = new RequireSkillCommandParser();
    @Test
    public void parse_validArgs_returnsRequireSkillCommand() {
        Name groupName1 = new Name("group 1");
        Set<Skill> skillSet1 = new HashSet<>(List.of(
                new Skill("C++")));

        RequireSkillCommand targetCommand = new RequireSkillCommand(groupName1, skillSet1);
        assertParseSuccess(parser, "group 1 -s C++", targetCommand);

        Name groupName2 = new Name("group 1");
        Set<Skill> skillSet2 = new HashSet<>(List.of(
                new Skill("C++"),
                new Skill("Java")));
        assertParseSuccess(parser, "group 1 -s C++ -s Java",
                new RequireSkillCommand(groupName2, skillSet2));
    }

    @Test
    public void parse_invalidArgsNoSkills_returnsParseException() {
        assertParseFailure(parser, "group 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RequireSkillCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidArgsEmptySkill_returnsParseException() {
        assertParseFailure(parser, "group 1 -s -s C++",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RequireSkillCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidName_returnsParseException() {
        assertParseFailure(parser, "gr@up 1 -s C++",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RequireSkillCommand.MESSAGE_USAGE));
    }
}
