package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAttendanceRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.tag.Attendance;

public class AddAttendanceRecordCommandParserTest {
    // Reuse the parser object
    private AddAttendanceRecordCommandParser parser = new AddAttendanceRecordCommandParser();

    @Test
    public void parse_validArgs_returnsAddAttendanceRecordCommand() {
        // Assume "19-03-2024" is a valid date for attendance and "ar/" is the prefix for attendance records
        String userInput = " ar/19-03-2024";
        AddAttendanceRecordCommand expectedCommand = new AddAttendanceRecordCommand(new Attendance(
                new AttendanceStatus("19-03-2024", "1")));
        try {
            AddAttendanceRecordCommand command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new AssertionError("Execution of command should not throw ParseException.", pe);
        }
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = ""; // Empty string is invalid
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingAttendanceRecordPrefix_throwsParseException() {
        String userInput = "19-03-2024"; // Missing "ar/" prefix
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_duplicateAttendanceRecordPrefixes_throwsParseException() {
        String userInput = " ar/19-03-2024 ar/20-03-2024"; // Duplicate "ar/" prefixes
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
