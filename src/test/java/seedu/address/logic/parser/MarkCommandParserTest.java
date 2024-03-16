package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MarkCommandParserTest {

    private final MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() throws ParseException {
        String args = "1";
        MarkCommand expectedCommand = new MarkCommand(ParserUtil.parseIndex("1"));

        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        // Missing index
        String args = "";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        // Invalid index format
        String args = "invalid";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_extraArgs_throwsParseException() {
        // Extra arguments
        String args = "1 extra";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}

