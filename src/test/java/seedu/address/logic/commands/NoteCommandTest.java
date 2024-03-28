package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonWithName;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGIAMAINTAINER;
import static seedu.address.testutil.TypicalPersons.GEORGIASTAFF;
import static seedu.address.testutil.TypicalPersons.GEORGIASUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

public class NoteCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Note validNote1 = new Note("get kibble today");
    private final Note validNote2 = new Note("get bones today");

    @Test
    public void execute_validNoteOther_addSuccess() {
        Person toAddNotePerson = ALICE;
        Person expectedPerson = new Person(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote1, toAddNotePerson.getTags(), toAddNotePerson.getRating());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);
        String expectedMessage = String.format(NoteMessages.MESSAGE_ADD_NOTE_SUCCESS,
                NoteMessages.format(expectedPerson));

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNoteStaff_addSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(GEORGIASTAFF);

        Staff toAddNotePerson = GEORGIASTAFF;
        Staff expectedPerson = new Staff(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(), toAddNotePerson.getNote(),
                toAddNotePerson.getTags(), toAddNotePerson.getSalary(), toAddNotePerson.getEmployment(),
                toAddNotePerson.getRating());
        expectedPerson.setNoteContent(validNote1.toString());
        expectedModel.addPerson(expectedPerson);

        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);

        try {
            noteCommand.execute(model);
        } catch (CommandException ce) {
            fail();
        }

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validNoteSupplier_addSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(GEORGIASUPPLIER);

        Supplier toAddNotePerson = GEORGIASUPPLIER;
        Supplier expectedPerson = new Supplier(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(), toAddNotePerson.getNote(),
                toAddNotePerson.getTags(), toAddNotePerson.getProduct(), toAddNotePerson.getPrice(),
                toAddNotePerson.getRating());
        expectedPerson.setNoteContent(validNote1.toString());
        expectedModel.addPerson(expectedPerson);

        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);

        try {
            noteCommand.execute(model);
        } catch (CommandException ce) {
            fail();
        }

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validNoteMaintainer_addSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(GEORGIAMAINTAINER);

        Maintainer toAddNotePerson = GEORGIAMAINTAINER;
        Maintainer expectedPerson = new Maintainer(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(), toAddNotePerson.getNote(),
                toAddNotePerson.getTags(), toAddNotePerson.getSkill(), toAddNotePerson.getCommission(),
                toAddNotePerson.getRating());
        expectedPerson.setNoteContent(validNote1.toString());
        expectedModel.addPerson(expectedPerson);

        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);

        try {
            noteCommand.execute(model);
        } catch (CommandException ce) {
            fail();
        }

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        showPersonWithName(model, ALICE.getName());

        Name invalidName = new Name("patty");

        // ensures that the invalid name is not equal to "Alice Pauline"
        assertNotEquals(invalidName, ALICE.getName());

        NoteCommand noteCommand = new NoteCommand(invalidName, validNote1);

        assertCommandFailure(noteCommand, model, NoteMessages.MESSAGE_NOTE_NAME_NOT_FOUND);
    }

    @Test
    public void equals() {
        NoteCommand noteFirstCommand = new NoteCommand(ALICE.getName(), validNote1);
        NoteCommand noteSecondCommand = new NoteCommand(BENSON.getName(), validNote1);
        NoteCommand noteThirdCommand = new NoteCommand(ALICE.getName(), validNote2);

        // same object -> returns true
        assertEquals(noteFirstCommand, noteFirstCommand);

        // different names -> returns false
        assertNotEquals(noteFirstCommand, noteSecondCommand);

        // different notes -> returns false
        assertNotEquals(noteFirstCommand, noteThirdCommand);
    }
}
