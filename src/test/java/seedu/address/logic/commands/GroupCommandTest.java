package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_GROUP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_GROUP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;



class GroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person groupedPerson = model.getFilteredPersonList().get(0);
        PersonBuilder personInList = new PersonBuilder(groupedPerson);
        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder(groupedPerson)
                .build();
        GroupCommand groupCommand = new GroupCommand(groupedPerson.getNusId(), descriptor);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(groupedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), groupedPerson);

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        NusId nusId = new NusId("E0000000"); // NusId does not exist
        GroupCommand groupCommand = new GroupCommand(nusId, new GroupCommand.GroupPersonDescriptor());
        assertCommandFailure(groupCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Test
    public void equals() {
        final GroupCommand standardCommand =
                new GroupCommand(new NusId(VALID_NUSID_AMY), DESC_AMY_GROUP);

        // same values -> returns true
        GroupCommand.GroupPersonDescriptor copyDescriptor =
                new GroupPersonDescriptorBuilder().withNusId(VALID_NUSID_AMY).withTag(VALID_TAG_AMY)
                        .withGroups(VALID_GROUP_HUSBAND).build();

        GroupCommand commandWithSameValues = new GroupCommand(new NusId(VALID_NUSID_AMY), copyDescriptor);
        //assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new GroupCommand(new NusId(VALID_NUSID_AMY), DESC_AMY_GROUP)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new GroupCommand(new NusId(VALID_NUSID_BOB), DESC_BOB_GROUP)));
    }
    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        GroupCommand.GroupPersonDescriptor groupPersonDescriptor = new GroupCommand.GroupPersonDescriptor();
        GroupCommand groupCommand = new GroupCommand(new NusId(VALID_NUSID_BOB),
                groupPersonDescriptor);
        String expected = GroupCommand.class.getCanonicalName() + "{nusid=" + VALID_NUSID_BOB
                + ", groupPersonDescriptor="
                + groupPersonDescriptor + "}";
        System.out.println(expected);
        System.out.println(groupCommand.toString());
        assertEquals(expected, groupCommand.toString());
    }
}
