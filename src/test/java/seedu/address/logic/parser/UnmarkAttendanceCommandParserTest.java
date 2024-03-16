package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NUSNET_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSNET_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEEK_NUMBER_1;
import static seedu.address.logic.commands.CommandTestUtil.WEEK_NUMBER_DESC_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.model.person.NusNet;
import seedu.address.model.weeknumber.WeekNumber;

public class UnmarkAttendanceCommandParserTest {
    private UnmarkAttendanceCommandParser parser = new UnmarkAttendanceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkAttendanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsUnmarkAttendanceCommand() {
        NusNet expectedNusNet = new NusNet(VALID_NUSNET_AMY);
        WeekNumber expectedWeekNumber = new WeekNumber(VALID_WEEK_NUMBER_1);
        UnmarkAttendanceCommand expectedUnmarkAttendanceCommand =
                new UnmarkAttendanceCommand(expectedNusNet, expectedWeekNumber);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, NUSNET_DESC_AMY + WEEK_NUMBER_DESC_1, expectedUnmarkAttendanceCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, NUSNET_DESC_AMY + "  " + WEEK_NUMBER_DESC_1, expectedUnmarkAttendanceCommand);
    }

}
