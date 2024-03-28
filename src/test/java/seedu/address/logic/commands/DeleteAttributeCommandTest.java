package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteAttributeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_null() {
        String[] attributeList = {"Name"};
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(ALICE.getUuidString(),
                attributeList);
        assertThrows(NullPointerException.class, () -> deleteAttributeCommand.execute(null));
        ALICE.setAttribute("Name", "Alice Pauline");
    }

    @Test
    public void execute_fail() {
        String[] attributeList = {"Name"};
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(null, attributeList);
        assertThrows(NullPointerException.class, () -> deleteAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail2() {
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(ALICE.getUuidString(), null);
        assertThrows(CommandException.class, () -> deleteAttributeCommand.execute(model));
    }

    @Test
    public void execute_pass() throws CommandException {
        String[] attributeList = {"Address"};
        DeleteAttributeCommand deleteAttributeCommand =
                new DeleteAttributeCommand(ALICE.getUuidString().substring(32, 36), attributeList);
        deleteAttributeCommand.execute(model);
        assertThrows(IllegalArgumentException.class, () -> ALICE.getAttribute("Address"));
    }

    @Test
    public void execute_noAttribute() {
        String[] attributeList = {"Dog"};
        DeleteAttributeCommand deleteAttributeCommand =
                new DeleteAttributeCommand(ALICE.getUuidString().substring(32, 36), attributeList);
        assertThrows(CommandException.class, () -> deleteAttributeCommand.execute(model));
    }

    @Test
    public void execute_noPerson() {
        String[] attributeList = {"Name"};
        DeleteAttributeCommand deleteAttributeCommand =
                new DeleteAttributeCommand("1234", attributeList);
        assertThrows(Exception.class, () -> deleteAttributeCommand.execute(model));
    }
}
