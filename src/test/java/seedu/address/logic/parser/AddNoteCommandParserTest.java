package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddNoteCommand;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();
    private final String nonEmptyNote = "Some note.";

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddNoteCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddNoteCommand.COMMAND_WORD + " " + nonEmptyNote, expectedMessage);
    }
}
