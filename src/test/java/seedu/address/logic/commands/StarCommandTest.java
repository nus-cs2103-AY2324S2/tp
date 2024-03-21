package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class StarCommandTest {

    @Test
    public void execute_validContactName_starsContact() throws Exception {
        // Arrange
        String contactName = "Alex Tan";
        Person personToStar = new PersonBuilder().withName(contactName).build();
        Model model = new ModelManager();
        model.addPerson(personToStar);
        StarCommand starCommand = new StarCommand(contactName);

        // Act
        CommandResult commandResult = starCommand.execute(model);

        // Assert
        Person starredPerson = model.getFilteredPersonList().get(0);
        assertEquals("Nice! I have starred this contact:\nAlex Tan ★", commandResult.getFeedbackToUser());
        assertEquals(contactName, starredPerson.getName().fullName);
        assertEquals(true, starredPerson.isStarred());
    }

    @Test
    public void execute_contactNotFound_throwsCommandException() {
        // Arrange
        String contactName = "Alex Tan";
        Model model = new ModelManager();
        StarCommand starCommand = new StarCommand(contactName);

        // Act and Assert
        assertThrows(CommandException.class, () -> starCommand.execute(model));
    }

    @Test
    public void equals() {
        StarCommand starCommand1 = new StarCommand("Alex");
        StarCommand starCommand2 = new StarCommand("Alex");

        // same object -> returns true
        assertEquals(starCommand1, starCommand1);

        // same values -> returns true
        assertEquals(starCommand1, starCommand2);

        // different types -> returns false
        assertNotEquals(starCommand1, 1);

        // null -> returns false
        assertNotEquals(starCommand1, null);

        // different contactName -> returns false
        StarCommand differentStarCommand = new StarCommand("John");
        assertNotEquals(starCommand1, differentStarCommand);
    }
}
