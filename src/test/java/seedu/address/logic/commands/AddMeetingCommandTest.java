package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddMeetingCommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AddMeetingCommandTest {

    private static final String MEETING_STUB = "Interview: 23 March 2024 (1400 - 1600)";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMeetingUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMeeting(MEETING_STUB).build();

        String[] details = AddMeetingCommandParser.parseDetails(editedPerson.getMeeting().toString());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(editedPerson.getName().fullName,
                new Meeting(details[0], details[1], details[2], details[3]));

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS,
                editedPerson.getName().fullName, editedPerson.getMeeting().toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMeetingUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMeeting("").build();

        String[] details = AddMeetingCommandParser.parseDetails(editedPerson.getMeeting().toString());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(editedPerson.getName().fullName,
                new Meeting(details[0], details[1], details[2], details[3]));

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS,
                editedPerson.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withMeeting(MEETING_STUB).build();
        String[] details = AddMeetingCommandParser.parseDetails(editedPerson.getMeeting().toString());

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(editedPerson.getName().fullName,
                new Meeting(details[0], details[1], details[2], details[3]));

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS,
                editedPerson.getName().fullName, editedPerson.getMeeting().toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonName_throwsCommandException() {
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand("",
                new Meeting("Interview", "20-03-2024", "1500", "1600"));
        assertCommandFailure(addMeetingCommand, model, AddMeetingCommand.MESSAGE_EMPTY_NAME);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand("ABC",
                new Meeting("Networking Event", "11-04-2024", "1400", "1600"));
        assertCommandFailure(addMeetingCommand, model, String.format(AddMeetingCommand.MESSAGE_PERSON_NOT_FOUND,
                "ABC"));
    }

    @Test
    public void equals() {
        final AddMeetingCommand standardCommand = new AddMeetingCommand("Amy Reale",
                new Meeting("Interview", "20-03-2024", "1500", "1600"));
        // same values -> returns true
        AddMeetingCommand commandWithSameValues = new AddMeetingCommand("Amy Reale",
                new Meeting("Interview", "20-03-2024", "1500", "1600"));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different company tag -> returns false
        assertFalse(standardCommand.equals(new AddMeetingCommand("Bob Tan",
                new Meeting("Networking Event", "11-04-2024", "1400", "1600"))));
    }
}
