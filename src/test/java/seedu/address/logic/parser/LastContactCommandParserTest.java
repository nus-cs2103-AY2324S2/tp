package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTCONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LastContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LastContact;

public class LastContactCommandParserTest {

    private LastContactCommandParser parser = new LastContactCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        // Setup expected values
        String name = "John Doe";
        String lastContact = "13-03-2024 0600"; // Assuming the format for LastContact is YYYY-MM-DD

        // User input mimics typical command line input for last contact update
        String userInput = " " + PREFIX_NAME + name + " " + PREFIX_LASTCONTACT + lastContact;
        LastContactCommand expectedCommand = new LastContactCommand(name, new LastContact(lastContact));

        // Parse the user input and compare with expected values
        LastContactCommand command = parser.parse(userInput);
        assertEquals(expectedCommand.getName(), command.getName());
        assertEquals(expectedCommand.getLastContact(), command.getLastContact());
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // Missing NAME prefix
        String userInputMissingName = " " + PREFIX_LASTCONTACT + "2023-01-01";
        assertThrows(ParseException.class, () -> parser.parse(userInputMissingName));

        // Missing LASTCONTACT prefix
        String userInputMissingLastContact = " " + PREFIX_NAME + "John Doe";
        assertThrows(ParseException.class, () -> parser.parse(userInputMissingLastContact));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid LASTCONTACT (assuming invalid date format, etc.)
        String userInputInvalidLastContact = " " + PREFIX_NAME + "John Doe " + PREFIX_LASTCONTACT + "not-a-date";
        assertThrows(ParseException.class, () -> parser.parse(userInputInvalidLastContact));
    }
}
