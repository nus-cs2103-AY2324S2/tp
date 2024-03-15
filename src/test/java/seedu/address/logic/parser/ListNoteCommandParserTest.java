package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListNoteCommandParserTest {
    @Test
    public void parse_invalidIndex_throwsParseException() {
        String invalidInput = "a";
        ListNoteCommandParser parser = new ListNoteCommandParser();
        assertThrows(ParseException.class, () -> parser.parse(invalidInput));
    }

    @Test
    public void parse_outOfRangeIndex_throwsParseException() {
        String outOfRangeInput = "-1";
        ListNoteCommandParser parser = new ListNoteCommandParser();
        assertThrows(ParseException.class, () -> parser.parse(outOfRangeInput));
    }
}
