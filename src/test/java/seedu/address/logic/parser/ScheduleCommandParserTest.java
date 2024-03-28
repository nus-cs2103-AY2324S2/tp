package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Schedule;

public class ScheduleCommandParserTest {

    private final ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsScheduleCommand() throws ParseException {
        String args = "1 " + PREFIX_SCHEDULE + "2022-12-31 18:00";
        ScheduleCommand expectedCommand = new ScheduleCommand(ParserUtil.parseIndex("1"),
                new Schedule(DateTimeUtil.parseStringToDateTime("2022-12-31 18:00")));

        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        // Missing index
        String args = PREFIX_SCHEDULE + "2022-12-31 18:00";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingSchedulePrefix_throwsParseException() {
        // Missing schedule prefix
        String args = "1 2022-12-31 18:00";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        // Invalid index format
        String args = "invalid " + PREFIX_SCHEDULE + "2022-12-31 18:00";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidScheduleFormat_throwsParseException() {
        // Invalid schedule format
        String args = "1 " + PREFIX_SCHEDULE + "invalid-date-format";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
