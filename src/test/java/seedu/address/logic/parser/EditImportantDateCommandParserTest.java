package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditImportantDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.ImportantDate;

public class EditImportantDateCommandParserTest {
    private final EditImportantDateCommandParser editImportantDateCommandParser = new EditImportantDateCommandParser();
    private final ImportantDate validDate = new ImportantDate(VALID_IMPORTANT_DATE_NAME, VALID_IMPORTANT_DATE);

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse(""));
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse("1"));
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse(VALID_IMPORTANT_DATE_NAME));
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse(VALID_IMPORTANT_DATE));
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse("1"
                + PREFIX_IMPORTANT_DATE + "1"));
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse("1 "
                + PREFIX_IMPORTANT_DATE + " 1 " + PREFIX_NAME + VALID_IMPORTANT_DATE_NAME));
        assertThrows(ParseException.class, () -> editImportantDateCommandParser.parse("1 "
                + PREFIX_IMPORTANT_DATE + " -1 " + PREFIX_NAME + VALID_IMPORTANT_DATE_NAME + " " + PREFIX_DATETIME
                + "20-12-2021"));
    }

    @Test
    public void parse_validArgs_returnEditImportantDateCommand() {
        String userInput;
        EditImportantDateCommand editImportantDateCommand;
        userInput = INDEX_FIRST_PATIENT.getOneBased() + " e/" + INDEX_FIRST_EVENT.getOneBased() + " n/"
                + VALID_IMPORTANT_DATE_NAME + " d/" + VALID_IMPORTANT_DATE;
        editImportantDateCommand = new EditImportantDateCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_EVENT, validDate);
        assertParseSuccess(editImportantDateCommandParser, userInput, editImportantDateCommand);
    }
}
