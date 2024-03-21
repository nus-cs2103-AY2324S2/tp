package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NetConnect;
import seedu.address.model.ReadOnlyNetConnect;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.SupplierBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        // Assuming PersonBuilder can handle role-specific fields
        Person validClient = new ClientBuilder().build();
        Person validEmployee = new EmployeeBuilder().build();
        Person validSupplier = new SupplierBuilder().build();

        CommandResult commandResultClient = new AddCommand(validClient).execute(modelStub);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validClient)),
                commandResultClient.getFeedbackToUser());
        assertEquals(List.of(validClient), modelStub.personsAdded);

        modelStub.personsAdded.clear();

        CommandResult commandResultEmployee = new AddCommand(validEmployee).execute(modelStub);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validEmployee)),
                commandResultEmployee.getFeedbackToUser());
        assertEquals(List.of(validEmployee), modelStub.personsAdded);

        modelStub.personsAdded.clear();

        CommandResult commandResultSupplier = new AddCommand(validSupplier).execute(modelStub);
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validSupplier)),
                commandResultSupplier.getFeedbackToUser());
        assertEquals(List.of(validSupplier), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validClient = new ClientBuilder().build();
        AddCommand addCommand = new AddCommand(validClient);
        ModelStub modelStub = new ModelStubWithPerson(validClient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new ClientBuilder().withName("Alice").build();
        Person bob = new ClientBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommanddCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommanddCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertTrue(expected.equals(addCommand.toString()));
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
        public Path getNetConnectFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNetConnectFilePath(Path netConnectFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNetConnect(ReadOnlyNetConnect newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyNetConnect getNetConnect() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasId(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonById(Id id) {
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
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyNetConnect getNetConnect() {
            return new NetConnect();
        }
    }

}
