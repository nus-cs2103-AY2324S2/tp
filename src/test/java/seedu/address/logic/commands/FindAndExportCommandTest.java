package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;
//import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindAndExportCommandTest {

    @TempDir
    Path testFolder;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTagAndFilename_success() throws Exception {
        Path filePath = testFolder.resolve("output.csv");
        FindAndExportCommand command = new FindAndExportCommand("friends", null, null, filePath.toString());

        command.execute(model);

        assertTrue(Files.exists(filePath), "The file was not created.");
    }
}
