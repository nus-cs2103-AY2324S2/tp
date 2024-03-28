package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.parser.DeleteAttributeCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAttributeCommandParserTest {
    private final DeleteAttributeCommandParser parser = new DeleteAttributeCommandParser();
    @Test
    public void parse_validDeleteCommand_success() throws ParseException {
        String userInput = "12345 /nickname";
        Command command = parser.parse(userInput);
        assertTrue(command instanceof DeleteAttributeCommand);
    }
    @Test
    public void parse_noUuid_throwsParseException() {
        String noUuid = "/nickname";
        assertThrows(ParseException.class, () -> parser.parse(noUuid));
    }
    @Test
    public void parse_noAttribute_throwsParseException() {
        String noAttribute = "12345";
        assertThrows(ParseException.class, () -> parser.parse(noAttribute));
    }
    @Test
    public void parse_malformedCommand_throwsParseException() {
        String malformedCommand = "12345 12345 /nickname";
        assertThrows(ParseException.class, () -> parser.parse(malformedCommand));
    }
    @Test
    public void parse_malformedCommand2_throwsParseException() {
        String malformedCommand = "12345 nickname";
        assertThrows(ParseException.class, () -> parser.parse(malformedCommand));
    }

}
