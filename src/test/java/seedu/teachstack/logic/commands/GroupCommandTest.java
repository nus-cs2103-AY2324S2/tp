package seedu.teachstack.logic.commands;

import static seedu.teachstack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.teachstack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachstack.model.util.SampleDataUtil.getGroupSet;
import static seedu.teachstack.model.util.SampleDataUtil.getStudentIdSetFromStudentIds;
import static seedu.teachstack.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.teachstack.logic.Messages;
import seedu.teachstack.logic.commands.exceptions.CommandException;
import seedu.teachstack.model.AddressBook;
import seedu.teachstack.model.Model;
import seedu.teachstack.model.ModelManager;
import seedu.teachstack.model.UserPrefs;
import seedu.teachstack.model.person.Person;
import seedu.teachstack.model.person.StudentId;
import seedu.teachstack.testutil.PersonBuilder;
import seedu.teachstack.testutil.TypicalPersons;

public class GroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Clears the default ID of a person (in this case, Alice).
     */
    @Test
    public void clear_validID_success() {
        // Alice will have no groups
        Person alice = new PersonBuilder(TypicalPersons.ALICE).withGroups().build();
        GroupCommand groupCommand = new GroupCommand(getGroupSet(),
                getStudentIdSetFromStudentIds(alice.getStudentId()));

        // Should clear all groups
        String expectedMessage = String.format(GroupCommand.MESSAGE_CLEAR_SUCCESS, Messages.format(alice));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getPerson(alice.getStudentId()), alice);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Checks that Alice's group is correctly changed to group 99.
     */
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {

        //clear Alice's group
        model.setPerson(TypicalPersons.ALICE, new PersonBuilder(TypicalPersons.ALICE).withGroups().build());

        //change alice to group 99
        Person editedPerson = new PersonBuilder(TypicalPersons.ALICE).withGroups("Group 99").build();
        Set<StudentId> studentIds = getStudentIdSetFromStudentIds(TypicalPersons.ALICE.getStudentId());
        GroupCommand groupCommand = new GroupCommand(editedPerson.getGroups(), studentIds);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getPerson(editedPerson.getStudentId()), editedPerson);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Checks that trying to modify the student ID of someone not in the list fails.
     */
    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        //tries to put a person with (unfound) ID "A9999999Z" in Group 99.
        Person editedPerson = new PersonBuilder().withStudentId("A9999999Z")
                .withGroups("Group 99").build();
        HashSet<StudentId> studentIds = new HashSet<>();
        studentIds.add(editedPerson.getStudentId());
        GroupCommand groupCommand = new GroupCommand(editedPerson.getGroups(), studentIds);

        assertCommandFailure(groupCommand, model, GroupCommand.STUDENTS_NOT_FOUND + "A9999999Z ");
    }

    /**
     * Adds a student to a group 99. Then, adds that student to another group 100.
     * The student should be in both groups in the end.
     */
    @Test
    public void execute_rememberPreviousGroups_success() throws CommandException {

        //clear Alice's group
        model.setPerson(TypicalPersons.ALICE, new PersonBuilder(TypicalPersons.ALICE).withGroups().build());

        //change alice to group 99 and 100
        Person editedPerson = new PersonBuilder(TypicalPersons.ALICE).withGroups("Group 99", "Group 100").build();

        HashSet<StudentId> studentIds = new HashSet<>();
        studentIds.add(editedPerson.getStudentId());
        GroupCommand addToGroup99 = new GroupCommand(getGroupSet("Group 99"), studentIds);
        GroupCommand addToGroup100 = new GroupCommand(getGroupSet("Group 100"), studentIds);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getPerson(editedPerson.getStudentId()), editedPerson); // Alice in group 99, 100

        addToGroup99.execute(model);

        assertCommandSuccess(addToGroup100, model, expectedMessage, expectedModel);
    }
}
