package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.IdAndNameContainsQueryIdAndNamePredicate;
import seedu.address.model.person.IdContainsQueryIdPredicate;
import seedu.address.model.person.NameContainsQueryNamePredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyId_throwsParseException() {
        assertParseFailure(parser, "i/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_emptyName_throwsParseException() {
        assertParseFailure(parser, "n/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_emptyIdAndName_throwsParseException() {
        assertParseFailure(parser, "i/    n/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, "John Doe A1234567X",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Query by Name - no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new NameContainsQueryNamePredicate("John Doe"));
        assertParseSuccess(parser, "find n/John Doe", expectedFindNameCommand);

        // Query by Name - multiple whitespaces
        assertParseSuccess(parser, "find n/ John Doe   ", expectedFindNameCommand);

        // Query by ID - no leading and trailing whitespaces
        FindCommand expectedFindIdCommand =
                new FindCommand(new IdContainsQueryIdPredicate("A1234567X"));
        assertParseSuccess(parser, "find i/A1234567X", expectedFindIdCommand);

        // Query by ID - multiple whitespaces
        assertParseSuccess(parser, "find i/    A1234567X   ", expectedFindIdCommand);

        // Query by ID And Name - no leading and trailing whitespaces
        FindCommand expectedFindIdAndNameCommand =
                new FindCommand(new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John Doe"));
        assertParseSuccess(parser, "find i/A1234567X n/John Doe", expectedFindIdAndNameCommand);

        // Query by ID And Name - multiple whitespaces between keywords
        assertParseSuccess(parser, "find i/    A1234567X    n/  John Doe   ", expectedFindIdAndNameCommand);

    }

}
