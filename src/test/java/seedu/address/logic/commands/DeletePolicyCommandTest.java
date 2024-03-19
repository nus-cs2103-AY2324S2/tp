package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeletePolicyCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeletePolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(index, "123");
        DeletePolicyCommand sameDeletePolicyCommand = new DeletePolicyCommand(index, "123");

        assertTrue(deletePolicyCommand.equals(deletePolicyCommand));
        assertTrue(deletePolicyCommand.equals(sameDeletePolicyCommand));
        assertFalse(deletePolicyCommand.equals(null));
        assertFalse(deletePolicyCommand.equals(new Object()));
        assertFalse(deletePolicyCommand.equals(new DeletePolicyCommand(Index.fromOneBased(2),
                "123")));
        assertFalse(deletePolicyCommand.equals(new DeletePolicyCommand(Index.fromOneBased(1),
                 "113")));
    }

    @Test
    public void execute_validIndexAndPolicyID_success() {
        Index validIndex = Index.fromOneBased(2);
        String policyToDelete = "123";
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(validIndex, policyToDelete);

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.deletePolicy(model.getAddressBook().getPersonList().get(1), policyToDelete);

        CommandTestUtil.assertCommandSuccess(deletePolicyCommand, model,
                String.format(MESSAGE_SUCCESS, policyToDelete, model.getFilteredPersonList().get(1).getName()),
                expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        String policyToDelete = "123";

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(invalidIndex, policyToDelete);

        assertThrows(CommandException.class, () -> deletePolicyCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nonExistingId_throwsCommandException() {
        Index validIndex = Index.fromOneBased(2);
        String policyToDelete = "123";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePolicy(model.getAddressBook().getPersonList().get(1), policyToDelete);

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(validIndex, "123");

        CommandTestUtil.assertCommandSuccess(deletePolicyCommand, model,
                String.format(MESSAGE_SUCCESS, policyToDelete, model.getFilteredPersonList().get(1).getName()),
                expectedModel);

        assertThrows(CommandException.class, () -> deletePolicyCommand.execute(model),
                Messages.MESSAGE_POLICY_NOT_FOUND);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index validIndex = Index.fromOneBased(1);
        String policyToDelete = "123";
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(validIndex, policyToDelete);


        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.deletePolicy(model.getAddressBook().getPersonList().get(1), policyToDelete);

        CommandTestUtil.assertCommandSuccess(deletePolicyCommand, model,
                String.format(MESSAGE_SUCCESS, policyToDelete , model.getFilteredPersonList().get(0).getName()),
                expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        String policyToDelete = "123";
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(outOfBoundIndex, policyToDelete);

        assertCommandFailure(deletePolicyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toString_validCommand_returnsExpectedString() {
        Index index = Index.fromOneBased(1);
        String policyToDelete = "123";
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(index, policyToDelete);
        String expected = DeletePolicyCommand.class.getCanonicalName() + "{index=" + index + ", deletePolicy="
                + policyToDelete + "}";
        assertEquals(expected, deletePolicyCommand.toString());
    }
}

