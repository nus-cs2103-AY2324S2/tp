package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EditRelationshipCommandParserTest {
    private final EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

    @Test
    public void parse_invalidInputMissingParts_throwsParseException() {
        String userInput = "uuid1 uuid2 family";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidUuid_throwsParseException() {
        String userInput = "invalidUuid uuid2 family friend";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidRelationshipDescriptors_throwsParseException() {
        String userInput = "uuid1 uuid2 invalidRelationship friend";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputSameDescriptors_throwsParseException() {
        String userInput = "uuid1 uuid2 family family";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }


    @Test
    public void parse_validInput_success() {
        // Valid input with UUIDs and relationship descriptors
        String userInput = "1234 "
                + "5432 "
                + "family friend";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        try {
            EditRelationshipCommand command = parser.parse(userInput);
            assertNotNull(command);
        } catch (ParseException e) {
            fail("Unexpected ParseException thrown");
        }
    }

    @Test
    public void parse_invalidInput_wrongFormat_exceptionThrown() {
        // Invalid input with wrong format
        String userInput = "invalid input";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInput_invalidUuid_exceptionThrown() {
        // Invalid input with invalid UUIDs
        String userInput = "invalid-uuid invalid-uuid oldDescriptor newDescriptor";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
