package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class UnstarCommandTest {

    @Test
    public void execute_contactNotFound_throwsCommandException() {
        UnstarCommand unstarCommand = new UnstarCommand("Nonexistent Contact");
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandFailure(unstarCommand, model, "Error! Contact not found: Nonexistent Contact");
    }

    @Test
    public void execute_unstarAlreadyUnstarredContact_throwsCommandException() {
        String contactName = "Alex Tan";
        Person personToUnstar = new PersonBuilder().withName(contactName).build();
        Model model = new ModelManager();
        model.addPerson(personToUnstar);
        UnstarCommand unstarCommand = new UnstarCommand(contactName);

        model.getFilteredPersonList().get(0);

        assertThrows(CommandException.class, () -> unstarCommand.execute(model));
    }

    @Test
    public void execute_validContactName_unstarsContact() throws Exception {
        // Arrange
        String contactName = "Alex Tan";
        Person personToUnstar = new PersonBuilder().withName(contactName).build();
        Model model = new ModelManager();
        model.addPerson(personToUnstar);
        StarCommand starCommand = new StarCommand(contactName);
        UnstarCommand unstarCommand = new UnstarCommand(contactName);

        // Act
        starCommand.execute(model);
        CommandResult unstarCommandResult = unstarCommand.execute(model);

        // Assert
        Person unstarredPerson = model.getFilteredPersonList().get(0);
        assertEquals("Nice! You have unstarred this contact:\nAlex Tan", unstarCommandResult.getFeedbackToUser());
        assertEquals(contactName, unstarredPerson.getName().fullName);
        assertEquals(false, unstarredPerson.isStarred());
    }

    @Test
    public void equals() {
        UnstarCommand unstarCommand1 = new UnstarCommand("Alex");
        UnstarCommand unstarCommand2 = new UnstarCommand("Alex");

        // same object -> returns true
        assertEquals(unstarCommand1, unstarCommand1);

        // same values -> returns true
        assertEquals(unstarCommand1, unstarCommand2);

        // different types -> returns false
        assertNotEquals(unstarCommand1, 1);

        // null -> returns false
        assertNotEquals(unstarCommand1, null);

        // different contactName -> returns false
        UnstarCommand differentUnstarCommand = new UnstarCommand("John");
        assertNotEquals(unstarCommand1, differentUnstarCommand);
    }
}
