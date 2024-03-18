package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Event;

public class EditEventCommandParserTest {
    private final EditEventCommandParser editEventCommandParser = new EditEventCommandParser();
    private final Event validDate = new Event(VALID_EVENT_NAME, VALID_EVENT_DATE);

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> editEventCommandParser.parse(""));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse("1"));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse(VALID_EVENT_NAME));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse(VALID_EVENT_DATE));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse("1"
                + PREFIX_EVENT + "1"));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse("1 "
                + PREFIX_EVENT + " 1 " + PREFIX_NAME + VALID_EVENT_NAME));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse("-1 "
                + PREFIX_EVENT + " 1 " + PREFIX_NAME + VALID_EVENT_NAME + " " + PREFIX_DATETIME
                + VALID_EVENT_DATE));
        assertThrows(ParseException.class, () -> editEventCommandParser.parse("1 "
                + PREFIX_EVENT + " -1 " + PREFIX_NAME + VALID_EVENT_NAME + " " + PREFIX_DATETIME
                + VALID_EVENT_DATE));
    }

    @Test
    public void parse_validArgs_returnEditEventCommand() {
        String userInput;
        EditEventCommand editEventCommand;
        userInput = INDEX_FIRST_PATIENT.getOneBased() + " e/" + INDEX_FIRST_EVENT.getOneBased() + " n/"
                + VALID_EVENT_NAME + " d/" + VALID_EVENT_DATE;
        editEventCommand = new EditEventCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validDate);
        assertParseSuccess(editEventCommandParser, userInput, editEventCommand);
    }
}
