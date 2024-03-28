package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_LINK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.model.group.Group;

public class EditGroupCommandParserTest {
    private EditGroupCommandParser parser = new EditGroupCommandParser();

    @Test
    public void parse_validArgs_returnsEditGroupCommand() {
        assertParseSuccess(parser, " g/TUT04 tg/https://t.me/abcdefg",
                new EditGroupCommand(new Group(VALID_GROUP_TUTORIAL), VALID_TELEGRAM_LINK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " g/abcd tg/https://t.me/abcdef", Group.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " g/TUT01 tg/www.google.com", Group.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE));
    }

}
