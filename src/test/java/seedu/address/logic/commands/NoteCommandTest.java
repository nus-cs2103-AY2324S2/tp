package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class NoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addNoteUnfilteredList_success() {

        Person targetPerson = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(targetPerson).withNote("Updated Note").build();

        NoteCommand executeCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note("Updated Note"), false);

        String expectedResult = String.format(executeCommand.MESSAGE_ADD_NOTE_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(executeCommand, model, expectedResult, expectedModel);
    }

    @Test
    void execute_addNoteUnfilteredListWithDate_success() {

        Person targetPerson = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(targetPerson).withNote("Updated Note").build();

        NoteCommand executeCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note("Updated Note"), true);

        String expectedResult = String.format(executeCommand.MESSAGE_ADD_NOTE_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(executeCommand, model, expectedResult, expectedModel);
    }
}
