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
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;

public class AddMeetingCommandParserTest {

    private AddMeetingCommandParser parser = new AddMeetingCommandParser();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = Index.fromOneBased(1);
        String description = "Project discussion";
        String dateTimeStr = "01-01-2030 17:00";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        String userInput = " " + CLIENT_INDEX + DATETIME + DESCRIPTION;

        assertParseSuccess(parser, userInput, new AddMeetingCommand(dateTime, description, targetIndex));
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
        String userInput = " " + PREFIX_CLIENT_INDEX + "0 " + DATETIME + DESCRIPTION;
        // Invalid client index
        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);

        String userInputDateTime = " " + CLIENT_INDEX + " " + PREFIX_DATETIME + "01-01-2024 17:00 " + DESCRIPTION;
        // Invalid date time
        assertParseFailure(parser, userInputDateTime, Meeting.MESSAGE_INVALID_DATE_TIME);

        String userInputDescription = " " + CLIENT_INDEX + DATETIME + " " + PREFIX_DESCRIPTION + " ";
        // Invalid description
        assertParseFailure(parser, userInputDescription, Meeting.MESSAGE_CONSTRAINTS);

    }

    // Additional tests for other invalid scenarios, like invalid description, etc.
}
