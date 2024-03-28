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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Disabled;
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

class GroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListOnePerson_success() {
        List<Person> groupedPerson = new ArrayList<>();
        Set<NusId> nusIds = new HashSet<>();
        groupedPerson.add(model.getFilteredPersonList().get(0));

        for (Person person : groupedPerson) {
            nusIds.add(person.getNusId());
        }

        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder(groupedPerson).build();
        GroupCommand groupCommand = new GroupCommand(nusIds, descriptor);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(groupedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), groupedPerson.get(0));

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    @Disabled
    @Test
    public void execute_allFieldsSpecifiedUnfilteredListMultiplePerson_success() {
        List<Person> groupedPersons = new ArrayList<>();
        Set<NusId> nusIds = new HashSet<>();
        groupedPersons.add(model.getFilteredPersonList().get(0));
        groupedPersons.add(model.getFilteredPersonList().get(1));
        for (Person person : groupedPersons) {
            nusIds.add(person.getNusId());
        }

        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder(groupedPersons).build();
        GroupCommand groupCommand = new GroupCommand(nusIds, descriptor);

        String expectedMessage = String.format(GroupCommand.MESSAGE_GROUP_PERSON_SUCCESS,
                Messages.format(GroupCommand.createGroupedPerson(groupedPersons, descriptor)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        IntStream.range(0, groupedPersons.size())
                .forEach(index -> {
                    expectedModel.setPerson(model.getFilteredPersonList().get(index), groupedPersons.get(index));
                });

        assertCommandSuccess(groupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNusIdFilteredList_failure() {
        NusId nusId = new NusId("E0000000"); // NusId does not exist
        Set<NusId> nusIdSet = new HashSet<>();
        nusIdSet.add(nusId);
        GroupCommand groupCommand = new GroupCommand(nusIdSet, new GroupCommand.GroupPersonDescriptor());
        assertCommandFailure(groupCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Test
    public void execute_partialInvalidPersonsNusIdFilteredList_failure() {
        NusId nusId = new NusId("E0000000"); // NusId does not exist
        Set<NusId> nusIdSet = new HashSet<>();
        nusIdSet.add(nusId);
        nusIdSet.add(model.getFilteredPersonList().get(0).getNusId());
        GroupCommand groupCommand = new GroupCommand(nusIdSet, new GroupCommand.GroupPersonDescriptor());
        assertCommandFailure(groupCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Disabled
    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        NusId nusId = new NusId("E0000000"); // NusId does not exist
        Set<NusId> nusIdSet = new HashSet<>();
        nusIdSet.add(nusId);
        GroupCommand.GroupPersonDescriptor descriptor = new GroupPersonDescriptorBuilder().build();
        GroupCommand editCommand = new GroupCommand(nusIdSet, descriptor);
        assertCommandFailure(editCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Test
    public void equals() {
        Set<NusId> nusIdSetOne = new HashSet<>();
        nusIdSetOne.add(new NusId(VALID_NUSID_AMY));

        Set<NusId> nusIdEqualSetOne = new HashSet<>();
        nusIdEqualSetOne.add(new NusId(VALID_NUSID_AMY));

        final GroupCommand standardCommand =
                new GroupCommand(nusIdSetOne, DESC_AMY_GROUP);

        // same values -> returns true
        GroupCommand.GroupPersonDescriptor copyDescriptor =
                new GroupPersonDescriptorBuilder().withTag(VALID_TAG_AMY).withGroups(VALID_GROUP_HUSBAND).build();

        GroupCommand commandWithSameValues = new GroupCommand(nusIdEqualSetOne, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        // assertFalse(standardCommand.equals(new GroupCommand(nusIdSetOne, DESC_AMY_GROUP)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new GroupCommand(nusIdSetOne, DESC_BOB_GROUP)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Set<NusId> nusIdSet = new HashSet<>();
        nusIdSet.add(new NusId(VALID_NUSID_AMY));
        nusIdSet.add(new NusId(VALID_NUSID_BOB));

        GroupCommand.GroupPersonDescriptor groupPersonDescriptor = new GroupCommand.GroupPersonDescriptor();
        GroupCommand groupCommand = new GroupCommand(nusIdSet, groupPersonDescriptor);
        String expected = GroupCommand.class.getCanonicalName() + "{nusIds=[" + VALID_NUSID_AMY + ", "
                + VALID_NUSID_BOB + "], groupPersonDescriptor="
                + groupPersonDescriptor + "}";
        assertEquals(expected, groupCommand.toString());
    }
}
