package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertRecentlyProcessedCourseMateEdited;
import static seedu.address.logic.commands.CommandTestUtil.showAllCourseMates;
import static seedu.address.logic.commands.CommandTestUtil.showCourseMateAtIndex;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE_MATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE_MATE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditCourseMateDescriptor;
import seedu.address.model.ContactList;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.testutil.CourseMateBuilder;
import seedu.address.testutil.EditCourseMateDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        CourseMate editedCourseMate = new CourseMateBuilder().build();
        EditCommand.EditCourseMateDescriptor descriptor = new EditCourseMateDescriptorBuilder(editedCourseMate).build();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), descriptor);

        String expectedMessage = EditCommand.MESSAGE_EDIT_COURSE_MATE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());

        Index indexLastCourseMate = Index.fromOneBased(1);
        CourseMate currentCourseMate = model.getFilteredCourseMateList().get(indexLastCourseMate.getZeroBased());
        CourseMate newEditedCourseMate = new CourseMateBuilder(currentCourseMate)
                .withName(editedCourseMate.getName().toString())
                .withPhone(editedCourseMate.getPhone().toString())
                .withEmail(editedCourseMate.getEmail().toString())
                .build();

        expectedModel.setCourseMate(model.getFilteredCourseMateList().get(0), newEditedCourseMate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, newEditedCourseMate);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCourseMate = Index.fromOneBased(model.getFilteredCourseMateList().size());
        CourseMate lastCourseMate = model.getFilteredCourseMateList().get(indexLastCourseMate.getZeroBased());

        CourseMateBuilder courseMateInList = new CourseMateBuilder(lastCourseMate);
        CourseMate editedCourseMate = courseMateInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditCourseMateDescriptor descriptor = new EditCourseMateDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(indexLastCourseMate), descriptor);

        String expectedMessage = EditCommand.MESSAGE_EDIT_COURSE_MATE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(lastCourseMate, editedCourseMate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), new EditCourseMateDescriptor());
        CourseMate editedCourseMate = model.getFilteredCourseMateList().get(INDEX_FIRST_COURSE_MATE.getZeroBased());

        String expectedMessage = EditCommand.MESSAGE_EDIT_COURSE_MATE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_filteredList_success() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);

        CourseMate courseMateInFilteredList = model.getFilteredCourseMateList()
                .get(INDEX_FIRST_COURSE_MATE.getZeroBased());
        CourseMate editedCourseMate = new CourseMateBuilder(courseMateInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE),
                new EditCourseMateDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = EditCommand.MESSAGE_EDIT_COURSE_MATE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(model.getFilteredCourseMateList().get(0), editedCourseMate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_similarCourseMates() {
        showAllCourseMates(model, new Name("a"));
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(new Name("a")),
                new EditCourseMateDescriptorBuilder().withName(VALID_NAME_BOB).build());
        String expectedMessage = String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME, 4, "a");

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());

        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("a");
        expectedModel.updateFilteredCourseMateList(predicate);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_duplicateCourseMateUnfilteredList_failure() {
        CourseMate firstCourseMate = model.getFilteredCourseMateList().get(INDEX_FIRST_COURSE_MATE.getZeroBased());
        EditCourseMateDescriptor descriptor = new EditCourseMateDescriptorBuilder(firstCourseMate).build();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(INDEX_SECOND_COURSE_MATE), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_duplicateCourseMateFilteredList_failure() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);

        // edit courseMate in filtered list into a duplicate in contact list
        CourseMate courseMateInList = model.getContactList().getCourseMateList()
                .get(INDEX_SECOND_COURSE_MATE.getZeroBased());
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE),
                new EditCourseMateDescriptorBuilder(courseMateInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_invalidCourseMateIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseMateList().size() + 1);
        EditCourseMateDescriptor descriptor = new EditCourseMateDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(outOfBoundIndex), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_invalidCourseMateNameUnfilteredList_failure() {
        Name name = new Name("Bob");
        EditCourseMateDescriptor descriptor = new EditCourseMateDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(name), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of contact list
     */
    @Test
    public void execute_invalidCourseMateIndexFilteredList_failure() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);
        Index outOfBoundIndex = INDEX_SECOND_COURSE_MATE;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getCourseMateList().size());

        EditCommand editCommand = new EditCommand(new QueryableCourseMate(outOfBoundIndex),
                new EditCourseMateDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), DESC_AMY);

        // same values -> returns true
        EditCommand.EditCourseMateDescriptor copyDescriptor = new EditCourseMateDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(
                new QueryableCourseMate(INDEX_SECOND_COURSE_MATE), DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCourseMateDescriptor editCourseMateDescriptor = new EditCourseMateDescriptor();
        EditCommand editCommand = new EditCommand(new QueryableCourseMate(index), editCourseMateDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editCourseMateDescriptor="
                + editCourseMateDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
