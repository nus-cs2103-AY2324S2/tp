package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EditPersonDescriptor;

public class EditPersonCommandParserTest {

    private final EditPersonCommandParser parser = new EditPersonCommandParser();

    @Test
    public void parse_addCommand_success() {
        // Assuming you have a valid UUID for testing
        String uuid = "123e4567-e89b-12d3-a456-426614174000";
        String input = "/add " + uuid + " /name John /email john@example.com";
        try {
            Command command = parser.parse(input);
            assertTrue(command instanceof EditPersonCommand);
            EditPersonCommand editCommand = (EditPersonCommand) command;
            EditPersonDescriptor descriptor = editCommand.getDescriptor();
            assertTrue(descriptor.getAttributes().containsKey("name"));
            assertTrue(descriptor.getAttributes().containsKey("email"));
        } catch (ParseException e) {
            fail("Parsing should not have failed.");
        }
    }

    @Test
    public void parse_incorrectFormat_throwsParseException() {
        String input = "/add";
        assertThrows(ParseException.class, () -> parser.parse(input), "Incorrect command format.");
    }

    @Test
    public void parse_invalidUuid_throwsParseException() {
        String input = "/add 123 /name John /email john@example.com";
        assertThrows(ParseException.class, () -> parser.parse(input), "Invalid UUID format.");
    }

    // Additional tests for other scenarios like /delete action can be similarly structured.
}

