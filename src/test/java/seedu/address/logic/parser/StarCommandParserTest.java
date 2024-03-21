package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;


public class StarCommandParserTest {

    private final StarCommandParser parser = new StarCommandParser();

    @Test
    public void parse_emptyInput_throwsParseException() {
        // Arrange
        String userInput = "";

        // Act and Assert
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_nullInput_throwsNullPointerException() {
        // Act and Assert
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }
}
