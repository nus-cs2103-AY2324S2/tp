package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentTime;

public class TimeParserTest {
    private TimeParser parser = new TimeParser();

    @Test
    public void regex() {
        AppointmentTime expectedTime = new AppointmentTime("15/02/2024 9am-10am");

        try {
            assertTrue(parser.parse("15/02/2024 9am-10am") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9AM-10am") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9am-10AM") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9AM-10AM") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9am -10Am") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9am - 10am") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9aM - 10AM") instanceof AppointmentTime);
            assertTrue(parser.parse("15/02/2024 9Am- 10Am") instanceof AppointmentTime);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }
}
