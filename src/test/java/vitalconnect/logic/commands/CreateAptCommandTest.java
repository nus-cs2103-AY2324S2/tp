package vitalconnect.logic.commands;

import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.logic.parser.ParserUtil;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

//import vitalconnect.model.person.identificationinformation.Name;
public class CreateAptCommandTest {

    @Test
    public void execute_icNotExist_throwsCommandException() throws ParseException {
        ModelStub modelStub = new ModelStubWithoutPerson();
        Nric patientIc = new Nric("S4848058F");
        LocalDateTime dateTimeStr = ParserUtil.parseTime("02/02/2024 1330");
        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, dateTimeStr);

        assertThrows(CommandException.class,
            MESSAGE_PERSON_NOT_FOUND, () -> createAptCommand.execute(modelStub));
    }


    /*
    @Test
    public void execute_appointmentCreatedSuccessfully() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        String patientIc = "S1234567D";
        String dateTimeStr = "02/02/2024 1330";
        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, dateTimeStr);

        CommandResult commandResult = createAptCommand.execute(modelStub);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

        String successString = String.format("Created an appointment successfully!\nName: "
                + "Amy" + "\nNRIC: %s\nTime: %s",
                patientIc, dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")));

        assertEquals(successString, commandResult.getFeedbackToUser());
        assertTrue(modelStub.appointmentsAdded.stream().anyMatch(appointment ->
                appointment.getPatientIc().equals(patientIc)
                        && appointment.getDateTime().equals(dateTime)));
    }
*/


    private class ModelStub implements Model {
        @Override
        public void setCurrentPredicate(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Person> getCurrentPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClinicFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinicFilePath(Path clinicFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinic(ReadOnlyClinic newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClinic getClinic() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesIcExist(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesPersonExist(String name) {
            throw new AssertionError("This method should not be called.");
        }
        @Test
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointments(List<Appointment> appointments) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public Person findPersonByNric(Nric nric) {
            return null;
        }

        @Override
        public void updatePersonContactInformation(Nric nric, ContactInformation contactInformation) {

        }

        /**
         * @param nric
         * @param medicalInformation
         */
        @Override
        public void updatePersonMedicalInformation(Nric nric, MedicalInformation medicalInformation) {

        }

    }


    private class ModelStubWithoutPerson extends ModelStub {
        @Override
        public boolean doesPersonExist(String name) {
            return false;
        }
        @Override
        public boolean doesIcExist(String ic) {
            return false;
        }
    }


    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();
        @Override
        public boolean doesPersonExist(String name) {
            return true;
        }

        @Override
        public boolean doesIcExist(String ic) {
            return true;
        }

        @Override
        public void addAppointment(Appointment appointment) {
            appointmentsAdded.add(appointment);
        }
    }
}



