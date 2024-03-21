package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

public class InternshipFindCommandParserTest {

    private InternshipFindCommandParser parser = new InternshipFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMode_throwsParseException() {
        assertParseFailure(parser, "withsome /com Microsoft Google",
                String.format(InternshipFindCommand.INVALID_MODE_SPECIFIED));
    }

    @Test
    public void parse_noMode_throwsParseException() {
        assertParseFailure(parser, " /com Microsoft Google",
                String.format(InternshipFindCommand.INVALID_MODE_SPECIFIED));
    }

    @Test
    public void parse_noSearchKey_throwsParseException() {
        assertParseFailure(parser, MODE_WITHALL,
                String.format(InternshipFindCommand.NO_SEARCH_KEY_SPECIFIED));
    }
    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new InternshipContainsKeywordsPredicate(
                        "Microsoft Google", null, null,
                        null, null, null, true));

        assertParseSuccess(parser, MODE_WITHALL + " "
                + PREFIX_COMPANY + " Microsoft Google ", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHALL + " "
                + PREFIX_COMPANY + " \n Microsoft \n \t Google  \t", expectedFindCommand);
    }
}
