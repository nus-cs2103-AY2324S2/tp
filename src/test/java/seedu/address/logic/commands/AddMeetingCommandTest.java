package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    @Test
    public void constructor_validMeeting_success() {
        AddMeetingCommand meetingCommand = new AddMeetingCommand(
                LocalDateTime.of(2024, 1, 1, 12, 0),
                "Sell Insurance", INDEX_FIRST_PERSON);
        assert(meetingCommand != null);
    }
    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(LocalDateTime.of(2024, 1, 1, 12, 0),
                null, null));
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, "Sell Insurance", null));
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null, INDEX_FIRST_PERSON));
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null, null));
    }
    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Person client = new MeetingBuilder().build();
        Meeting validMeeting = new MeetingBuilder().build().getMeetings().get(0);
        ModelStubWithMeeting modelStub = new ModelStubWithMeeting(validMeeting, client);

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(validMeeting.getDateTime(),
                validMeeting.getDescription(), INDEX_FIRST_PERSON);

        assertThrows(CommandException.class,
                AddMeetingCommand.MESSAGE_DUPLICATE_MEETING, () -> addMeetingCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(
                LocalDateTime.of(2024, 1, 1, 12, 0), "Sell Insurance", INDEX_FIRST_PERSON);
        AddMeetingCommand addMeetingCommandCopy = new AddMeetingCommand(
                LocalDateTime.of(2024, 1, 1, 12, 0), "Sell Insurance", INDEX_FIRST_PERSON);
        AddMeetingCommand addMeetingCommandDifferent = new AddMeetingCommand(
                LocalDateTime.of(2024, 1, 1, 12, 0), "Sell Insurance", Index.fromZeroBased(1));

        // same object -> returns true
        assertEquals(addMeetingCommand, addMeetingCommand);

        // same values -> returns true
        assertEquals(addMeetingCommand, addMeetingCommandCopy);

        // different types -> returns false
        assert(!addMeetingCommand.equals(1));

        // null -> returns false
        assert(!addMeetingCommand.equals(null));

        // different meeting -> returns false
        assert(!addMeetingCommand.equals(addMeetingCommandDifferent));
    }

    @Test
    public void execute_invalidClientIndex_throwsCommandException() {
        ModelStubWithIndexedPerson modelStub = new ModelStubWithIndexedPerson();
        Index outOfBoundIndex = Index.fromOneBased(modelStub.getFilteredPersonList().size() + 1);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(
                LocalDateTime.of(2024, 1, 1, 12, 0), "Sell Insurance", outOfBoundIndex);

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> addMeetingCommand.execute(modelStub));
    }

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
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This Meeting method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This Meeting method should not be called.");
        }
        @Override
        public void deleteAllMeetingsForClient(Index clientIndex) {
            throw new AssertionError("This Meeting method should not be called.");
        }
        @Override
        public void deleteSpecificMeetingForClient(Index clientIndex, Index meetingIndex) {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A Model stub that to micmic the person list and meeting list.
     */
    private class ModelStubWithIndexedPerson extends ModelStub {
        private ObservableList<Person> persons = FXCollections.observableArrayList();
        private ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        ModelStubWithIndexedPerson(Person... persons) {
            this.persons.addAll(Arrays.asList(persons)); // Correctly add the persons to the observable list
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            return meetings.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            meetings.add(meeting);
        }

    }


    /**
     * A Model stub that contains a single predefined meeting.
     * This stub is designed for testing scenarios where the model is expected to already contain a specific meeting.
     * It overrides the {@code hasMeeting} method to return true when checking for the predefined meeting,
     * simulating the scenario where a meeting already exists in the model.
     * Use this stub in tests that require the model to behave as if it already contains a certain meeting,
     * such as when testing for duplicate meeting prevention.
     */

    private class ModelStubWithMeeting extends ModelStub {
        private ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        private ObservableList<Person> persons = FXCollections.observableArrayList();

        ModelStubWithMeeting(Meeting meeting, Person... persons) {
            requireNonNull(meeting);
            this.meetings.add(meeting);
            this.persons.addAll(Arrays.asList(persons));
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            return meetings.stream().anyMatch(meeting::isSameMeeting);
        }
        @Override
        public void addMeeting(Meeting meeting) {
            meetings.add(meeting);
        }
        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }


    }


}
