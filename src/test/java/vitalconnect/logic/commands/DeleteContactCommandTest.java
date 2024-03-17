package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.nio.file.Path;
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
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.testutil.PersonBuilder;


public class DeleteContactCommandTest {
    private Model model = new ModelManager(getTypicalClinic(), new UserPrefs());

    @Test
    public void constructor_nullContactInformation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteContactCommand(null));
    }

    @Test
    public void execute_personNotFind_failure() {
        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND, () ->
          new DeleteContactCommand(new Nric("S2519229Z")).execute(model));
    }

    @Test
    public void execute_deleteContactInformation_success() throws CommandException {
        ModelStubHasOnePersonWithNoneEmptyCI modelStub = new ModelStubHasOnePersonWithNoneEmptyCI();
        Person validPerson = modelStub.findPersonByNric(new Nric("S2519229Z"));
        CommandResult commandResult = new DeleteContactCommand(validPerson.getIdentificationInformation().getNric())
            .execute(modelStub);

        assertEquals(DeleteContactCommand.MESSAGE_SUCCESS,
            commandResult.getFeedbackToUser());
    }

    @Test
    public void equalsTest() {
        Nric nric = new Nric("S2519229Z");
        DeleteContactCommand command = new DeleteContactCommand(nric);
        // same object, equal
        assertTrue(command.equals(command));

        // same nric, same command
        assertTrue(command.equals(new DeleteContactCommand(new Nric("S2519229Z"))));

        // different type, not same command
        assertFalse(command.equals("abc"));

        // null, not same command
        assertFalse(command.equals(null));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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

        @Test
        public void addAppointment(Appointment appointment) {
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

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubHasOnePersonWithNoneEmptyCI extends ModelStub {
        final Person person = new PersonBuilder().withEmail("abc@email.com").build();

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
            this.person.setContactInformation(contactInformation);
        }
    }
}
