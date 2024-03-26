package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAttributeCommand;
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
    public void execute_pass_phoneNumber() throws CommandException {
        ALICE.deleteAttribute("Phone");
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "Phone", "12345678");
        addAttributeCommand.execute(model);
        assertNotNull(ALICE.getAttribute("Phone"));
    }

    @Test
    public void execute_pass_date() throws CommandException {
        ALICE.deleteAttribute("Birthday");
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "Birthday", "2001-01-01");
        addAttributeCommand.execute(model);
        assertNotNull(ALICE.getAttribute("Birthday"));
    }

    @Test
    public void execute_pass_genderFemale() throws CommandException {
        ALICE.deleteAttribute("Sex");
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "sex", "female");
        addAttributeCommand.execute(model);
        assertNotNull(ALICE.getAttribute("Sex"));
    }

    @Test
    public void execute_pass_genderMale() throws CommandException {
        ALICE.deleteAttribute("Sex");
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "sex", "male");
        addAttributeCommand.execute(model);
        assertNotNull(ALICE.getAttribute("Sex"));
    }

    @Test
    public void execute_fail_nullAttribute() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), null, "Alice");
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail_nullValue() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString(), "Name", null);
        assertThrows(NullPointerException.class, () -> addAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail_invalidDate() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "Birthday", "2001-13-32");
        assertThrows(CommandException.class, () -> addAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail_invalidPhoneNumber() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "Phone", "-12345");
        assertThrows(CommandException.class, () -> addAttributeCommand.execute(model));
    }

    @Test
    public void execute_fail_invalidSex() {
        AddAttributeCommand addAttributeCommand =
                new AddAttributeCommand(ALICE.getUuidString().substring(36 - 4), "Sex", "Helicopter");
        assertThrows(CommandException.class, () -> addAttributeCommand.execute(model));
    }

}
