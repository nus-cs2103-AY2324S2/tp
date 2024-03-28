package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Theme;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.alias.Alias;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;

public class AddAliasCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAliasCommand(null, null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        AddAliasCommandTest.ModelStubAcceptingAliasAdded modelStub =
                new AddAliasCommandTest.ModelStubAcceptingAliasAdded();

        CommandResult commandResult = new AddAliasCommand("test", "result").execute(modelStub);

        assertEquals(String.format(AddAliasCommand.MESSAGE_SUCCESS, "test"), commandResult.getFeedbackToUser());

        Alias alias = new Alias();
        alias.addAlias("test", "result");
        assertFalse(modelStub.getAlias().equals(alias));
    }

    @Test
    public void equals() {
        AddAliasCommand addAliasTestCommand = new AddAliasCommand("test", "result");
        AddAliasCommand addAlias1TestCommand = new AddAliasCommand("test1", "result1");

        // same object -> returns true
        assertTrue(addAliasTestCommand.equals(addAliasTestCommand));

        // same values -> returns true
        AddAliasCommand addAliasTestCommandCopy = new AddAliasCommand("test", "result");
        assertTrue(addAliasTestCommand.equals(addAliasTestCommandCopy));

        // different types -> returns false
        assertFalse(addAliasTestCommand.equals(1));

        // null -> returns false
        assertFalse(addAliasTestCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliasTestCommand.equals(addAlias1TestCommand));
    }

    @Test
    public void toStringMethod() {
        AddAliasCommand addAliasCommand = new AddAliasCommand("test", "result");
        addAliasCommand.toString();
        String expected = AddAliasCommand.class.getCanonicalName()
                + "{alias=test" + ", toReplace=result}";
        assertEquals(expected, addAliasCommand.toString());
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
        public void addPerson(Person person) {
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
        public void addBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cancelBooking(Booking target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getFilteredBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTheme(Theme theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookingList(Predicate<Booking> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlias(String string, String string2) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Alias getAlias() {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingAliasAdded extends AddAliasCommandTest.ModelStub {

        private Alias alias = new Alias();

        @Override
        public void addAlias(String string, String string2) {
            alias.addAlias(string, string);
        }

        @Override
        public Alias getAlias() {
            return alias;
        }

    }
}
