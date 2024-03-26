package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.TelegramChat;

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

        CreateGroupCommand targetCommand = new CreateGroupCommand(groupName, courseMates, null);
        assertParseSuccess(parser, "group 1 -cm Bob", targetCommand);

        assertParseSuccess(parser, "group 1", new CreateGroupCommand(groupName, new HashSet<>(), null));
    }

    @Test
    public void parse_withValidTelegramChat_returnsCreateGroupCommand() {
        Name groupName = new Name("group 1");
        Set<QueryableCourseMate> courseMates =
                new HashSet<>(List.of(new QueryableCourseMate(new Name("Bob"))));
        TelegramChat telegramChat = new TelegramChat("https://t.me/AAAAAEQ8H1J1J1J1J1J1J1");

        CreateGroupCommand targetCommand = new CreateGroupCommand(groupName, courseMates, telegramChat);
        assertParseSuccess(parser, "group 1 -cm Bob -t https://t.me/AAAAAEQ8H1J1J1J1J1J1J1", targetCommand);
    }

    @Test
    public void parse_invalidName_throwsParseException() {
        assertParseFailure(parser, "*****", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                CreateGroupCommand.MESSAGE_USAGE));
    }
}
