package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CountCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterHighPriorityCommand;
import seedu.address.logic.commands.FilterMedPriorityCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCompanyCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.PriorityCommand;
import seedu.address.logic.commands.StarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Priority;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

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
    public void parseCommand_delete() throws ParseException {
        String testName = ALICE.getName().toString();
        String userInput = DeleteCommand.COMMAND_WORD + " " + testName;
        DeleteCommand command = (DeleteCommand) parser.parseCommand(userInput);
        assertTrue(command instanceof DeleteCommand);
        assertEquals(testName, command.getTargetName());
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
    public void parseCommand_findCompany() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCompanyCommand command = (FindCompanyCommand) parser.parseCommand(
                FindCompanyCommand.COMMAND_WORD + " " + keywords.stream().collect(
                        Collectors.joining(" ")));
        assertEquals(new FindCompanyCommand(new CompanyContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_count() throws Exception {
        assertTrue(parser.parseCommand(CountCommand.COMMAND_WORD) instanceof CountCommand);
        assertTrue(parser.parseCommand(CountCommand.COMMAND_WORD + " 3") instanceof CountCommand);
    }

    @Test
    public void parseCommand_priorityHigh() throws Exception {
        String testName = ALICE.getName().toString();
        String userInput = PriorityCommand.COMMAND_WORD_HIGH + " Alice Pauline";
        PriorityCommand command = (PriorityCommand) parser.parseCommand(userInput);
        assertEquals(new PriorityCommand(testName, new Priority("high")), command);
    }

    @Test
    public void parseCommand_priorityMed() throws Exception {
        String testName = ALICE.getName().toString();
        String userInput = PriorityCommand.COMMAND_WORD_MED + " Alice Pauline";
        PriorityCommand command = (PriorityCommand) parser.parseCommand(userInput);
        assertEquals(new PriorityCommand(testName, new Priority("med")), command);
    }

    @Test
    public void parseCommand_filterHighPriority() throws Exception {
        assertTrue(parser.parseCommand(FilterHighPriorityCommand.COMMAND_WORD) instanceof FilterHighPriorityCommand);
    }

    @Test
    public void parseCommand_filterMedPriority() throws Exception {
        assertTrue(parser.parseCommand(FilterMedPriorityCommand.COMMAND_WORD) instanceof FilterMedPriorityCommand);
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
    public void parseCommand_validStarCommand_returnsStarCommand() throws Exception {
        AddressBookParser parser = new AddressBookParser();
        String userInput = "star John Doe";
        Command command = parser.parseCommand(userInput);
        assertEquals(StarCommand.class, command.getClass());
    }

    @Test
    public void parseCommand_invalidCommand_throwsParseException() {
        AddressBookParser parser = new AddressBookParser();
        String userInput = "invalid command";
        assertThrows(ParseException.class, () -> parser.parseCommand(userInput));
    }
}
