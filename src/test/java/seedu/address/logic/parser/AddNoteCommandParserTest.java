package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Note;

public class AddNoteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedIndex = Index.fromOneBased(1);
        Note expectedNote = new Note("very hardworking!!!");
        assertParseSuccess(parser, "1 note/very hardworking!!!", new AddNoteCommand(expectedIndex, expectedNote));
    }

    @Test
    public void parse_missingArgument_throwParseException() {
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "hardworking", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a note/hardworking"));
    }

    @Test
    public void parse_emptyInput_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_zeroBasedIndex_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse("0 note/hardworking"));
    }
}
