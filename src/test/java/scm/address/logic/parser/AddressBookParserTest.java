package scm.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scm.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static scm.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static scm.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static scm.address.logic.parser.CliSyntax.PREFIX_FILENAME;
import static scm.address.logic.parser.CliSyntax.PREFIX_NAME;
import static scm.address.logic.parser.CliSyntax.PREFIX_TAG;
import static scm.address.testutil.Assert.assertThrows;
import static scm.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import scm.address.logic.commands.AddCommand;
import scm.address.logic.commands.ClearCommand;
import scm.address.logic.commands.DeleteCommand;
import scm.address.logic.commands.EditCommand;
import scm.address.logic.commands.EditCommand.EditPersonDescriptor;
import scm.address.logic.commands.ExitCommand;
import scm.address.logic.commands.FindAndExportCommand;
import scm.address.logic.commands.FindCommand;
import scm.address.logic.commands.HelpCommand;
import scm.address.logic.commands.ImportCommand;
import scm.address.logic.commands.ListCommand;
import scm.address.logic.parser.exceptions.ParseException;
import scm.address.model.person.NameContainsKeywordsPredicate;
import scm.address.model.person.Person;
import scm.address.testutil.EditPersonDescriptorBuilder;
import scm.address.testutil.PersonBuilder;
import scm.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
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
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
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
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_import() throws Exception {
        assertTrue(parser.parseCommand(ImportCommand.COMMAND_WORD + " f/filename")
                instanceof ImportCommand);
        assertTrue(parser.parseCommand(ImportCommand.COMMAND_WORD + " f/filename1 f/filename2")
                instanceof ImportCommand);
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

    @Test
    public void parseCommand_findAndExport() throws ParseException {
        String tag = "friends";
        String name = "John";
        String address = "123 Main St";
        String filename = "output.json";

        String input = FindAndExportCommand.COMMAND_WORD + " "
                + PREFIX_TAG + tag + " "
                + PREFIX_NAME + name + " "
                + PREFIX_ADDRESS + address + " "
                + PREFIX_FILENAME + filename;

        FindAndExportCommand expectedCommand = new FindAndExportCommand(tag, name, address, filename);

        FindAndExportCommand resultCommand = (FindAndExportCommand) parser.parseCommand(input);
        assertEquals(expectedCommand.getName(), resultCommand.getName());
        assertEquals(expectedCommand.getAddress(), resultCommand.getAddress());
        assertEquals(expectedCommand.getFilename(), resultCommand.getFilename());
    }
}
