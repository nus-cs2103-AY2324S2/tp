package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPhoneCommand;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

public class FindPhoneCommandParserTest {

    private FindPhoneCommandParser parser = new FindPhoneCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPhoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPhoneCommand() {
        // no leading and trailing whitespaces
        FindPhoneCommand expectedFindPhoneCommand =
                new FindPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("123", "456")));
        assertParseSuccess(parser, "123 456", expectedFindPhoneCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 123 \n \t 456  \t", expectedFindPhoneCommand);
    }

}
