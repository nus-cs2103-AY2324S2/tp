package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.attributes.DeleteAttributeCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteAttributeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_pass() {
        ALICE.deleteAttribute("Name");
        assertFalse(ALICE.hasAttribute("Name"));
        ALICE.setAttribute("Name", "Alice Pauline");
    }

    @Test
    public void execute_null() {
        DeleteAttributeCommand deleteAttributeCommand = new DeleteAttributeCommand(ALICE.getUuidString(), "Name");
        assertThrows(NullPointerException.class, () -> deleteAttributeCommand.execute(null));
        ALICE.setAttribute("Name", "Alice Pauline");
    }
}
