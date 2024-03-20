package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class GroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Checks that Alice's group is correctly changed to group 99.
     */
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {

        //change alice to group 99
        Person editedPerson = new PersonBuilder(TypicalPersons.ALICE).withGroups("Group 99").build();
        HashSet<StudentId> studentIds = new HashSet<>();
        studentIds.add(editedPerson.getStudentId());
        GroupCommand groupCommand = new GroupCommand(editedPerson.getGroups(), studentIds);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }
}
