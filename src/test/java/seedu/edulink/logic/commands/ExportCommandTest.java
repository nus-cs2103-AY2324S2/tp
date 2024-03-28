package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.edulink.commons.util.CsvUtil;
import seedu.edulink.model.AddressBook;
import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.testutil.TypicalPersons;

public class ExportCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_validFileName_success() throws Exception {
        String testFileName = "test";
        ExportCommand exportCommand = new ExportCommand(testFileName);
        String expectedMessage = ExportCommand.MESSAGE_EXPORT_SUCCESS + testFileName + ".csv";
        model.addPerson(TypicalPersons.ALICE);
        model.addPerson(TypicalPersons.BOB);
        model.addPerson(TypicalPersons.AMY);
        CommandResult result = exportCommand.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());

        Path exportPath = Paths.get(CsvUtil.FOLDER_PATH + testFileName + CsvUtil.FILE_FORMAT);
        List<String> lines = Files.readAllLines(exportPath);
        assertEquals(4, lines.size());
        Files.delete(exportPath);
    }

    @Test
    public void equals() {
        ExportCommand exportCommand1 = new ExportCommand("test1");
        ExportCommand exportCommand2 = new ExportCommand("test2");
        ExportCommand exportCommand3 = new ExportCommand("test1");
        ExitCommand exitCommand = new ExitCommand();

        // same object -> returns true
        assertEquals(exportCommand1, exportCommand1);

        // different objects, same filename -> returns true
        assertEquals(exportCommand1, exportCommand3);

        // different objects, different filename -> returns false
        assertFalse(exportCommand1.equals(exportCommand2));

        assertNotEquals(exportCommand1, exitCommand);
    }

    @Test
    public void testToString() {
        ExportCommand exportCommand = new ExportCommand("test");
        String expectedString = ExportCommand.class.getCanonicalName() + "{filename=test}";
        assertEquals(expectedString, exportCommand.toString());
    }

}
