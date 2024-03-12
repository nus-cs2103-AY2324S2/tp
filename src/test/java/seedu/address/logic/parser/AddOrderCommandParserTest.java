package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.order.Date;

public class AddOrderCommandParserTest {
    private static final String NON_EMPTY_DATE = "2020-01-01";
    private static final String NON_EMPTY_DESCRIPTION = "100 chicken wings";

    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_DATE + NON_EMPTY_DATE + " "
                + PREFIX_DESCRIPTION + NON_EMPTY_DESCRIPTION;

        AddOrderCommand expectedCommand = new AddOrderCommand(INDEX_FIRST_PERSON, new Date(NON_EMPTY_DATE),
                NON_EMPTY_DESCRIPTION);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingDateField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddOrderCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddOrderCommand.COMMAND_WORD + " " + PREFIX_DATE + NON_EMPTY_DATE,
                expectedMessage);
    }
}
