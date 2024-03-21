package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCompanyCommand;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;

public class FindCompanyCommandParserTest {

    private FindCompanyCommandParser parser = new FindCompanyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCompanyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCompanyCommand expectedFindCompanyCommand =
                new FindCompanyCommand(new CompanyContainsKeywordsPredicate(Arrays.asList("Google", "Microsoft")));
        assertParseSuccess(parser, "Google Microsoft", expectedFindCompanyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Google \n \t Microsoft  \t", expectedFindCompanyCommand);
    }

}
