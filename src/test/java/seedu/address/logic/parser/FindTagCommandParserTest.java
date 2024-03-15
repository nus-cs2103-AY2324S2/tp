package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindTagCommand;

class FindTagCommandParserTest {
    private static final FindTagCommandParser PARSER = new FindTagCommandParser();

    @Test
    void parse_emptyString_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, "",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_spaces_throws() {
        CommandParserTestUtil.assertParseFailure(PARSER, " ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_word_success() {
        CommandParserTestUtil.assertParseSuccess(PARSER, " something", new FindTagCommand("something"));
    }

    @Test
    void parse_extraSpace_success() {
        CommandParserTestUtil.assertParseSuccess(PARSER, "  something", new FindTagCommand(" something"));
    }
}
