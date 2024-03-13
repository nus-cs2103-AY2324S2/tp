package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.IMPORTANT_DATE_DESC_DATE;
import static seedu.address.logic.commands.CommandTestUtil.IMPORTANT_DATE_DESC_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IMPORTANT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddImportantDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.ImportantDate;

public class AddImportantDateParserTest {
    private final AddImportantDateParser parser = new AddImportantDateParser();
    private final ImportantDate validDate = new ImportantDate(VALID_IMPORTANT_DATE_NAME, VALID_IMPORTANT_DATE);
    private final ImportantDate validDateTime = new ImportantDate(VALID_IMPORTANT_DATE_NAME, VALID_IMPORTANT_DATETIME);

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse(VALID_IMPORTANT_DATE));
        assertThrows(ParseException.class, () -> parser.parse("1" + INVALID_IMPORTANT_DATE_DESC));
        assertThrows(ParseException.class, () -> parser.parse(" " + VALID_IMPORTANT_DATE));
        assertThrows(ParseException.class, () -> parser.parse(" " + VALID_IMPORTANT_DATETIME));
        assertThrows(ParseException.class, () -> parser.parse(" " + INVALID_IMPORTANT_DATE_DESC));
    }

    @Test
    public void parse_validArgs_returnsAddImportantDateCommand() throws ParseException {
        Index validIndex = INDEX_FIRST_PATIENT;

        String userInput;
        AddImportantDateCommand expected;
        AddImportantDateCommand command;

        userInput = validIndex.getOneBased() + IMPORTANT_DATE_DESC_DATE;
        expected = new AddImportantDateCommand(validIndex, validDate);
        command = parser.parse(userInput);
        assertEquals(expected, command);

        userInput = validIndex.getOneBased() + IMPORTANT_DATE_DESC_DATETIME;
        expected = new AddImportantDateCommand(validIndex, validDateTime);
        command = parser.parse(userInput);
        assertEquals(expected, command);
    }
}
