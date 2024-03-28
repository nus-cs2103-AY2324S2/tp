package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * A stub class for Model.
 */
public class ModelStub implements Model {
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
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
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
    public void addPerson(Person person) {
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
    public void setEventBook(ReadOnlyEventBook eventBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEvent(Event target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isAnEventSelected() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public boolean isSameSelectedEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void selectEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deselectEvent() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isPersonInSelectedEvent(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPersonToSelectedEvent(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePersonFromSelectedEvent(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableValue<Event> getSelectedEvent() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonListOfSelectedEvent() {
        throw new AssertionError("This method should not be called.");
    }
}
