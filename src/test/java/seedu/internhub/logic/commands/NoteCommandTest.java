package seedu.internhub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.internhub.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.logic.commands.exceptions.CommandException;
import seedu.internhub.model.Model;
import seedu.internhub.model.ModelManager;
import seedu.internhub.model.UserPrefs;
import seedu.internhub.model.person.Person;

class NoteCommandTest {

    @Test
    void execute_validIndex_success() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        List<Person> personList = model.getFilteredPersonList();
        Person personToView = personList.get(0);
        NoteCommand noteCommand = new NoteCommand(Index.fromZeroBased(0));

        CommandResult commandResult = noteCommand.execute(model);
        assertEquals("edit 1 n/" + personToView.getNote(), commandResult.getFeedbackToUser());
        assertEquals(personToView, commandResult.getViewPerson());
    }

    @Test
    void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        List<Person> personList = model.getFilteredPersonList();
        Index invalidIndex = Index.fromZeroBased(personList.size() + 10);
        NoteCommand noteCommand = new NoteCommand(invalidIndex);
        assertThrows(CommandException.class, () -> noteCommand.execute(model));
    }

    @Test
    void equals_sameObject_true() {
        NoteCommand noteCommand = new NoteCommand(Index.fromZeroBased(0));

        assertEquals(noteCommand, noteCommand);
    }

    @Test
    void equals_differentObject_false() {
        NoteCommand noteCommand1 = new NoteCommand(Index.fromZeroBased(0));
        NoteCommand noteCommand2 = new NoteCommand(Index.fromZeroBased(1));

        assertNotEquals(noteCommand1, noteCommand2);
    }

    @Test
    void equals_differentClass_false() {
        NoteCommand noteCommand = new NoteCommand(Index.fromZeroBased(0));
        Object otherObject = new Object();

        assertNotEquals(noteCommand, otherObject);
    }

    @Test
    public void toString_validIndex_success() {
        // Create an Index object
        Index index = Index.fromZeroBased(3);

        // Create a NoteCommand object using the Index
        NoteCommand noteCommand = new NoteCommand(index);

        // Create the expected string representation using variables
        String expectedToString = String.format("seedu.internhub.logic.commands.NoteCommand{targetIndex=%s}", index);

        // Perform the assertion
        assertEquals(expectedToString, noteCommand.toString());
    }
}
