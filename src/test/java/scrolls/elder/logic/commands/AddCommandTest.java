package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.Model;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.ReadOnlyUserPrefs;
import scrolls.elder.model.person.Person;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalPersons;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        Assert.assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, ()
            -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(TypicalPersons.ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + TypicalPersons.ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private final Error unimplementedError = new AssertionError("This method should not be called.");

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw unimplementedError;
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw unimplementedError;
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw unimplementedError;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw unimplementedError;
        }

        @Override
        public Path getDatastoreFilePath() {
            throw unimplementedError;
        }

        @Override
        public void setDatastoreFilePath(Path datastoreFilePath) {
            throw unimplementedError;
        }

        @Override
        public ReadOnlyDatastore getDatastore() {
            throw unimplementedError;
        }

        @Override
        public Datastore getMutableDatastore() {
            throw unimplementedError;
        }

        @Override
        public void setDatastore(ReadOnlyDatastore d) {
            throw unimplementedError;
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
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
    }

}
