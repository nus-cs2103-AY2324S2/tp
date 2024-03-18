package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.nio.file.Paths;

public class ImportCommandTest {
    @Test
    public void execute_import_success() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/valid.csv"));
        CommandResult commandResult = importCommand.execute(model);
        assertEquals(String.format("Imported Contacts from: %s", "src/test/data/ImportCommandTest/valid.csv"),
                commandResult.getFeedbackToUser());
    }
    @Test
    public void execute_readCsvFile_success() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/valid.csv"));
        importCommand.readCsvFile();
    }
    @Test
    public void execute_import_failure() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/invalid.csv"));
        assertThrows(DataLoadingException.class, () -> importCommand.execute(model));
    }
    @Test
    public void execute_import_invalidPath_failure() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/invalidPath.csv"));
        assertThrows(DataLoadingException.class, () -> importCommand.execute(model));
    }
    @Test
    public void execute_import_invalidFile_failure() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/invalidFile.csv"));
        assertThrows(DataLoadingException.class, () -> importCommand.execute(model));
    }
    @Test
    public void execute_import_invalidFileFormat_failure() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/invalidFileFormat.csv"));
        assertThrows(DataLoadingException.class, () -> importCommand.execute(model));
    }
    @Test
    public void execute_import_invalidFileContent_failure() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/invalidFileContent.csv"));
        }
}
