package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.exceptions.InvalidSortTypeException;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "sort",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommand_throwsInvalidSortTypeException() {
        String invalidSortType = "moolah";
        assertParseFailure(parser, SortCommand.COMMAND_WORD + " " + invalidSortType,
                String.format(MESSAGE_INVALID_SORT_TYPE, invalidSortType));
    }

    @Test
    public void parse_validCommand_success() throws InvalidSortTypeException {
        assertParseSuccess(parser, SortCommand.COMMAND_WORD + " " + SortCommand.BIRTHDAY_SORT_TYPE,
                new SortCommand(SortCommand.BIRTHDAY_SORT_TYPE));
    }
}
