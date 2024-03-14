package seedu.findvisor.logic.parser;

import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.findvisor.logic.commands.FindCommand;
import seedu.findvisor.model.person.NameContainsKeywordPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordPredicate("Bob Choo"));
        assertParseSuccess(parser, NAME_DESC_BOB, expectedFindCommand);

        // multiple whitespaces before and after keyword
        String paddedKeyword = "\n \t " + NAME_DESC_BOB + "\n \t";
        assertParseSuccess(parser, paddedKeyword, expectedFindCommand);
    }

}
