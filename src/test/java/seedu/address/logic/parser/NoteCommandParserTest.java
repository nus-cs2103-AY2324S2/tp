package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.startup.Note;

public class NoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + " Some note";
        Note note = new Note("Some note");
        NoteCommand.NoteStartupDescriptor descriptor = new NoteCommand.NoteStartupDescriptor();
        descriptor.setNote(note);
        NoteCommand expectedCommand = new NoteCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(new NoteCommandParser(), userInput, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String userInput = "a Some note";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

        CommandParserTestUtil.assertParseFailure(new NoteCommandParser(), userInput, expectedMessage);
    }

    @Test
    public void parse_missingNoteDescription_throwsParseException() {
        String userInput = "1 ";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

        CommandParserTestUtil.assertParseFailure(new NoteCommandParser(), userInput, expectedMessage);
    }

    @Test
    public void parse_extraSpaces_returnsNoteCommand() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = "  1   Some note  ";
        Note note = new Note("Some note");
        NoteCommand.NoteStartupDescriptor descriptor = new NoteCommand.NoteStartupDescriptor();
        descriptor.setNote(note);
        NoteCommand expectedCommand = new NoteCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(new NoteCommandParser(), userInput, expectedCommand);
    }

}
