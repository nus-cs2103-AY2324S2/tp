package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ResetDebtCommandTest {
    @Test
    public void execute_validPerson_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Benson owes money
        ResetDebtCommand command = new ResetDebtCommand(BENSON);
        expectedModel.setPerson(BENSON, new PersonBuilder(BENSON).withMoneyOwed("0").build());
        assertCommandSuccess(command, model,
                String.format(ResetDebtCommand.RESET_SUCCESS_MESSAGE, BENSON.getName()), expectedModel);

        // Alice doesn't owe money so don't need to replace her in the address book
        command = new ResetDebtCommand(ALICE);
        assertCommandSuccess(command, model,
                String.format(ResetDebtCommand.RESET_SUCCESS_MESSAGE, ALICE.getName()), expectedModel);
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ResetDebtCommand command = new ResetDebtCommand(AMY);
        assertCommandFailure(command, model, String.format(ResetDebtCommand.PERSON_NOT_FOUND_MESSAGE, AMY.getPhone()));
    }

    @Test
    public void equals() {
        ResetDebtCommand resetDebtFirstCommand = new ResetDebtCommand(AMY);
        ResetDebtCommand resetDebtSecondCommand = new ResetDebtCommand(BOB);

        // same object -> returns true
        assertEquals(resetDebtFirstCommand, resetDebtFirstCommand);

        // same values -> returns true
        ResetDebtCommand resetDebtFirstCommandCopy = new ResetDebtCommand(AMY);
        assertEquals(resetDebtFirstCommand, resetDebtFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(resetDebtFirstCommand, 1);

        // null -> returns false
        assertNotEquals(resetDebtFirstCommand, null);

        // different person -> returns false
        assertNotEquals(resetDebtFirstCommand, resetDebtSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Person person = AMY;
        ResetDebtCommand resetDebtCommand = new ResetDebtCommand(person);
        String expected = ResetDebtCommand.class.getCanonicalName() + "{originalPerson=" + person + "}";
        assertEquals(expected, resetDebtCommand.toString());
    }
}
