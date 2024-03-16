package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_BOB;
import static seedu.address.logic.commands.PolicyCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Policy;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PolicyCommand.
 */
public class PolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Policy policy = new Policy("Some policy");

        assertCommandFailure(new PolicyCommand(INDEX_FIRST_PERSON, policy), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), policy));
    }

    @Test
    public void equals() {
        final PolicyCommand standardCommand = new PolicyCommand(INDEX_FIRST_PERSON, new Policy(VALID_POLICY_AMY));

        // same values -> returns true
        PolicyCommand commandWithSameValues = new PolicyCommand(INDEX_FIRST_PERSON, new Policy(VALID_POLICY_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PolicyCommand(INDEX_SECOND_PERSON, new Policy(VALID_POLICY_AMY))));

        // different policy -> returns false
        assertFalse(standardCommand.equals(new PolicyCommand(INDEX_FIRST_PERSON, new Policy(VALID_POLICY_BOB))));
    }
}