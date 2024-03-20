package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.attributes.DeleteAttributeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteAttributeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_null() {
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(ALICE.getUuidString(), "Name");
        assertThrows(NullPointerException.class, () -> deleteAttributeCommand.execute(null));
        ALICE.setAttribute("Name", "Alice Pauline");
    }

    @Test
    public void execute_fail() {
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(null, "Name");
        assertThrows(NullPointerException.class, () -> deleteAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail2() {
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(ALICE.getUuidString(), null);
        assertThrows(NullPointerException.class, () -> deleteAttributeCommand.execute(model));
    }

    @Test
    public void execute_pass() throws CommandException {
        DeleteAttributeCommand deleteAttributeCommand =
                new DeleteAttributeCommand(ALICE.getUuidString().substring(32, 36), "Address");
        deleteAttributeCommand.execute(model);
        assertThrows(IllegalArgumentException.class, () -> ALICE.getAttribute("Address"));
    }

    @Test
    public void execute_noAttribute() {
        DeleteAttributeCommand deleteAttributeCommand =
                new DeleteAttributeCommand(ALICE.getUuidString().substring(32, 36), "Dog");
        assertThrows(CommandException.class, () -> deleteAttributeCommand.execute(model));
    }

    @Test
    public void execute_noPerson() {
        DeleteAttributeCommand deleteAttributeCommand =
                new DeleteAttributeCommand("1234", "Name");
        assertThrows(Exception.class, () -> deleteAttributeCommand.execute(model));
    }
}
