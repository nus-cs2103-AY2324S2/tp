package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ImportCommandTest {
    @Test
    public void execute_import_success() throws CommandException, ParseException, DataLoadingException {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/valid.csv"));
        CommandResult commandResult = importCommand.execute(model);
        assertEquals(String.format("Imported Contacts from: %s", "src\\test\\data\\ImportCommandTest\\valid.csv"),
                commandResult.getFeedbackToUser());
    }
    @Test
    public void execute_convertToAddCommandInput_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        Map<String, String> personData = Map.of(
                "studio", "S1",
                "matric", "A1111111A",
                "address", "123, Jurong West Ave 6, #08-111",
                "reflection", "R1",
                "phone", "94351253",
                "name", "Alice Pauline",
                "email", "alice@example.com",
                "tags", "friends"
                );
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/valid.csv"));
        String actual = importCommand.convertToAddCommandInput(personData);
        String expected =
                " n/Alice Pauline p/94351253 "
                        + "e/alice@example.com a/123, Jurong West Ave 6, #08-111 m/A1111111A r/R1 s/S1 t/friends  ";
        assertEquals(expected, actual);
    }
    @Test
    public void execute_import_invalidPathFailure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(Paths.get("src/test/data/ImportCommandTest/invalid.csv"));
        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }
}
