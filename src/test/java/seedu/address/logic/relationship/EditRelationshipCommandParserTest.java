package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EditRelationshipCommandParserTest {
    private EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

    @Test
    public void parse_invalidInputMissingParts_throwsParseException() {
        String userInput = "uuid1 uuid2 siblings";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidUuid_throwsParseException() {
        String userInput = "invalidUuid uuid2 siblings friend";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidRelationshipDescriptors_throwsParseException() {
        String userInput = "uuid1 uuid2 invalidRelationship friend";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputSameDescriptors_throwsParseException() {
        String userInput = "uuid1 uuid2 siblings siblings";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }


    @Test
    public void parse_validInputWithoutRoles_success() {
        // Valid input with UUIDs and relationship descriptors
        String userInput = "1234 "
                + "5432 "
                + "siblings friend";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        try {
            EditRelationshipCommand command = parser.parse(userInput);
            assertNotNull(command);
        } catch (ParseException e) {
            fail("Unexpected ParseException thrown");
        }
    }

    @Test
    public void parse_validInputWithRoles_success() {
        // Valid input with UUIDs and relationship descriptors
        String userInput = "Boss 1234 "
                + "subordinate 5432 "
                + "siblings workbuddies";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        try {
            EditRelationshipCommand command = parser.parse(userInput);
            assertNotNull(command);
        } catch (ParseException e) {
            fail("Unexpected ParseException thrown");
        }
    }

    @Test
    public void parse_validInputWithRoleInvalidNewRelationshipDescriptor_throwsParseException() {
        String userInput = "Role1 1234 Role2 1245 oldDescriptor family";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidUuidWithRole_throwsParseException() {
        String userInput = "Role1 invalidUuid Role2 uuid2 oldDescriptor newDescriptor";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidRole_throwsParseException() {
        String userInput = "Role1 uuid1 Role2 uuid2 oldDescriptor newDescriptor";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputMissingPartsWithRoles_throwsParseException() {
        String userInput = "Role1 uuid1 Role2 uuid2 oldDescriptor";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputWithFamilyDescriptor_throwsParseException() {
        String userInput = "role1 1234 role2 1256 family newDescriptor";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_invalidRoleInput_throwsIllegalArgumentException() {
        String userInput = "parent 0001 123 0002 friends bioparents";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputWrongFormat_exceptionThrown() {
        // Invalid input with wrong format
        String userInput = "invalid input";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidUuid_exceptionThrown() {
        // Invalid input with invalid UUIDs
        String userInput = "invalid-uuid invalid-uuid oldDescriptor newDescriptor";

        EditRelationshipCommandParser parser = new EditRelationshipCommandParser();

        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
