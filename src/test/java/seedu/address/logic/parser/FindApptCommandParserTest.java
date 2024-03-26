package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.commands.FindApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.Time;
import seedu.address.model.person.Nric;

public class FindApptCommandParserTest {

    private FindApptCommandParser parser = new FindApptCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindApptCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " i/  ",
                String.format(Nric.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, " d/  ",
                String.format(Date.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, " from/  ",
                String.format(Time.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for NRIC
        FindApptCommand expectedFindApptCommand =
                new FindApptCommand(new AppointmentContainsKeywordsPredicate(
                        Optional.of(new Nric("S1234567A")),
                        Optional.empty(),
                        Optional.empty()));
        assertParseSuccess(parser, " i/ S1234567A", expectedFindApptCommand);

        // multiple whitespaces between keywords for NRIC
        assertParseSuccess(parser, " \n i/  S1234567A", expectedFindApptCommand);

        // leading and trailing whitespaces for nric
        assertParseSuccess(parser, " i/   S1234567A      ", expectedFindApptCommand);
    }

    @Test
    public void parse_emptyInput_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidNric_throwParseException() {

        assertParseFailure(parser, FindApptCommand.COMMAND_WORD + " i/A1234567",
                String.format(Nric.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidDate_throwParseException() {
        assertParseFailure(parser, FindApptCommand.COMMAND_WORD + " d/2024-02",
                String.format(Date.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidTime_throwParseException() {
        assertParseFailure(parser, FindApptCommand.COMMAND_WORD + " from/24:00",
                String.format(Time.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validNricFilter_returnFindAppCommand() throws ParseException {
        FindApptCommand expectedCommand = new FindApptCommand(
                new AppointmentContainsKeywordsPredicate(
                        Optional.of(new Nric("S1234567A")),
                        Optional.empty(),
                        Optional.empty()
                )
        );
        assertEquals(expectedCommand, parser.parse(FindApptCommand.COMMAND_WORD + " i/S1234567A"));
    }

    @Test
    public void parse_validDateFilter_returnFindAppCommand() throws ParseException {
        FindApptCommand expectedCommand = new FindApptCommand(
                new AppointmentContainsKeywordsPredicate(
                        Optional.empty(),
                        Optional.of(new Date("2024-12-31")),
                        Optional.empty()
                )
        );
        assertEquals(expectedCommand, parser.parse(FindApptCommand.COMMAND_WORD + " d/2024-12-31"));
    }

    @Test
    public void parse_validTimeFilter_returnFindAppCommand() throws ParseException {
        FindApptCommand expectedCommand = new FindApptCommand(
                new AppointmentContainsKeywordsPredicate(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(new Time("10:00"))
                )
        );
        assertEquals(expectedCommand, parser.parse(FindApptCommand.COMMAND_WORD + " from/10:00"));
    }

}
