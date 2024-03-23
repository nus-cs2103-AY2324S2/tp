package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        assertDoesNotThrow(() -> parser.parse("filter n/ Alice"));
        assertDoesNotThrow(() -> parser.parse("filter t/ friend"));
        assertDoesNotThrow(() -> parser.parse("filter T/ Team A"));
        assertDoesNotThrow(() -> parser.parse("filter r/ Manager"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("filter"));
        assertThrows(ParseException.class, () -> parser.parse("filter n/ Alice t/"));
    }

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        FilterCommandParser parser = new FilterCommandParser();
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }
}
