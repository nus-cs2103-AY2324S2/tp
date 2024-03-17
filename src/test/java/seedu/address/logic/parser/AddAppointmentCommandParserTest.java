package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class AddAppointmentCommandParserTest {

    @Test
    public void parse_missingPrefix_throwsParseException() {
        AddAppointmentCommandParser parser = new AddAppointmentCommandParser();
        String args = "ad/2024-01-01";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        AddAppointmentCommandParser parser = new AddAppointmentCommandParser();
        String args = "";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
