package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddPolicyCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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
import seedu.address.model.policy.Policy;

public class AddPolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(index, new Policy("Life", "123"));
        AddPolicyCommand sameAddPolicyCommand = new AddPolicyCommand(index, new Policy("Life", "123"));

        assertTrue(addPolicyCommand.equals(addPolicyCommand));
        assertTrue(addPolicyCommand.equals(sameAddPolicyCommand));
        assertFalse(addPolicyCommand.equals(null));
        assertFalse(addPolicyCommand.equals(new Object()));
        assertFalse(addPolicyCommand.equals(new AddPolicyCommand(Index.fromOneBased(2),
                new Policy("Life", "123"))));
        assertFalse(addPolicyCommand.equals(new AddPolicyCommand(Index.fromOneBased(1),
                new Policy("Health", "123"))));
        assertFalse(addPolicyCommand.equals(new AddPolicyCommand(Index.fromOneBased(1),
                new Policy("Life", "113"))));
    }

    @Test
    public void execute_validIndexAndPolicyID_success() {
        Index validIndex = Index.fromOneBased(1);
        Policy policyToAdd = new Policy("Life", "123");
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(validIndex, policyToAdd);

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPolicy(model.getAddressBook().getPersonList().get(0), policyToAdd);

        CommandTestUtil.assertCommandSuccess(addPolicyCommand, model,
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().get(0).getName()), expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Policy policyToAdd = new Policy("Life", "123");

        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(invalidIndex, policyToAdd);

        assertThrows(CommandException.class, () -> addPolicyCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_conflictingId_throwsCommandException() {
        Index validIndex = Index.fromOneBased(1);
        Policy policyToAdd = new Policy("Life", "123");
        Policy conflictingPolicyToAdd = new Policy("Health", "123");

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPolicy(model.getAddressBook().getPersonList().get(0), policyToAdd);

        AddPolicyCommand firstAddPolicyCommand = new AddPolicyCommand(validIndex, policyToAdd);
        AddPolicyCommand secondAddPolicyCommand = new AddPolicyCommand(validIndex, conflictingPolicyToAdd);

        CommandTestUtil.assertCommandSuccess(firstAddPolicyCommand, model,
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().get(0).getName()), expectedModel);

        assertThrows(CommandException.class, () -> secondAddPolicyCommand.execute(model),
                Messages.MESSAGE_DUPLICATE_POLICY);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index validIndex = Index.fromOneBased(1);
        Policy policyToAdd = new Policy("Life", "123");
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(validIndex, policyToAdd);


        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPolicy(model.getAddressBook().getPersonList().get(0), policyToAdd);

        CommandTestUtil.assertCommandSuccess(addPolicyCommand, model,
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().get(0).getName()), expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Policy policyToAdd = new Policy("Life", "123");
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(outOfBoundIndex, policyToAdd);

        assertCommandFailure(addPolicyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toString_validCommand_returnsExpectedString() {
        Index index = Index.fromOneBased(1);
        Policy policyToAdd = new Policy("Life", "123");
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(index, policyToAdd);
        String expected = AddPolicyCommand.class.getCanonicalName() + "{index=" + index + ", addPolicy="
                + policyToAdd + "}";
        assertEquals(expected, addPolicyCommand.toString());
    }
}

