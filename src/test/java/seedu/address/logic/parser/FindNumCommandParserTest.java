package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindNumCommand;
import seedu.address.model.person.PhoneContainsDigitsPredicate;

import java.util.Arrays;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindNumCommandParserTest {

    private final FindNumCommandParser parser = new FindNumCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     "
                           , String.format(MESSAGE_INVALID_COMMAND_FORMAT
                           , FindNumCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindNumCommand() {
        // no leading and trailing whitespaces
        FindNumCommand expectedFindNumCommand =
                new FindNumCommand(new PhoneContainsDigitsPredicate(Arrays.asList("87438807", "99272758")));
        assertParseSuccess(parser, "87438807 99272758", expectedFindNumCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 87438807 \n \t 99272758  \t", expectedFindNumCommand);
    }

}
