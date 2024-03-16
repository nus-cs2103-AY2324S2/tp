package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindAndExportCommandTest {

    @TempDir
    java.nio.file.Path testFolder;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_exportFailure_noSuchDirectory() {
        String testFileName = "does/not/exist/testExport.csv";

        FindAndExportCommand command = new FindAndExportCommand("friend", null, null, testFileName);

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_exportFailure_invalidTag() {
        String testFileName = "testExport.csv";
        Path filePath = testFolder.resolve(testFileName);

        FindAndExportCommand command = new FindAndExportCommand("nonExistentTag", null, null, filePath.toString());
        assertThrows(CommandException.class, () -> command.execute(model));
        assertFalse(Files.exists(filePath));
    }
}
