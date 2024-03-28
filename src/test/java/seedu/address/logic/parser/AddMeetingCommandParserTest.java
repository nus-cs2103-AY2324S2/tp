package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Meeting;

public class AddMeetingCommandParserTest {

    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsAddMeetingCommand() throws ParseException {
        String args = "John m/Meeting time/23-03-2024 1400-1500";
        AddMeetingCommand expectedCommand = new AddMeetingCommand("John",
                new Meeting("Meeting", "23-03-2024", "1400", "1500"));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingDescription_throwsParseException() {
        String args = "John m/ time/23-03-2024 1400-1500";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        String args = "John m/Meeting time/2024-03-23 1400-1500";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidTime_throwsParseException() {
        String args = "John m/Meeting time/23-03-2024 14:00-15:00";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

}
