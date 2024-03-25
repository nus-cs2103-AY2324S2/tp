package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.commands.FindAppCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.Time;
import seedu.address.model.person.Nric;

public class FindAppCommandParserTest {

    private FindAppCommandParser parser = new FindAppCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppCommand.MESSAGE_USAGE));
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
        FindAppCommand expectedFindAppCommand =
                new FindAppCommand(new AppointmentContainsKeywordsPredicate(
                        Optional.of(new Nric("S1234567A")),
                        Optional.empty(),
                        Optional.empty()));
        assertParseSuccess(parser, " i/ S1234567A", expectedFindAppCommand);

        // multiple whitespaces between keywords for NRIC
        assertParseSuccess(parser, " \n i/  S1234567A", expectedFindAppCommand);

        // leading and trailing whitespaces for nric
        assertParseSuccess(parser, " i/   S1234567A      ", expectedFindAppCommand);
    }

    @Test
    public void parse_emptyInput_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidNric_throwParseException() {
        assertParseFailure(parser, "findApp i/A1234567",
                String.format(Nric.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidDate_throwParseException() {
        assertParseFailure(parser, "findApp d/2024-02",
                String.format(Date.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidTime_throwParseException() {
        assertParseFailure(parser, "findApp from/24:00",
                String.format(Time.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validNricFilter_returnFindAppCommand() throws ParseException {
        FindAppCommand expectedCommand = new FindAppCommand(
                new AppointmentContainsKeywordsPredicate(
                        Optional.of(new Nric("S1234567A")),
                        Optional.empty(),
                        Optional.empty()
                )
        );
        assertEquals(expectedCommand, parser.parse("findApp i/S1234567A"));
    }

    @Test
    public void parse_validDateFilter_returnFindAppCommand() throws ParseException {
        FindAppCommand expectedCommand = new FindAppCommand(
                new AppointmentContainsKeywordsPredicate(
                        Optional.empty(),
                        Optional.of(new Date("2024-12-31")),
                        Optional.empty()
                )
        );
        assertEquals(expectedCommand, parser.parse("findApp d/2024-12-31"));
    }

    @Test
    public void parse_validTimeFilter_returnFindAppCommand() throws ParseException {
        FindAppCommand expectedCommand = new FindAppCommand(
                new AppointmentContainsKeywordsPredicate(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(new Time("10:00"))
                )
        );
        assertEquals(expectedCommand, parser.parse("findApp from/10:00"));
    }

}
