package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.logic.Messages;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Clinic;
import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.UserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.testutil.PersonBuilder;

public class AddContactCommandTest {
    private Model model = new ModelManager(getTypicalClinic(), new UserPrefs(), new ArrayList<>());

    @Test
    public void constructor_nullContactInformation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddContactCommand(null, null));
    }

    @Test
    public void execute_personNotFind_failure() {
        assertThrows(CommandException.class,
            Messages.MESSAGE_PERSON_NOT_FOUND, () -> new AddContactCommand(new Nric("S2519229Z"),
                new ContactInformation()).execute(model));
    }

    @Test
    public void execute_duplicateContactInformation_failure() {
        Person personInList = model.getClinic().getPersonList().get(0);
        ContactInformation contactInformation = new ContactInformation(new Email(""), new Phone(""), new Address(""));
        AddContactCommand addContactCommand = new AddContactCommand(personInList
            .getIdentificationInformation().getNric(), contactInformation);

        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_ALREADY_EXIST, () ->
            addContactCommand.execute(model));
    }

    @Test
    public void execute_addContactInformation_success() throws CommandException {
        ModelStubHasOnePersonWithEmptyCI modelStub = new ModelStubHasOnePersonWithEmptyCI();
        Person validPerson = modelStub.findPersonByNric(new Nric("S2519229Z"));
        ContactInformation ci = new ContactInformation(new Email("email@123.com"), new Phone(""), new Address(""));
        CommandResult commandResult = new AddContactCommand(validPerson
            .getIdentificationInformation().getNric(), ci).execute(modelStub);

        assertEquals(AddContactCommand.MESSAGE_SUCCESS,
            commandResult.getFeedbackToUser());
    }

    @Test
    public void toStringTest() {
        Nric nric = new Nric("S2519229Z");
        ContactInformation ci = new ContactInformation(new Email("email@123.com"), new Phone(""), new Address(""));
        AddContactCommand command = new AddContactCommand(nric, ci);
        assertEquals(command.toString(), "addContact" + nric + ci);
    }

    /**
     * A default model stub that have all of the methods failing.
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
        public boolean doesPersonExist(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesIcExist(String name) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonContactInformation(Nric nric, ContactInformation contactInformation) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @param nric
         * @param medicalInformation
         */
        @Override
        public void updatePersonMedicalInformation(Nric nric, MedicalInformation medicalInformation) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubHasOnePersonWithEmptyCI extends ModelStub {
        final Person person = new PersonBuilder().build();

        @Override
        public Person findPersonByNric(Nric nric) {
            return this.person;
        }

        @Override
        public ReadOnlyClinic getClinic() {
            return new Clinic();
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
        }

        @Override
        public void updatePersonContactInformation(Nric nric, ContactInformation contactInformation) {
        }

    }

}
