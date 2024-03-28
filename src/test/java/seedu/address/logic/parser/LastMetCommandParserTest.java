package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.LastMetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LastMet;

public class LastMetCommandParserTest {

    private final LastMetCommandParser parser = new LastMetCommandParser();

    @Test
    public void parse_validArgs_returnsLastMetCommand() throws ParseException {
        String args = "1 " + CliSyntax.PREFIX_LASTMET + "2022-12-31";
        LastMetCommand expectedCommand = new LastMetCommand(ParserUtil.parseIndex("1"),
                new LastMet(DateUtil.parseStringToDate("2022-12-31")));

        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        // Missing index
        String args = CliSyntax.PREFIX_LASTMET + "2022-12-31";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_missingLastMetPrefix_throwsParseException() {
        // Missing last met prefix
        String args = "1 2022-12-31";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        // Invalid index format
        String args = "invalid " + CliSyntax.PREFIX_LASTMET + "2022-12-31";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidLastMetFormat_throwsParseException() {
        // Invalid last met format
        String args = "1 " + CliSyntax.PREFIX_LASTMET + "invalid-date-format";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
