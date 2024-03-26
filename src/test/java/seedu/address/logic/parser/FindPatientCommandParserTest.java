package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.NricContainsMatchPredicate;

public class FindPatientCommandParserTest {

    private FindPatientCommandParser parser = new FindPatientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " i/  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for name
        FindPatientCommand expectedFindPatientCommand =
                new FindPatientCommand(new NameContainsKeywordsPredicate(Arrays.asList("Ali", "Bob")));
        assertParseSuccess(parser, " n/ Ali Bob", expectedFindPatientCommand);

        // multiple whitespaces between keywords for name
        assertParseSuccess(parser, " \n n/ Ali \n \t Bob  \t", expectedFindPatientCommand);

        // leading and trailing whitespaces for nric
        expectedFindPatientCommand =
                new FindPatientCommand(new NricContainsMatchPredicate("T012"));
        assertParseSuccess(parser, " i/   T012      ", expectedFindPatientCommand);
    }

    @Test
    public void parse_multipleInputFields_failure() {
        String userInput = " i/ T012    n/ Alex";
        assertParseFailure(parser, userInput, FindPatientCommand.MESSAGE_MULTIPLE_FIELDS_FAILURE);

        userInput = "  n/ Alex  i/ T012  ";
        assertParseFailure(parser, userInput, FindPatientCommand.MESSAGE_MULTIPLE_FIELDS_FAILURE);
    }

}
