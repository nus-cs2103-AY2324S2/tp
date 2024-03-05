package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonDetailContainsKeywordPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidPrefix_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " m/Alice",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "Alice"));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);

        // whitespace before keyword
        assertParseSuccess(parser, " n/  Alice", expectedFindCommand);
    }

    @Test
    public void parse_validEmailArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "alice@gmail.com"));
        assertParseSuccess(parser, " e/alice@gmail.com", expectedFindCommand);

        // whitespace before keyword
        assertParseSuccess(parser, " e/  alice@gmail.com  ", expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonDetailContainsKeywordPredicate(PREFIX_PHONE, "91234567"));
        assertParseSuccess(parser, " p/91234567", expectedFindCommand);

        // whitespace before keyword
        assertParseSuccess(parser, " p/  91234567  ", expectedFindCommand);
    }

    @Test
    public void parse_validAddressArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonDetailContainsKeywordPredicate(PREFIX_ADDRESS, "123, Jurong West Ave 6"));
        assertParseSuccess(parser, " a/123, Jurong West Ave 6", expectedFindCommand);

        // whitespace before keyword
        assertParseSuccess(parser, " a/  123, Jurong West Ave 6  ", expectedFindCommand);
    }

    @Test
    public void parse_validTagArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonDetailContainsKeywordPredicate(PREFIX_TAG, "friends"));
        assertParseSuccess(parser, " t/friends", expectedFindCommand);

        // whitespace before keyword
        assertParseSuccess(parser, " t/  friends  ", expectedFindCommand);
    }



}
