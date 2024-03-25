package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CHANGED_DATA_SOURCE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ChangeDataSourceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Path pathOne = Paths.get("data/addressbook.json");
        Path pathTwo = Paths.get("data/addressbook.json");
        Path pathThree = Paths.get("data/adk.json");

        ChangeDataSourceCommand commandOne = new ChangeDataSourceCommand(pathOne);
        ChangeDataSourceCommand commandTwo = new ChangeDataSourceCommand(pathTwo);
        ChangeDataSourceCommand commandThree = new ChangeDataSourceCommand(pathThree);

        assertTrue(commandOne.equals(commandOne));

        assertTrue(commandOne.equals(commandTwo));

        // different types -> returns false
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different -> returns false
        assertFalse(commandOne.equals(commandThree));
    }

    @Test
    void execute() throws CommandException {
        Path pathOne = Paths.get("data/addressbook.json");
        ChangeDataSourceCommand command = new ChangeDataSourceCommand(pathOne);
        String expectedMessage = MESSAGE_CHANGED_DATA_SOURCE;
        CommandResult result = command.execute(model);
        assertFalse(expectedMessage.equals(result.toString()));
    }

    @Test
    void getNewPath() {
        Path pathOne = Paths.get("data/addressbook.json");
        ChangeDataSourceCommand commandOne = new ChangeDataSourceCommand(pathOne);

        assertTrue(commandOne.getNewPath().equals(pathOne));
    }
}
