package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddNoteCommand.PREFIX_DATE;
import static seedu.address.logic.commands.AddNoteCommand.PREFIX_NOTE;
import static seedu.address.logic.commands.AddNoteCommand.PREFIX_TIME;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_FLU;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE_TIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.model.person.note.Description;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String command = " 1 d/19-02-2024 t/2130 n/General Flu";

        assertParseSuccess(parser, command, new AddNoteCommand(INDEX_FIRST_PERSON, VALID_NOTE_FLU));
    }

    @Test
    public void parse_repeatedFields_failure() {
        // Repeated date.
        assertParseFailure(parser, "1 " + DATE_DESC + TIME_DESC + NOTE_DESC + DATE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE));

        // Repeated Time.
        assertParseFailure(parser, "1 " + DATE_DESC + TIME_DESC + NOTE_DESC + TIME_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));

        // Repeated Note.
        assertParseFailure(parser, "1 " + DATE_DESC + TIME_DESC + NOTE_DESC + NOTE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);
        // Missing date prefix.
        assertParseFailure(parser, "1 " + TIME_DESC + NOTE_DESC, message);

        // Missing time prefix.
        assertParseFailure(parser, "1 " + DATE_DESC + NOTE_DESC, message);

        // Missing note prefix.
        assertParseFailure(parser, "1 " + DATE_DESC + TIME_DESC, message);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid date.
        assertParseFailure(parser, "1 " + INVALID_DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_DATE_TIME);

        // Invalid time.
        assertParseFailure(parser, "1 " + DATE_DESC + INVALID_TIME_DESC + NOTE_DESC, MESSAGE_INVALID_DATE_TIME);

        // Invalid note.
        assertParseFailure(parser, "1 " + DATE_DESC + TIME_DESC + INVALID_NOTE_DESC, Description.MESSAGE_CONSTRAINTS);
    }
}