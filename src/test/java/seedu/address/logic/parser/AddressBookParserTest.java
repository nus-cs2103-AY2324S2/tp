package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

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
import seedu.address.logic.commands.EditMaintainerCommand;
import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnpinCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.KeywordPredicate;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.EditMaintainerDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditStaffDescriptorBuilder;
import seedu.address.testutil.EditSupplierDescriptorBuilder;
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
                DeleteCommand.COMMAND_WORD + " ; name : " + ALICE.getName());
        assertEquals(new DeleteCommand(ALICE.getName()), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " " + PREFIX_NAME
            + person.getName() + " " + PREFIX_FIELD + "{ "
            + PersonUtil.getEditPersonDescriptorDetails(descriptor) + "}");
        assertEquals(command, new EditCommand(person.getName(), descriptor));
    }

    @Test
    public void parseCommand_editStaff() throws Exception {
        Staff staff = new StaffBuilder().build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(staff).build();
        EditStaffCommand command = (EditStaffCommand) parser.parseCommand(EditStaffCommand.COMMAND_WORD + " "
                + PREFIX_NAME
                + staff.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditStaffDescriptorDetails(descriptor) + "}");
        assertEquals(command, new EditStaffCommand(staff.getName(), descriptor));
    }

    @Test
    public void parseCommand_editSupplier() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(supplier).build();
        EditSupplierCommand command = (EditSupplierCommand) parser.parseCommand(EditSupplierCommand.COMMAND_WORD + " "
                + PREFIX_NAME
                + supplier.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditSupplierDescriptorDetails(descriptor) + "}");
        assertEquals(new EditSupplierCommand(supplier.getName(), descriptor), command);
    }

    @Test
    public void parseCommand_editMaintainer() throws Exception {
        Maintainer maintainer = new MaintainerBuilder().build();
        EditMaintainerDescriptor descriptor = new EditMaintainerDescriptorBuilder(maintainer).build();
        EditMaintainerCommand command = (EditMaintainerCommand)
                parser.parseCommand(EditMaintainerCommand.COMMAND_WORD + " "
                + PREFIX_NAME
                + maintainer.getName() + " " + PREFIX_FIELD + "{ "
                + PersonUtil.getEditMaintainerDescriptorDetails(descriptor) + "}");
        assertEquals(new EditMaintainerCommand(maintainer.getName(), descriptor), command);
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
        HelpCommand command = (HelpCommand) parser.parseCommand(
                HelpCommand.COMMAND_WORD + " ; command : " + "add");
        assertEquals(new HelpCommand("add"), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_search() throws Exception {
        String keyword = " ; name : Poochie";
        ArgumentMultimap token = ArgumentTokenizer.tokenize(keyword, PREFIX_NAME);
        SearchCommand command = (SearchCommand) parser.parseCommand(
                SearchCommand.COMMAND_WORD + keyword);
        assertEquals(new SearchCommand(new KeywordPredicate(token)), command);
    }


    @Test
    public void parseCommand_note() throws Exception {
        NoteCommand command = (NoteCommand) parser.parseCommand("/note ; name : Bob Choo ; note : get kibble");
        assertTrue(command instanceof NoteCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        RedoCommand command = (RedoCommand) parser.parseCommand("/redo");
        assertTrue(command instanceof RedoCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        UndoCommand command = (UndoCommand) parser.parseCommand("/undo");
        assertTrue(command instanceof UndoCommand);
    }

    @Test
    public void parseCommand_pin() throws Exception {
        PinCommand command = (PinCommand) parser.parseCommand("/pin ; name : Bob Choo");
        assertEquals(new PinCommand(new Name("Bob Choo")), command);
    }

    @Test
    public void parseCommand_unpin() throws Exception {
        UnpinCommand command = (UnpinCommand) parser.parseCommand("/unpin ; name : Bob Choo");
        assertEquals(new UnpinCommand(new Name("Bob Choo")), command);
    }

    @Test
    public void parseCommand_remind() throws Exception {
        RemindCommand command = (RemindCommand) parser.parseCommand("/remind");
        assertTrue(command instanceof RemindCommand);
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
