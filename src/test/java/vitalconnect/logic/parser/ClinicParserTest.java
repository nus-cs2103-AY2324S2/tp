package vitalconnect.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.AddContactCommand;
import vitalconnect.logic.commands.ClearCommand;
import vitalconnect.logic.commands.CreateAptCommand;
import vitalconnect.logic.commands.DeleteAptCommand;
import vitalconnect.logic.commands.DeleteCommand;
import vitalconnect.logic.commands.DeleteContactCommand;
import vitalconnect.logic.commands.EditCommand;
import vitalconnect.logic.commands.EditCommand.EditPersonDescriptor;
import vitalconnect.logic.commands.ExitCommand;
import vitalconnect.logic.commands.FindCommand;
import vitalconnect.logic.commands.HelpCommand;
import vitalconnect.logic.commands.ListAptCommand;
import vitalconnect.logic.commands.ListCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.NameContainsKeywordsPredicate;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.testutil.EditPersonDescriptorBuilder;
import vitalconnect.testutil.PersonBuilder;
import vitalconnect.testutil.PersonUtil;


public class ClinicParserTest {

    private final ClinicParser parser = new ClinicParser();

    //    @Test
    //    public void parseCommand_add() throws Exception {
    //        Person person = new PersonBuilder().build();
    //        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
    //        assertEquals(new AddCommand(person), command);
    //    }

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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_createApt() throws Exception {
        String getPatientIc = "S1234567D";
        String dateTimeStr = "02/02/2024 1330";
        String inputCommand = CreateAptCommand.COMMAND_WORD + " ic/" + getPatientIc + " time/" + dateTimeStr;
        CreateAptCommand expectedCommand =
            new CreateAptCommand(new Nric(getPatientIc), ParserUtil.parseTime(dateTimeStr));
        CreateAptCommand parsedCommand = (CreateAptCommand) parser.parseCommand(inputCommand);

        assertEquals(expectedCommand.getPatientIc(), parsedCommand.getPatientIc());
        assertEquals(expectedCommand.getDateTimeStr(), parsedCommand.getDateTimeStr());
    }

    @Test
    public void parseCommand_listApt() throws Exception {
        assertTrue(parser.parseCommand(ListAptCommand.COMMAND_WORD) instanceof ListAptCommand);
        assertTrue(parser.parseCommand(ListAptCommand.COMMAND_WORD + " 3") instanceof ListAptCommand);
    }

    @Test
    public void parseCommand_deleteApt() throws Exception {
        String input = DeleteAptCommand.COMMAND_WORD + " 1";
        DeleteAptCommand command = (DeleteAptCommand) parser.parseCommand(input);
        Index resIndex = command.getIndex();
        assertEquals(1, resIndex.getOneBased());
    }

    @Test
    public void parseCommand_addContact() throws ParseException {
        assertTrue(parser.parseCommand(AddContactCommand.COMMAND_WORD
            + " ic/S7898305A p/12345678") instanceof AddContactCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws ParseException {
        assertTrue(parser.parseCommand(DeleteContactCommand.COMMAND_WORD
            + " ic/S7898305A") instanceof DeleteContactCommand);
    }
}
