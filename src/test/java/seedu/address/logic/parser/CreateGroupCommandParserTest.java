package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalGroups.SAMPLE_SKILL_LIST_1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.TelegramChat;
import seedu.address.model.skill.Skill;

/**
 * Contains unit tests for CreateGroupCommand
 */
public class CreateGroupCommandParserTest {
    private CreateGroupCommandParser parser = new CreateGroupCommandParser();

    @Test
    public void parse_validName_returnsCreateGroupCommand() {
        Name groupName = new Name("group 1");
        Set<QueryableCourseMate> courseMates =
                new HashSet<>(List.of(new QueryableCourseMate(new Name("Bob"))));
        Set<Skill> skillSet =
                new HashSet<>(SAMPLE_SKILL_LIST_1);

        CreateGroupCommand targetCommand = new CreateGroupCommand(groupName, courseMates, skillSet, null);
        assertParseSuccess(parser, "group 1 -cm Bob -s C++ -s JavaScript", targetCommand);

        assertParseSuccess(parser, "group 1",
                new CreateGroupCommand(groupName, new HashSet<>(), new HashSet<>(), null));
    }

    @Test
    public void parse_withValidSkills_returnsCreateGroupCommand() {
        Name groupName = new Name("group 1");
        Set<QueryableCourseMate> courseMates =
                new HashSet<>(List.of(new QueryableCourseMate(new Name("Bob"))));
        Set<Skill> skillSet =
                new HashSet<>(SAMPLE_SKILL_LIST_1);

        CreateGroupCommand targetCommand = new CreateGroupCommand(groupName, courseMates, skillSet, null);
        assertParseSuccess(parser, "group 1 -cm Bob -s C++ -s JavaScript", targetCommand);
    }

    @Test
    public void parse_withValidTelegramChat_returnsCreateGroupCommand() {
        Name groupName = new Name("group 1");
        Set<QueryableCourseMate> courseMates =
                new HashSet<>(List.of(new QueryableCourseMate(new Name("Bob"))));
        TelegramChat telegramChat = new TelegramChat("https://t.me/AAAAAEQ8H1J1J1J1J1J1J1");

        CreateGroupCommand targetCommand =
                new CreateGroupCommand(groupName, courseMates, new HashSet<>(), telegramChat);
        assertParseSuccess(parser, "group 1 -cm Bob -t https://t.me/AAAAAEQ8H1J1J1J1J1J1J1", targetCommand);
    }

    @Test
    public void parse_invalidName_throwsParseException() {
        assertParseFailure(parser, "*****", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                CreateGroupCommand.MESSAGE_USAGE));
    }
}
