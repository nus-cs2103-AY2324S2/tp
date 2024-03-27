package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DESC_DATE;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DESC_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Event;

public class AddEventParserTest {
    private final AddEventParser parser = new AddEventParser();
    private final Event validDate = new Event(VALID_EVENT_NAME, VALID_EVENT_DATE);
    private final Event validDateTime = new Event(VALID_EVENT_NAME, VALID_EVENT_DATETIME);

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse(VALID_EVENT_DATE));
        assertThrows(ParseException.class, () -> parser.parse("1" + INVALID_EVENT_DESC));
        assertThrows(ParseException.class, () -> parser.parse(" " + VALID_EVENT_DATE));
        assertThrows(ParseException.class, () -> parser.parse(" " + VALID_EVENT_DATETIME));
        assertThrows(ParseException.class, () -> parser.parse(" " + INVALID_EVENT_DESC));
    }

    @Test
    public void parse_validArgs_returnsAddEventCommand() throws ParseException {
        Index validIndex = INDEX_FIRST_PATIENT;

        String userInput;
        AddEventCommand expected;
        AddEventCommand command;

        userInput = validIndex.getOneBased() + EVENT_DESC_DATE;
        expected = new AddEventCommand(validIndex, validDate);
        command = parser.parse(userInput);
        assertEquals(expected, command);

        userInput = validIndex.getOneBased() + EVENT_DESC_DATETIME;
        expected = new AddEventCommand(validIndex, validDateTime);
        command = parser.parse(userInput);
        assertEquals(expected, command);
    }
}
