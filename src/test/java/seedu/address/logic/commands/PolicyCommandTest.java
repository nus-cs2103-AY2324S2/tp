package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PolicyCommand.
 */
public class PolicyCommandTest {

    private static final String POLICY_STUB = "Some policy";
    private static final HashSet<Policy> POLICY_HASH_SET_STUB = new HashSet<>(Set.of(new Policy(POLICY_STUB)));

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addPolicyUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPolicy(POLICY_STUB).build();

        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON, editedPerson.getPolicies());

        String expectedMessage = String.format(PolicyCommand.MESSAGE_ADD_POLICY_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deletePolicyUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPolicy().build();

        PolicyCommand remarkCommand = new PolicyCommand(INDEX_FIRST_PERSON, editedPerson.getPolicies());

        String expectedMessage =
                String.format(PolicyCommand.MESSAGE_DELETE_POLICY_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withPolicy(POLICY_STUB).build();

        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON, editedPerson.getPolicies());

        String expectedMessage = String.format(PolicyCommand.MESSAGE_ADD_POLICY_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PolicyCommand policyCommand = new PolicyCommand(outOfBoundIndex,
                new HashSet<Policy>(Set.of(new Policy(VALID_POLICY_BOB))));

        assertCommandFailure(policyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidRelationshipFilteredList_failure() {
        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIFTH_PERSON, POLICY_HASH_SET_STUB);

        assertCommandFailure(policyCommand, model, PolicyCommand.MESSAGE_PERSON_NOT_CLIENT_FAILURE);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PolicyCommand policyCommand = new PolicyCommand(outOfBoundIndex,
                new HashSet<Policy>(Set.of(new Policy(VALID_POLICY_BOB))));
        assertCommandFailure(policyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PolicyCommand standardCommand = new PolicyCommand(INDEX_FIRST_PERSON,
                new HashSet<Policy>(Set.of(new Policy(VALID_POLICY_AMY))));

        // same values -> returns true
        PolicyCommand commandWithSameValues = new PolicyCommand(INDEX_FIRST_PERSON,
                new HashSet<Policy>(Set.of(new Policy(VALID_POLICY_AMY))));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

    }
}
