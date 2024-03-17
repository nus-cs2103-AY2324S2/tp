package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_MSHIP_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_MSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.person.Membership;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddMemshipCommandTest {
    private static final String MEMBERSHIP_STUB = "T1";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_changeMembershipUnfilteredList_success() {

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Person editedPerson = new PersonBuilder(firstPerson).withMembership(MEMBERSHIP_STUB).build();

        // Create a command to change the membership of the first person
        AddMemshipCommand addMembershipCommand = new AddMemshipCommand(firstPerson.getName(), MEMBERSHIP_STUB);

        // Expected success message
        String expectedMessage = String.format(AddMemshipCommand.MESSAGE_ADD_MEMBERSHIP_SUCCESS,
                firstPerson.getName(), MEMBERSHIP_STUB);

        // Set up the expected model with the person's membership updated
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        // Assert that the command succeeded
        assertCommandSuccess(addMembershipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        // Name that does not exist in the address book
        Name outOfBoundName = new Name("Nonexistent Name");

        // Create a command with a non-existent person
        AddMemshipCommand addMembershipCommand = new AddMemshipCommand(outOfBoundName, MEMBERSHIP_STUB);

        // Assert that the command failed
        assertCommandFailure(addMembershipCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void equals() {
        // Name of the first person in the list
        final Name firstPersonName = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName();

        // Create a standard command for comparison
        final AddMemshipCommand standardCommand = new AddMemshipCommand(firstPersonName, MEMBERSHIP_STUB);


        AddMemshipCommand commandWithSameValues = new AddMemshipCommand(firstPersonName, MEMBERSHIP_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));


        assertTrue(standardCommand.equals(standardCommand));


        assertFalse(standardCommand.equals(null));


        assertFalse(standardCommand.equals(new ClearCommand()));


        Name secondPersonName = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()).getName();
        assertFalse(standardCommand.equals(new AddMemshipCommand(secondPersonName, MEMBERSHIP_STUB)));


        assertFalse(standardCommand.equals(new AddMemshipCommand(firstPersonName, "T3")));
    }
}
