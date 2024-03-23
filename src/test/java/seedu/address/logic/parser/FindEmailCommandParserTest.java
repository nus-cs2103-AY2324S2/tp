package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindEmailCommand;
import seedu.address.model.person.EmailContainsKeywordsPredicate;

public class FindEmailCommandParserTest {

    private FindEmailCommandParser parser = new FindEmailCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindEmailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindEmailCommand() {
        // no leading and trailing whitespaces
        FindEmailCommand expectedFindEmailCommand =
                new FindEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(
                        "Alice@email.com", "Bob@email.com")));
        assertParseSuccess(parser, "Alice@email.com Bob@email.com", expectedFindEmailCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice@email.com \n \t Bob@email.com  \t", expectedFindEmailCommand);
    }

}
