package scrolls.elder.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.AddCommand;
import scrolls.elder.logic.commands.ClearCommand;
import scrolls.elder.logic.commands.DeleteCommand;
import scrolls.elder.logic.commands.EditCommand;
import scrolls.elder.logic.commands.ExitCommand;
import scrolls.elder.logic.commands.FindCommand;
import scrolls.elder.logic.commands.HelpCommand;
import scrolls.elder.logic.commands.ListCommand;
import scrolls.elder.logic.parser.exceptions.ParseException;
import scrolls.elder.model.person.NameContainsKeywordsPredicate;
import scrolls.elder.model.person.Person;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.EditPersonDescriptorBuilder;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.PersonUtil;
import scrolls.elder.testutil.TypicalIndexes;

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
                DeleteCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(
                descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                        -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, Messages.MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }
}
