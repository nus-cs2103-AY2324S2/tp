package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.AlwaysTruePredicate;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.PhoneMatchesPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 n/name", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "n/ p/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "name", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(List.of("Alice")),
                        new PhoneMatchesPredicate(new Phone("99999999")));
        assertParseSuccess(parser, " n/Alice p/99999999", expectedFindCommand);
        assertParseSuccess(parser, " \n n/Alice \n \t p/99999999  \t", expectedFindCommand);

        expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(List.of("Alice")),
                        new AlwaysTruePredicate<>());
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);

        expectedFindCommand =
                new FindCommand(new AlwaysTruePredicate<>(),
                        new PhoneMatchesPredicate(new Phone("99999999")));
        assertParseSuccess(parser, " p/99999999", expectedFindCommand);
    }
}
