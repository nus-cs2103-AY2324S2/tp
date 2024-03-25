package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.StartupBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_startupAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStartupAdded modelStub = new ModelStubAcceptingStartupAdded();
        Startup validStartup = new StartupBuilder().build();

        CommandResult commandResult = new AddCommand(validStartup).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validStartup)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStartup), modelStub.startupsAdded);
    }

    @Test
    public void execute_duplicateStartup_throwsCommandException() {
        Startup validStartup = new StartupBuilder().build();
        AddCommand addCommand = new AddCommand(validStartup);
        ModelStub modelStub = new ModelStubWithStartup(validStartup);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_STARTUP, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Startup alice = new StartupBuilder().withName("Alice").build();
        Startup bob = new StartupBuilder().withName("Bob").build();
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

        // different startup -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStartup(Startup startup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStartup(Startup startup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStartup(Startup target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStartup(Startup target, Startup editedStartup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Startup> getFilteredStartupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStartupList(Predicate<Startup> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single startup.
     */
    private class ModelStubWithStartup extends ModelStub {
        private final Startup startup;

        ModelStubWithStartup(Startup startup) {
            requireNonNull(startup);
            this.startup = startup;
        }

        @Override
        public boolean hasStartup(Startup startup) {
            requireNonNull(startup);
            return this.startup.isSameStartup(startup);
        }
    }

    /**
     * A Model stub that always accept the startup being added.
     */
    private class ModelStubAcceptingStartupAdded extends ModelStub {
        final ArrayList<Startup> startupsAdded = new ArrayList<>();

        @Override
        public boolean hasStartup(Startup startup) {
            requireNonNull(startup);
            return startupsAdded.stream().anyMatch(startup::isSameStartup);
        }

        @Override
        public void addStartup(Startup startup) {
            requireNonNull(startup);
            startupsAdded.add(startup);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
