package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.attributes.AddAttributeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddAttributeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_null() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), "Name", "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(null));
    }

    @Test
    public void execute_pass() throws CommandException {
        ALICE.deleteAttribute("Name");
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "Name", "Alice");
        addAttributeCommand.execute(model);
        assertNotNull(ALICE.getAttribute("Name"));
    }

    @Test
    public void execute_fail() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(null, "Name", "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(null));
    }

    @Test
    public void execute_fail2() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), null, "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail3() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), "Name", null);
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(model));
    }
}
