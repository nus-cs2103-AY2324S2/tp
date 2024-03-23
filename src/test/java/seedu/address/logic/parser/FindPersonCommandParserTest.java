package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsMatchPredicate;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " i/  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for name
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Ali", "Bob")));
        assertParseSuccess(parser, " n/ Ali Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords for name
        assertParseSuccess(parser, " \n n/ Ali \n \t Bob  \t", expectedFindPersonCommand);

        // leading and trailing whitespaces for nric
        expectedFindPersonCommand =
                new FindPersonCommand(new NricContainsMatchPredicate("T012"));
        assertParseSuccess(parser, " i/   T012      ", expectedFindPersonCommand);
    }

    @Test
    public void parse_repeatedValue_failure() {

    }

}
