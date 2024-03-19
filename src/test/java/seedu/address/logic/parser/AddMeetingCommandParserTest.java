package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;

public class AddMeetingCommandParserTest {

    private AddMeetingParser parser = new AddMeetingParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = Index.fromOneBased(1);
        String description = "Project Meeting";
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 12, 0);
        String userInput = " " + DATETIME + DESCRIPTION + CLIENT_INDEX;

        AddMeetingCommand expectedCommand = new AddMeetingCommand(dateTime, description, targetIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // Missing client index
        assertParseFailure(parser, DATETIME + DESCRIPTION, expectedMessage);

        // Missing date time
        assertParseFailure(parser, DESCRIPTION + CLIENT_INDEX, expectedMessage);

        // Missing description
        assertParseFailure(parser, DATETIME + CLIENT_INDEX, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid client index
        assertParseFailure(parser, PREFIX_CLIENT_INDEX + "0"
                + PREFIX_DATETIME + "01-01-2024 12:00" + PREFIX_DESCRIPTION + "Project Meeting", MESSAGE_INVALID_INDEX);

        // Invalid date time
        assertParseFailure(parser, CLIENT_INDEX
                + "not-a-date-time" + PREFIX_DESCRIPTION + "Project Meeting", Meeting.MESSAGE_INVALID_DATE_TIME);

        // Invalid description
        assertParseFailure(parser, CLIENT_INDEX
                + "01-01-2024 12:00" + PREFIX_DESCRIPTION + " ", Meeting.MESSAGE_CONSTRAINTS);

    }

    // Additional tests for other invalid scenarios, like invalid description, etc.
}
