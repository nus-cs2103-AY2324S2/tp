package seedu.address.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandInfoTest {

    @Test
    public void constructor_validCommandInfo_creationSuccessful() {
        // Setup
        String expectedCommand = "add";
        String expectedDescription = "Adds a new item.";

        // Exercise
        CommandInfo commandInfo = new CommandInfo(expectedCommand, expectedDescription);

        // Verify
        assertEquals(expectedCommand, commandInfo.getCommand());
        assertEquals(expectedDescription, commandInfo.getDescription());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        // Setup
        CommandInfo commandInfo1 = new CommandInfo("edit", "Edits an item.");
        CommandInfo commandInfo2 = new CommandInfo("edit", "Edits an item.");

        // Verify
        assertEquals(commandInfo1, commandInfo2);
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        // Setup
        CommandInfo commandInfo1 = new CommandInfo("delete", "Deletes an item.");
        CommandInfo commandInfo2 = new CommandInfo("add", "Adds a new item.");

        // Verify
        assertNotEquals(commandInfo1, commandInfo2);
    }

    @Test
    public void equals_differentDescriptions_returnsFalse() {
        // Setup
        CommandInfo commandInfo1 = new CommandInfo("add", "Adds an item.");
        CommandInfo commandInfo2 = new CommandInfo("add", "Adds a new item.");

        // Verify
        assertNotEquals(commandInfo1, commandInfo2);
    }

    @Test
    public void equals_differentObjects_returnsFalse() {
        // Setup
        CommandInfo commandInfo = new CommandInfo("list", "Lists all items.");

        // Verify
        assertNotEquals(commandInfo, new String("Different Type"));
    }
}
