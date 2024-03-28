package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONEY_OWED_FOR_SPLIT_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SplitCommand;
import seedu.address.model.person.MoneyOwed;

class SplitCommandParserTest {

    private SplitCommandParser parser = new SplitCommandParser();

    @Test
    void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 3 $/22.40",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0 3 $/22.40" + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_missingMoneyOwed_throwsParseException() {
        assertParseFailure(parser, "1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_MISSING_AMOUNT));
    }

    @Test
    public void parse_validArgs_returnsSplitCommand() {
        // no leading and trailing whitespaces
        SplitCommand expectedSplitCommand =
                new SplitCommand(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON),
                        new MoneyOwed(VALID_MONEY_OWED_FOR_SPLIT_COMMAND));
        assertParseSuccess(parser, "1 2 $/20.40", expectedSplitCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 2\t $/20.40  \t", expectedSplitCommand);
    }
}
