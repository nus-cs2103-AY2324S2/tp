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

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.QueryDoctorAppointmentCommand;
import seedu.address.logic.commands.QueryDoctorCommand;
import seedu.address.logic.commands.QueryPatientAppointmentCommand;
import seedu.address.logic.commands.QueryPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.address.model.appointment.AppointmentContainsPatientPredicate;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PatientContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.AppointmentUtil;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addDoctor() throws Exception {
        Doctor person = new DoctorBuilder().build();
        AddDoctorCommand command = (AddDoctorCommand) parser.parseCommand(PersonUtil.getAddDoctorCommand(person));
        assertEquals(new AddDoctorCommand(person), command);
    }

    @Test
    public void parseCommand_addpatient() throws Exception {
        Patient person = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PersonUtil.getAddPatientCommand(person));
        assertEquals(new AddPatientCommand(person), command);
    }

    @Test
    public void parseCommand_addappointment() throws Exception {
        Appointment appt = new AppointmentBuilder().build();
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(
                AppointmentUtil.getAddAppointmentCommand(appt));
        assertEquals(new AddAppointmentCommand(appt), command);
    }

    @Test
    public void parseCommand_querydoctorappointment() throws Exception {
        List<String> keywords = List.of("T1234567A");
        QueryDoctorAppointmentCommand command = (QueryDoctorAppointmentCommand) parser.parseCommand(
                QueryDoctorAppointmentCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new QueryDoctorAppointmentCommand(new AppointmentContainsDoctorPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_querypatientappointment() throws Exception {
        List<String> keywords = List.of("T1234567A");
        QueryPatientAppointmentCommand command = (QueryPatientAppointmentCommand) parser.parseCommand(
                QueryPatientAppointmentCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new QueryPatientAppointmentCommand(new AppointmentContainsPatientPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_deleteappointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_PERSON), command);
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
        Person person = new PatientBuilder().build();
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
    public void parseCommand_querydoctor() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        QueryDoctorCommand command = (QueryDoctorCommand) parser.parseCommand(
                QueryDoctorCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new QueryDoctorCommand(new DoctorContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_querypatient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        QueryPatientCommand command = (QueryPatientCommand) parser.parseCommand(
                QueryPatientCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new QueryPatientCommand(new PatientContainsKeywordsPredicate(keywords)), command);
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
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
