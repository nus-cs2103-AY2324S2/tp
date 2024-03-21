package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.FindTechStackCommand;
import seedu.address.model.contact.TsContainsKeywordsPredicate;

public class FindTechStackCommandParserTest {

    private FindTechStackCommandParser parser = new FindTechStackCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTechStackCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTechStackCommand() {
        // no leading and trailing whitespaces
        FindTechStackCommand expectedFindTsCommand =
                new FindTechStackCommand(Arrays.asList("Alice", "Bob"));
        assertParseSuccess(parser, "Alice Bob", expectedFindTsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindTsCommand);
    }
}
