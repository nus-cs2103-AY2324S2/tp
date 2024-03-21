package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        String userInput = "invalidUuid uuid2 family friends";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputInvalidRelationshipDescriptors_throwsParseException() {
        String userInput = "uuid1 uuid2 invalidRelationship friends";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidInputSameDescriptors_throwsParseException() {
        String userInput = "uuid1 uuid2 family family";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
