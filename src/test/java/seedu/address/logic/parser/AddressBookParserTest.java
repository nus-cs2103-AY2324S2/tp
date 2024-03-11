package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMaintainerCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HelpDeleteCommand;
import seedu.address.logic.commands.HelpEditCommand;
import seedu.address.logic.commands.HelpPoochMaintenanceCommand;
import seedu.address.logic.commands.HelpPoochStaffCommand;
import seedu.address.logic.commands.HelpPoochSupplierCommand;
import seedu.address.logic.commands.HelpSearchCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addStaff() throws Exception {
        Staff person = new StaffBuilder().build();
        AddStaffCommand command = (AddStaffCommand) parser.parseCommand(
                PersonUtil.getAddStaffCommand(person));
        assertEquals(new AddStaffCommand(person), command);
    }

    @Test
    public void parseCommand_addSupplier() throws Exception {
        Supplier person = new SupplierBuilder().build();
        AddSupplierCommand command = (AddSupplierCommand) parser.parseCommand(
                PersonUtil.getAddSupplierCommand(person));
        assertEquals(new AddSupplierCommand(person), command);
    }

    @Test
    public void parseCommand_addMaintainer() throws Exception {
        Maintainer person = new MaintainerBuilder().build();
        AddMaintainerCommand command = (AddMaintainerCommand) parser.parseCommand(
                PersonUtil.getAddMaintainerCommand(person));
        assertEquals(new AddMaintainerCommand(person), command);
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
    public void parseCommand_helpDelete() throws Exception {
        assertTrue(parser.parseCommand(HelpDeleteCommand.COMMAND_WORD) instanceof HelpDeleteCommand);
        assertTrue(parser.parseCommand(HelpDeleteCommand.COMMAND_WORD + " 3") instanceof HelpDeleteCommand);
    }

    @Test
    public void parseCommand_helpEdit() throws Exception {
        assertTrue(parser.parseCommand(HelpEditCommand.COMMAND_WORD) instanceof HelpEditCommand);
        assertTrue(parser.parseCommand(HelpEditCommand.COMMAND_WORD + " 3") instanceof HelpEditCommand);
    }

    @Test
    public void parseCommand_helpSearch() throws Exception {
        assertTrue(parser.parseCommand(HelpSearchCommand.COMMAND_WORD) instanceof HelpSearchCommand);
        assertTrue(parser.parseCommand(HelpSearchCommand.COMMAND_WORD + " 3") instanceof HelpSearchCommand);
    }

    @Test
    public void parseCommand_helpPoochMaintenance() throws Exception {
        assertTrue(parser.parseCommand(HelpPoochMaintenanceCommand.COMMAND_WORD) instanceof HelpPoochMaintenanceCommand);
        assertTrue(parser.parseCommand(HelpPoochMaintenanceCommand.COMMAND_WORD + " 3") instanceof HelpPoochMaintenanceCommand);
    }

    @Test
    public void parseCommand_helpPoochSupplier() throws Exception {
        assertTrue(parser.parseCommand(HelpPoochSupplierCommand.COMMAND_WORD) instanceof HelpPoochSupplierCommand);
        assertTrue(parser.parseCommand(HelpPoochSupplierCommand.COMMAND_WORD + " 3") instanceof HelpPoochSupplierCommand);
    }

    @Test
    public void parseCommand_helpPoochStaff() throws Exception {
        assertTrue(parser.parseCommand(HelpPoochStaffCommand.COMMAND_WORD) instanceof HelpPoochStaffCommand);
        assertTrue(parser.parseCommand(HelpPoochStaffCommand.COMMAND_WORD + " 3") instanceof HelpPoochStaffCommand);
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
