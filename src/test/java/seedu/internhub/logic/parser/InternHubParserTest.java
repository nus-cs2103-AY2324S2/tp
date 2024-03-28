package seedu.internhub.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhub.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internhub.testutil.Assert.assertThrows;
import static seedu.internhub.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.logic.commands.AddCommand;
import seedu.internhub.logic.commands.ClearCommand;
import seedu.internhub.logic.commands.DeleteCommand;
import seedu.internhub.logic.commands.EditCommand;
import seedu.internhub.logic.commands.ExitCommand;
import seedu.internhub.logic.commands.FilterCommand;
import seedu.internhub.logic.commands.FindCommand;
import seedu.internhub.logic.commands.HelpCommand;
import seedu.internhub.logic.commands.ListCommand;
import seedu.internhub.logic.commands.NoteCommand;
import seedu.internhub.logic.commands.ViewCommand;
import seedu.internhub.logic.parser.exceptions.ParseException;
import seedu.internhub.model.person.MatchingTagPredicate;
import seedu.internhub.model.person.NameContainsKeywordsPredicate;
import seedu.internhub.model.person.Person;
import seedu.internhub.testutil.EditPersonDescriptorBuilder;
import seedu.internhub.testutil.PersonBuilder;
import seedu.internhub.testutil.PersonUtil;


public class InternHubParserTest {

    private final InternHubParser parser = new InternHubParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName("Betty").build();

        EditCommand command = (EditCommand) parser.parseCommand(
                String.format("%s %d %s", EditCommand.COMMAND_WORD, index.getOneBased(), "c/" + "Betty"));

        assertEquals(new EditCommand(index, descriptor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        String tag = "NR";
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + String.join(" ", tag));
        assertEquals(new FilterCommand(new MatchingTagPredicate(tag)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + (index).getOneBased());
        assertEquals(new ViewCommand(index), command);
    }
    @Test
    public void parseCommand_note() throws Exception {
        Index index = INDEX_FIRST_PERSON;
        NoteCommand command = (NoteCommand) parser.parseCommand(
                NoteCommand.COMMAND_WORD + " " + (index).getOneBased());
        assertEquals(new NoteCommand(index), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
