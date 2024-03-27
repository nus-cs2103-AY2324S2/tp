package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.person.Note;

public class NoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified (note John)
        assertParseFailure(parser, NOTE_DESC_AMY, String.format(Messages.MESSAGE_NO_INDEX,
                NoteCommand.MESSAGE_USAGE));

        // no index and no field specified (note)
        assertParseFailure(parser, "", String.format(Messages.MESSAGE_NO_INDEX, NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index (note -5)
        assertParseFailure(parser, "-5" + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

        // zero index (note 0 n/John)
        assertParseFailure(parser, "0" + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble (note 1 hey)
        assertParseFailure(parser, "1 some random string",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + NOTE_DESC_AMY;

        NoteCommand expectedCommand = new NoteCommand(targetIndex, new Note(VALID_NOTE_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_repeatedNoteField_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidUserInput = targetIndex.getOneBased() + NOTE_DESC_AMY + NOTE_DESC_BOB;

        NoteCommandParser noteCommandParser = new NoteCommandParser();

        assertParseFailure(noteCommandParser, invalidUserInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE));
    }
}
