package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {

    private ViewCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ViewCommandParser();
    }


    @Test
    public void parse_validInputArguments_success() throws ParseException {
        // Define valid input arguments
        String validArgs = "1";

        // Parse the arguments
        ViewCommand viewCommand = parser.parse(validArgs);

        // Verify the correctness of the parsed command
        assertEquals(new ViewCommand(Index.fromOneBased(1)), viewCommand);
    }

    @Test
    public void parse_invalidInputArguments_throwsParseException() {
        // Define invalid input arguments
        String invalidArgs = "abc";

        // Parsing invalid arguments should throw a ParseException
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }

    @Test
    public void parse_invalidIndexZero_throwsParseException() {
        // Define input arguments with invalid zero-based index
        String invalidArgs = "0";

        // Parsing invalid index should throw a ParseException
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }

    @Test
    public void parse_invalidIndexNegative_throwsParseException() {
        // Define input arguments with invalid negative index
        String invalidArgs = "-1";

        // Parsing invalid index should throw a ParseException
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }

    @Test
    public void parse_additionalArguments_throwsParseException() {
        // Define input arguments with additional arguments beyond the index
        String invalidArgs = "1 additional";

        // Parsing additional arguments should throw a ParseException
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }
}
