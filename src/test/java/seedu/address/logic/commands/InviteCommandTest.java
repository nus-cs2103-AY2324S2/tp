package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class InviteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InviteCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new InviteCommand(Index.fromZeroBased(0)).execute(null));
    }

    @Test
    public void eventIndex_outOfRange_throwsCommandException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());
        assertThrows(CommandException.class, () ->
                new InviteCommand(Index.fromZeroBased(100)).execute(model));
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());
        InviteCommand inviteCommand = new InviteCommand(Index.fromZeroBased(0));

        // Select an event first
        Event event = model.getEventBook().getEventList().get(0);
        model.selectEvent(event);

        // Add person
        event.addPerson(getTypicalAddressBook().getPersonList().get(0));
        model.selectEvent(event);

        assertThrows(CommandException.class, InviteCommand.MESSAGE_DUPLICATE_PERSON, ()
                -> inviteCommand.execute(model));
    }

    @Test
    public void execute_eventNotSelected_throwsCommandException() throws CommandException {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());
        InviteCommand inviteCommand = new InviteCommand(Index.fromZeroBased(0));

        assertThrows(CommandException.class, Messages.MESSAGE_SELECT_EVENT, () -> inviteCommand.execute(model));
    }

    @Test
    public void execute_selectEventAndInvitePerson_successful() throws CommandException {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setEventBook(getTypicalEventBook());
        InviteCommand inviteCommand = new InviteCommand(Index.fromZeroBased(0));

        // Select an event first
        model.selectEvent(model.getEventBook().getEventList().get(0));

        // Invite person
        inviteCommand.execute(model);

        // Ensure the person is added to the selected event
        Person personToInvite = TypicalPersons.getTypicalPersons().get(0);
        assertTrue(model.isPersonInSelectedEvent(personToInvite));
    }

    @Test
    public void equals_sameIndex_returnsTrue() {
        InviteCommand one = new InviteCommand(Index.fromZeroBased(5));
        InviteCommand two = new InviteCommand(Index.fromZeroBased(5));
        assertEquals(one, two);
    }

    @Test
    public void equals_handles_null() {
        InviteCommand one = new InviteCommand(Index.fromZeroBased(5));
        assertNotEquals(null, one);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        InviteCommand one = new InviteCommand(Index.fromZeroBased(5));
        assertEquals(one, one);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        InviteCommand inviteCommand = new InviteCommand(targetIndex);
        String expected = InviteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, inviteCommand.toString());
    }
}
