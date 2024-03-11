package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;

public class AddBookingCommandParser implements Parser<AddBookingCommand> {

    public AddBookingCommand parse(String args) throws ParseException {
        // Split the args into name, start, and end parts
        // Example parsing logic, adapt as necessary
        try {
            String[] parts = args.trim().split("\\s+");
            String name = parts[0];
            LocalDateTime start = LocalDateTime.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            LocalDateTime end = LocalDateTime.parse(parts[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            Booking booking = new Booking(name, start, end);
            return new AddBookingCommand(booking);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date and time format.");
        }
    }
}