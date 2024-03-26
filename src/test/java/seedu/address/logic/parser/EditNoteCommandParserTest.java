package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE_TIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.address.model.person.note.Description;

public class EditNoteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE);
    private final EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String command = " 1 1 d/19-02-2024 t/2130 n/General Flu";
        assertParseSuccess(parser, command, new EditNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE,
                new EditNoteDescriptor(DESC_NOTE1)));
    }
    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1 1", EditNoteCommand.MESSAGE_NOTE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
    @Nested
    class InvalidIndexTests {
        @Test
        public void parse_negativeIndex_failure() {
            assertParseFailure(parser, "-1 1" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
            assertParseFailure(parser, "1 -1" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
            assertParseFailure(parser, "-1 -1" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
        }

        @Test
        public void parse_zeroIndex_failure() {
            assertParseFailure(parser, "0 1" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
            assertParseFailure(parser, "1 0" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
            assertParseFailure(parser, "0 0" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
        }
    }

    @Nested
    class InvalidArgumentTests {
        @Test
        public void parse_invalidArguments_failure() {
            assertParseFailure(parser, "some random string 1" + DATE_DESC + TIME_DESC
                    + NOTE_DESC, MESSAGE_INVALID_FORMAT);
            assertParseFailure(parser, "1 some random string" + DATE_DESC + TIME_DESC
                    + NOTE_DESC, MESSAGE_INVALID_FORMAT);
        }

        @Test
        public void parse_invalidPrefix_failure() {
            assertParseFailure(parser, "1 1 z/ string" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
            assertParseFailure(parser, "1 z/ string 1" + DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_FORMAT);
        }
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid date.
        assertParseFailure(parser, "1 1" + INVALID_DATE_DESC + TIME_DESC + NOTE_DESC, MESSAGE_INVALID_DATE_TIME);

        // Invalid time.
        assertParseFailure(parser, "1 1" + DATE_DESC + INVALID_TIME_DESC + NOTE_DESC, MESSAGE_INVALID_DATE_TIME);

        // Invalid note.
        assertParseFailure(parser, "1 1" + DATE_DESC + TIME_DESC + INVALID_NOTE_DESC, Description.MESSAGE_CONSTRAINTS);
    }

}
