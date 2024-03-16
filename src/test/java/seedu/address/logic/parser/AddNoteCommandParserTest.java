package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

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
