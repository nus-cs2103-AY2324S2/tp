package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddMemPointsCommandTest {
    private static final int POINTS_TO_ADD_STUB = 10;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMembershipPoints("10").build();

        AddMemPointsCommand addMemPointsCommand = new AddMemPointsCommand(firstPerson.getName(), POINTS_TO_ADD_STUB);
        String expectedMessage = String.format(AddMemPointsCommand.MESSAGE_ADD_MEMBERSHIP_SUCCESS, POINTS_TO_ADD_STUB,
                editedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        System.out.println(expectedModel);
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addMemPointsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_throwsCommandException() {
        Name invalidName = new Name("Invalid Name");
        AddMemPointsCommand addMemPointsCommand = new AddMemPointsCommand(invalidName, POINTS_TO_ADD_STUB);
        assertCommandFailure(addMemPointsCommand, model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }
}
