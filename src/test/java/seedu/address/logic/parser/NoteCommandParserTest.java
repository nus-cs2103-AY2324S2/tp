package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.person.DeadlineNote;
import seedu.address.model.person.Note;

public class NoteCommandParserTest {

    private NoteCommandParser parser = new NoteCommandParser();
    private Note validNote = new Note("get kibble");

    @Test
    public void parse_validArgs_returnsDeadlineNoteCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + NOTE_DESC_BOB
                + DEADLINE_DESC_BOB, new NoteCommand(BOB.getName(), new DeadlineNote("get kibble",
                "2019-10-10")));
    }

    @Test
    public void parse_invalidArgsDeadline_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + INVALID_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + NOTE_DESC_BOB,
                new NoteCommand(BOB.getName(), validNote));
    }
}
