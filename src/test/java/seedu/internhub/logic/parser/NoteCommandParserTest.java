package seedu.internhub.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.logic.commands.NoteCommand;
import seedu.internhub.logic.parser.exceptions.ParseException;

class NoteCommandParserTest {

    @Test
    void parse_validArgs_returnsNoteCommand() throws ParseException {
        NoteCommandParser parser = new NoteCommandParser();
        NoteCommand expectedCommand = new NoteCommand(Index.fromZeroBased(0));

        assertEquals(expectedCommand, parser.parse("1"));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        NoteCommandParser parser = new NoteCommandParser();

        assertThrows(ParseException.class, () -> parser.parse("abc"));
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    void parse_invalidIndex_throwsParseException() {
        NoteCommandParser parser = new NoteCommandParser();

        assertThrows(ParseException.class, () -> parser.parse("0"));
        assertThrows(ParseException.class, () -> parser.parse("-1"));
    }

    @Test
    void parse_withWhitespaceAndValidArgs_returnsNoteCommand() throws ParseException {
        NoteCommandParser parser = new NoteCommandParser();
        NoteCommand expectedCommand = new NoteCommand(Index.fromZeroBased(1));

        assertEquals(expectedCommand, parser.parse("   2   "));
    }

    @Test
    void parse_withWhitespaceAndInvalidArgs_throwsParseException() {
        NoteCommandParser parser = new NoteCommandParser();

        assertThrows(ParseException.class, () -> parser.parse("   abc   "));
        assertThrows(ParseException.class, () -> parser.parse("   "));
    }
}
