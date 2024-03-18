package staffconnect.logic.parser;


import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.DATE_STARTDATE;
import static staffconnect.logic.commands.CommandTestUtil.DESCRIPTION_MIDTERM;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_STARTDATE;
import static staffconnect.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MEETING;
import static staffconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static staffconnect.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static staffconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static staffconnect.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import staffconnect.commons.core.index.Index;
import staffconnect.logic.Messages;
import staffconnect.logic.commands.AddMeetingCommand;
import staffconnect.model.meeting.Description;
import staffconnect.model.meeting.MeetDateTime;

public class AddMeetingCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, DESCRIPTION_MIDTERM + DATE_STARTDATE, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        //missing description
        assertParseFailure(parser, "1" + DATE_STARTDATE, MESSAGE_INVALID_FORMAT);

        //missing date
        assertParseFailure(parser, "1" + DESCRIPTION_MIDTERM, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {

        // non-empty rubbish preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_MIDTERM + DATE_STARTDATE,
                MESSAGE_INVALID_FORMAT);
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_MIDTERM + DATE_STARTDATE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_MIDTERM + DATE_STARTDATE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {

        //both invalid values for description and date, first value reported
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION + INVALID_STARTDATE,
                Description.MESSAGE_CONSTRAINTS);

        // invalid description followed by valid date
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION + DATE_STARTDATE,
                Description.MESSAGE_CONSTRAINTS);
        // valid description followed by invalid date
        assertParseFailure(parser, "1" + DESCRIPTION_MIDTERM + INVALID_STARTDATE, MeetDateTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_MIDTERM + DATE_STARTDATE;

        AddMeetingCommand expectedCommand = new AddMeetingCommand(targetIndex, VALID_MEETING);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_MIDTERM + INVALID_DESCRIPTION + DATE_STARTDATE;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION + DESCRIPTION_MIDTERM + DATE_STARTDATE;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // multiple valid fields repeated
        userInput =
                targetIndex.getOneBased() + DESCRIPTION_MIDTERM + DATE_STARTDATE + DESCRIPTION_MIDTERM + DATE_STARTDATE;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION,
                PREFIX_STARTDATE));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION + INVALID_STARTDATE + INVALID_DESCRIPTION
                + INVALID_STARTDATE;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION,
                PREFIX_STARTDATE));
    }
}
