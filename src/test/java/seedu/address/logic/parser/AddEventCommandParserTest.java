package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_BINGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_BINGO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.BINGO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(BINGO).build();

        assertParseSuccess(parser, EVENT_NAME_DESC_BINGO, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedEventString = EVENT_NAME_DESC_BINGO;

        assertParseFailure(parser, EVENT_NAME_DESC_BINGO + validExpectedEventString, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT_NAME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_EVENT_NAME_BINGO, expectedMessage);
    }

}
