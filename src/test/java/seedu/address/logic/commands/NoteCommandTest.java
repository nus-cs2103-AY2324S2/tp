package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.NoteMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

public class NoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Note validNote1 = new Note("get kibble today");
    private Note validNote2 = new Note("get bones today");

    private Staff georgiaStaff = new StaffBuilder().withName("Georgia Staff")
            .withAddress("123, Jurong West Ave 45, #08-131").withEmail("georgia@example.com")
            .withPhone("94355453")
            .withTags("friends")
            .withSalary("$50/hr")
            .withEmployment("part-time").build();

    private Supplier georgiaSupplier = new SupplierBuilder().withName("Georgia Supplier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("georgia@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withProduct("pooch medicine")
            .withPrice("$50/injection").build();

    private Maintainer georgiaMaintainer = new MaintainerBuilder().withName("Georgia Maintainer")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("georgia@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withSkill("train dog")
            .withCommission("$50/hr").build();

    @Test
    public void execute_validNoteOther_addSuccess() {
        Person toAddNotePerson = ALICE;
        Person expectedPerson = new Person(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                validNote1, toAddNotePerson.getTags());
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
        model.addPerson(georgiaStaff);

        Staff toAddNotePerson = georgiaStaff;
        Staff expectedPerson = new Staff(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getTags(), toAddNotePerson.getSalary(), toAddNotePerson.getEmployment());
        expectedPerson.setNoteContent(validNote1.toString());
        expectedModel.addPerson(expectedPerson);


        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);
        String expectedMessage = String.format(NoteMessages.MESSAGE_ADD_NOTE_SUCCESS, expectedPerson);

        noteCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validNoteSupplier_addSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(georgiaSupplier);

        Supplier toAddNotePerson = georgiaSupplier;
        Supplier expectedPerson = new Supplier(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getTags(), toAddNotePerson.getProduct(), toAddNotePerson.getPrice());
        expectedPerson.setNoteContent(validNote1.toString());
        expectedModel.addPerson(expectedPerson);


        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);
        String expectedMessage = String.format(NoteMessages.MESSAGE_ADD_NOTE_SUCCESS, expectedPerson);

        noteCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validNoteMaintainer_addSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(georgiaMaintainer);

        Maintainer toAddNotePerson = georgiaMaintainer;
        Maintainer expectedPerson = new Maintainer(toAddNotePerson.getName(), toAddNotePerson.getPhone(),
                toAddNotePerson.getEmail(), toAddNotePerson.getAddress(),
                toAddNotePerson.getTags(), toAddNotePerson.getSkill(), toAddNotePerson.getCommission());
        expectedPerson.setNoteContent(validNote1.toString());
        expectedModel.addPerson(expectedPerson);


        NoteCommand noteCommand = new NoteCommand(toAddNotePerson.getName(), validNote1);
        String expectedMessage = String.format(NoteMessages.MESSAGE_ADD_NOTE_SUCCESS, expectedPerson);

        noteCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
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
