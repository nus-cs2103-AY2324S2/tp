package seedu.findvisor.logic.parser;

import static seedu.findvisor.commons.util.DateTimeUtil.dateTimeToInputString;
import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.commands.CommandTestUtil.createValidMeeting;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.commands.ScheduleCommand;
import seedu.findvisor.model.person.Meeting;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsScheduleCommand() {
        Meeting meeting = createValidMeeting();
        Index targetIndex = INDEX_FIRST_PERSON;
        ScheduleCommand expectedScheduleCommand = new ScheduleCommand(targetIndex, meeting);
        System.out.println(dateTimeToInputString(meeting.start) + " " + dateTimeToInputString(meeting.end));
        assertParseSuccess(parser,
                targetIndex.getOneBased() + " "
                + PREFIX_START_DATETIME + " " + dateTimeToInputString(meeting.start) + " "
                + PREFIX_END_DATETIME + " " + dateTimeToInputString(meeting.end),
                expectedScheduleCommand);
    }

    @Test
    public void parse_invalidDatetimeFormat_throwsParseException() {
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_START_DATETIME + "2024-02-22 14:00 "
                + PREFIX_END_DATETIME + "2024-02-22 15:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }

}
