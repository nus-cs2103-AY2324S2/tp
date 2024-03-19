package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Availability;
import seedu.address.model.person.AvailableAtDatePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_prefixWithoutArgs_throwsParseException() {
        assertParseFailure(parser, " n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " a/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/ a/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // single space between keywords
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/  \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidAvailArgs_throwsParseException() {
        assertParseFailure(parser, " a/12/1/2024",
                String.format(Availability.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validAvailabilityArgs_returnsFindCommand() {
        try {
            // single space between keywords
            FindCommand expectedFindCommand =
                    new FindCommand(new AvailableAtDatePredicate(Arrays.asList("01/01/2024", "12/12/2024")));
            assertParseSuccess(parser, " a/01/01/2024 12/12/2024", expectedFindCommand);

            // multiple whitespaces between keywords
            assertParseSuccess(parser, " a/  \n 01/01/2024 \n \t 12/12/2024  \t", expectedFindCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
