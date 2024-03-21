package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IDENTITY_CARD_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Disabled("Requires add Command to be implemented")
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
        String args = "s1234567a";
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + args);
        assertEquals(new DeleteCommand(new IdentityCardNumberMatchesPredicate(
                new IdentityCardNumber(args))), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withIC(VALID_IDENTITY_CARD_NUMBER_AMY)
                .withSex(VALID_SEX_AMY).withAge(VALID_AGE_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        String userInput = EditCommand.COMMAND_WORD + " " + VALID_IDENTITY_CARD_NUMBER_AMY
                + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + IC_DESC_AMY + AGE_DESC_AMY + SEX_DESC_AMY + ADDRESS_DESC_AMY;
        EditCommand expectedCommand = new EditCommand(new IdentityCardNumberMatchesPredicate(
                new IdentityCardNumber(VALID_IDENTITY_CARD_NUMBER_AMY)), descriptor);

        EditCommand command = (EditCommand) parser.parseCommand(userInput);

        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String args = "s1234567a";
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " " + args);
        assertEquals(new FindCommand(new IdentityCardNumberMatchesPredicate(new IdentityCardNumber(args))), command);
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
    public void parseCommand_addNote_withReplaceFlag() throws Exception {
        final Note note = new Note("Some note.");
        AddNoteCommand command = (AddNoteCommand) parser.parseCommand(AddNoteCommand.COMMAND_WORD + " "
                + PREFIX_IC + "S0123456Q " + PREFIX_NOTE + note.value + " " + PREFIX_FLAG);
        assertEquals(new AddNoteCommand(new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S0123456Q")),
                note, true), command);
    }

    @Test
    public void parseCommand_addNote_withoutReplaceFlag() throws Exception {
        final Note note = new Note("Some note.");
        AddNoteCommand command = (AddNoteCommand) parser.parseCommand(AddNoteCommand.COMMAND_WORD + " "
                + PREFIX_IC + "S0123456Q " + PREFIX_NOTE + note.value);
        assertEquals(new AddNoteCommand(new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S0123456Q")),
                note, false), command);
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
