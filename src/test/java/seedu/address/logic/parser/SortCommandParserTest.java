package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.SortCommand;

class SortCommandParserTest {
    private static final SortCommandParser PARSER = new SortCommandParser();

    @Test
    void parse_emptyString_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_spaces_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, " ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_onlyField_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, "email",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_unknownField_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, "hello asc",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_unknownDirection_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, "major hi",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    @Test
    void parse_word_success() {
        CommandParserTestUtil.assertParseSuccess(PARSER, " major desc",
                new SortCommand("major", false));
    }

    @Test
    void parse_extraSpace_success() {
        CommandParserTestUtil.assertParseSuccess(PARSER, "  major asc",
                new SortCommand("major", true));
    }
}
