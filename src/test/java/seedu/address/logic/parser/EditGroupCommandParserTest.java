package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.TelegramChat;

public class EditGroupCommandParserTest {
    private EditGroupCommandParser parser = new EditGroupCommandParser();

    @Test
    public void parse_validArgs_returnsEditGroupCommand() {
        Name groupName = new Name("group 1");
        TelegramChat telegramChat = new TelegramChat("https://t.me/AAAAAEQ8H1J1J1J1J1J1J1");

        EditGroupCommand targetCommand = new EditGroupCommand(groupName, telegramChat);
        assertParseSuccess(parser, "group 1 -t https://t.me/AAAAAEQ8H1J1J1J1J1J1J1", targetCommand);
    }

    @Test
    public void parse_invalidArgs_returnsParseException() {
        // empty input
        assertParseFailure(parser, "", Name.MESSAGE_CONSTRAINTS);

        // invalid group name
        assertParseFailure(parser, "***** -t https://t.me/AAAAAEQ8H1J1J1J1J1J1", Name.MESSAGE_CONSTRAINTS);

        // missing telegram chat
        assertParseFailure(parser, "group 1", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                EditGroupCommand.MESSAGE_USAGE));

        // invalid telegram chat
        assertParseFailure(parser, "group 1 -t", TelegramChat.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "group 1 -t invalid", TelegramChat.MESSAGE_CONSTRAINTS);
    }
}
