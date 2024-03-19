package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox.CommandExecutor;

public class CommandBoxTest {
    private CommandBox commandBox;

    @BeforeAll
    public static void setupJavaFX() {
        // Initialize JavaFX toolkit
        JFXPanel jfxPanel = new JFXPanel();
    }

    @BeforeEach
    public void setUp() {
        // Initialize CommandBox with a dummy CommandExecutor
        CommandExecutor dummyCommandExecutor = new CommandExecutor() {
            @Override
            public CommandResult execute(String commandText) throws CommandException, ParseException {
                return null; // Dummy implementation
            }
        };
        commandBox = new CommandBox(dummyCommandExecutor);
    }

    @Test
    public void createNewCommandBox_successfullyCreated() {
        assertNotNull(commandBox);
    }


}
