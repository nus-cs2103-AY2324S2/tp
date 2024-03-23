package scm.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static scm.address.testutil.TypicalPersons.ALICE;
import static scm.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import scm.address.model.Model;
import scm.address.model.ModelManager;
import scm.address.model.UserPrefs;

public class FindAndExportCommandTest {

    @TempDir
    Path testFolder;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTagAndFilename_success() throws Exception {
        Path filePath = testFolder.resolve("output.json");
        FindAndExportCommand command = new FindAndExportCommand("friends", null, null, filePath.toString());

        command.execute(model);

        assertTrue(Files.exists(filePath), "The file was not created.");
    }

    @Test
    public void execute_exportSuccessful() throws Exception {
        Model model = new ModelManager();
        model.addPerson(ALICE);

        String tag = "friends";
        String name = ALICE.getName().toString();
        String address = ALICE.getAddress().toString();
        Path tempFile = Files.createTempFile("testExport", ".txt");
        String filename = tempFile.toString();

        FindAndExportCommand command = new FindAndExportCommand(tag, name, address, filename);
        CommandResult result = command.execute(model);
        assertTrue(Files.exists(tempFile));
        Files.deleteIfExists(tempFile);
    }
}
