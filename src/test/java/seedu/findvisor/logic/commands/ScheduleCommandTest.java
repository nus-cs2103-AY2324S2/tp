package seedu.findvisor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.commands.CommandTestUtil.createValidMeeting;
import static seedu.findvisor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.findvisor.logic.commands.CommandTestUtil.createOldMeeting;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.findvisor.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.model.AddressBook;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.ModelManager;
import seedu.findvisor.model.UserPrefs;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.Meeting;
import seedu.findvisor.testutil.PersonBuilder;

public class ScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_okayMeeting_scheduleSuccessful() throws Exception {
        Meeting meeting = createValidMeeting();
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, meeting);
        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personBuilder = new PersonBuilder(targetPerson).withMeeting(Optional.of(meeting));
        Person editedPerson = personBuilder.build();

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SCHEDULE_SUCCESS, targetPerson.getName(),
                meeting.getStartString(), meeting.getEndString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleMeeting_throwsCommandException() {
        Meeting meeting = createValidMeeting();
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, meeting);
        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personBuilder = new PersonBuilder(targetPerson).withMeeting(Optional.of(meeting));
        Person editedPerson = personBuilder.build();
        model.setPerson(targetPerson, editedPerson);

        assertCommandFailure(scheduleCommand, model, ScheduleCommand.MESSAGE_CANNOT_SCHEDULE_MULTIPLE_MEETINGS);
    }

    @Test
    public void execute_meetingTimeInPast_throwsCommandException() {
        Meeting meeting = createOldMeeting();
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, meeting);

        assertCommandFailure(scheduleCommand, model, ScheduleCommand.MESSAGE_CANNOT_SCHEDULE_MEETING_IN_THE_PAST);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Meeting meeting = createValidMeeting();
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, meeting);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Meeting meeting = createValidMeeting();
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, meeting);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final Meeting meeting = createValidMeeting();
        final ScheduleCommand standardCommand = new ScheduleCommand(INDEX_FIRST_PERSON, meeting);

        // same values -> returns true
        ScheduleCommand commandWithSameValues = new ScheduleCommand(INDEX_FIRST_PERSON, meeting);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(INDEX_SECOND_PERSON, meeting)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(INDEX_FIRST_PERSON, createOldMeeting())));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Meeting meeting = createValidMeeting();
        ScheduleCommand editCommand = new ScheduleCommand(index, meeting);
        String expected = ScheduleCommand.class.getCanonicalName() + "{toSchedule=" + index + ", meeting="
                + meeting + "}";
        assertEquals(expected, editCommand.toString());
    }

}
