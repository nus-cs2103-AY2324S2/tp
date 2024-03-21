package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DELSKILL_CPP_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DELSKILL_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
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
import seedu.address.logic.commands.DeleteSkillCommand.DeleteSkillDescriptor;
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
import seedu.address.testutil.DeleteSkillDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteSkillCommand.
 */
public class DeleteSkillCommandTest {

    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void execute_singleSkillUnfilteredList_success() {
        CourseMate courseMateToEdit = model.getFilteredCourseMateList().get(INDEX_FIRST_COURSE_MATE.getZeroBased());

        CourseMateBuilder courseMateInList = new CourseMateBuilder(courseMateToEdit);
        CourseMate editedCourseMate = courseMateInList.removeSkills(VALID_SKILL_JAVA).build();

        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build();
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), descriptor);

        String expectedMessage = DeleteSkillCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(courseMateToEdit, editedCourseMate);

        assertCommandSuccess(deleteSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_filteredList_success() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);

        CourseMate courseMateInFilteredList = model.getFilteredCourseMateList()
                .get(INDEX_FIRST_COURSE_MATE.getZeroBased());
        CourseMate editedCourseMate = new CourseMateBuilder(courseMateInFilteredList)
                .removeSkills(VALID_SKILL_JAVA).build();
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE),
                new DeleteSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build());

        String expectedMessage = DeleteSkillCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(model.getFilteredCourseMateList().get(0), editedCourseMate);

        assertCommandSuccess(deleteSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_similarCourseMates() {
        showAllCourseMates(model, new Name("a"));
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(new QueryableCourseMate(new Name("a")),
                new DeleteSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build());
        String expectedMessage = String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME, 4);

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());

        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("a");
        expectedModel.updateFilteredCourseMateList(predicate);

        assertCommandSuccess(deleteSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_invalidCourseMateIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseMateList().size() + 1);
        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build();
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(
                new QueryableCourseMate(outOfBoundIndex), descriptor);

        assertCommandFailure(deleteSkillCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_invalidCourseMateNameUnfilteredList_failure() {
        Name name = new Name("Bob");
        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build();
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(new QueryableCourseMate(name), descriptor);

        assertCommandFailure(deleteSkillCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
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

        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(new QueryableCourseMate(outOfBoundIndex),
                new DeleteSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build());

        assertCommandFailure(deleteSkillCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void equals() {
        final DeleteSkillCommand standardCommand = new DeleteSkillCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), DESC_DELSKILL_JAVA);

        // same values -> returns true
        DeleteSkillCommand.DeleteSkillDescriptor copyDescriptor = new DeleteSkillDescriptor(DESC_DELSKILL_JAVA);
        DeleteSkillCommand commandWithSameValues = new DeleteSkillCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteSkillCommand(
                new QueryableCourseMate(INDEX_SECOND_COURSE_MATE), DESC_DELSKILL_JAVA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new DeleteSkillCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), DESC_DELSKILL_CPP_CSHARP)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        DeleteSkillDescriptor deleteSkillDescriptor = new DeleteSkillDescriptor();
        DeleteSkillCommand deleteSkillCommand = new DeleteSkillCommand(
                new QueryableCourseMate(index), deleteSkillDescriptor);
        String expected = DeleteSkillCommand.class.getCanonicalName()
                + "{index=" + index + ", deleteSkillDescriptor="
                + deleteSkillDescriptor + "}";
        assertEquals(expected, deleteSkillCommand.toString());
    }

}
