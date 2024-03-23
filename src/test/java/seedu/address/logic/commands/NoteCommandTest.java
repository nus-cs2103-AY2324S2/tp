package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.NoteMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

public class NoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Note validNote1 = new Note("get kibble today");
    private Note validNote2 = new Note("get bones today");

    @Test
    public void execute_validNote_addSuccess() {
        Person toAddNotePerson = ALICE;
        Person expectedPerson = new Person(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote1, toAddNotePerson.getTags());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);
        String expectedMessage = String.format(NoteMessages.MESSAGE_ADD_NOTE_SUCCESS, expectedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        NoteCommand noteFirstCommand = new NoteCommand(ALICE.getName(), validNote1);
        NoteCommand noteSecondCommand = new NoteCommand(BENSON.getName(), validNote1);
        NoteCommand noteThirdCommand = new NoteCommand(ALICE.getName(), validNote2);

        // same object -> returns true
        assertTrue(noteFirstCommand.equals(noteFirstCommand));

        // different names -> returns false
        assertFalse(noteFirstCommand.equals(noteSecondCommand));

        // different notes -> returns false
        assertFalse(noteFirstCommand.equals(noteThirdCommand));
    }
}
