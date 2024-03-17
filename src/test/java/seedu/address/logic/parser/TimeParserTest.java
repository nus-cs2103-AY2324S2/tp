package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentTime;

public class TimeParserTest {
    @Test
    public void regex() {
        try {
            assertTrue(TimeParser.parse("15/02/2024 9am-10am") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9AM-10am") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9am-10AM") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9AM-10AM") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9am -10Am") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9am - 10am") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9aM - 10AM") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 9Am- 10Am") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 10am -10pm") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 2Pm - 5PM") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 2PM - 2pM") instanceof AppointmentTime);
            assertTrue(TimeParser.parse("15/02/2024 2PM- 3Pm") instanceof AppointmentTime);
            assertThrows(ParseException.class, () -> TimeParser.parse("15/02/20242PM- 3Pm"));
            assertThrows(ParseException.class, () -> TimeParser.parse("15/02/2024 20PM- 3Pm"));
            assertThrows(ParseException.class, () -> TimeParser.parse("15/02/2024 1PM- 13Pm"));
            assertThrows(ParseException.class, () -> TimeParser.parse(""));
            assertThrows(NullPointerException.class, () -> TimeParser.parse(null));
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}
