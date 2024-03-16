package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindAndExportCommandTest {

    @TempDir
    java.nio.file.Path testFolder;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_exportSuccess_writesFile() throws Exception {
        String testFileName = "testExport.csv";
        java.nio.file.Path filePath = testFolder.resolve(testFileName);

        FindAndExportCommand command = new FindAndExportCommand("friend", null, null, filePath.toString());
        command.execute(model);

        assertTrue(Files.exists(filePath));
    }
}
