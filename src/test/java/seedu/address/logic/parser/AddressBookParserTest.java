package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddTagsCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTagsCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Event;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new AddCommand(patient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        // this means that "clear 123" also works
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PATIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PATIENT.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PATIENT, descriptor), command);
    }

    @Test
    public void parseCommand_addTags() throws Exception {
        String args = "1 " + PREFIX_TAG + "tag1 " + PREFIX_TAG + "tag2 " + PREFIX_TAG + "tag3";
        AddTagsCommand command = (AddTagsCommand) parser.parseCommand(
                AddTagsCommand.COMMAND_WORD + " " + args);

        Set<Tag> expectedTags = new HashSet<>(Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3")));
        assertEquals(new AddTagsCommand(INDEX_FIRST_PATIENT, expectedTags), command);
    }

    @Test
    public void parseCommand_findTags() throws Exception {
        List<String> keywords = List.of("diabetes", "depression", "wheelchair");
        FindTagsCommand command = (FindTagsCommand) parser.parseCommand(
                FindTagsCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindTagsCommand(new TagContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_addEvent() throws Exception {
        String name = "Birthday";
        String date = "20-01-2022";
        String args = "1 " + PREFIX_NAME + name + " " + PREFIX_DATETIME + date;
        AddEventCommand command = (AddEventCommand) parser.parseCommand(
                AddEventCommand.COMMAND_WORD + " " + args);

        assertEquals(new AddEventCommand(INDEX_FIRST_PATIENT, new Event(name, date)), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        // this means that "exit 123" also works
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
        // this means that "help 123" also works
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        // this means that "list 123" also works
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
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
