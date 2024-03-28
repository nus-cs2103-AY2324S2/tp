package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ADDSKILL_CPP_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ADDSKILL_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_NEWSKILL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertRecentlyProcessedCourseMateEdited;
import static seedu.address.logic.commands.CommandTestUtil.showAllCourseMates;
import static seedu.address.logic.commands.CommandTestUtil.showCourseMateAtIndex;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE_MATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE_MATE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddSkillCommand.AddSkillDescriptor;
import seedu.address.model.ContactList;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.skill.Skill;
import seedu.address.testutil.AddSkillDescriptorBuilder;
import seedu.address.testutil.CourseMateBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddSkillCommand.
 */
public class AddSkillCommandTest {

    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void execute_singleSkillUnfilteredList_success() {
        Index indexLastCourseMate = Index.fromOneBased(model.getFilteredCourseMateList().size());
        CourseMate lastCourseMate = model.getFilteredCourseMateList().get(indexLastCourseMate.getZeroBased());

        CourseMateBuilder courseMateInList = new CourseMateBuilder(lastCourseMate);
        CourseMate editedCourseMate = courseMateInList.withSkills(VALID_SKILL_JAVA).build();

        AddSkillDescriptor descriptor = new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build();
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(indexLastCourseMate), descriptor);

        String expectedMessage = AddSkillCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(lastCourseMate, editedCourseMate);

        assertCommandSuccess(addSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_filteredList_success() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);

        CourseMate courseMateInFilteredList = model.getFilteredCourseMateList()
                .get(INDEX_FIRST_COURSE_MATE.getZeroBased());
        CourseMate editedCourseMate = new CourseMateBuilder(courseMateInFilteredList)
                .withSkills(VALID_SKILL_JAVA).build();
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE),
                new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build());

        String expectedMessage = AddSkillCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(model.getFilteredCourseMateList().get(0), editedCourseMate);

        assertCommandSuccess(addSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void execute_similarCourseMates() {
        showAllCourseMates(model, new Name("a"));
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(new Name("a")),
                new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build());

        String expectedMessage = String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME, 4, "a");

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());

        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("a");
        expectedModel.updateFilteredCourseMateList(predicate);

        assertCommandSuccess(addSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_invalidCourseMateIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseMateList().size() + 1);
        AddSkillDescriptor descriptor = new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build();
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(outOfBoundIndex), descriptor);

        assertCommandFailure(addSkillCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_invalidCourseMateNameUnfilteredList_failure() {
        Name name = new Name("Bob");
        AddSkillDescriptor descriptor = new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build();
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(name), descriptor);

        assertCommandFailure(addSkillCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
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

        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(outOfBoundIndex),
                new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_JAVA).build());

        assertCommandFailure(addSkillCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_newSkillWarning_success() {
        Index indexLastCourseMate = Index.fromOneBased(model.getFilteredCourseMateList().size());
        CourseMate lastCourseMate = model.getFilteredCourseMateList().get(indexLastCourseMate.getZeroBased());

        CourseMateBuilder courseMateInList = new CourseMateBuilder(lastCourseMate);
        CourseMate editedCourseMate = courseMateInList.withSkills(VALID_SKILL_NEWSKILL).build();

        AddSkillDescriptor descriptor = new AddSkillDescriptorBuilder().withSkills(VALID_SKILL_NEWSKILL).build();
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(indexLastCourseMate), descriptor);

        Set<Skill> newSkills = new HashSet<>();
        newSkills.add(new Skill(VALID_SKILL_NEWSKILL));
        String expectedMessage = AddSkillCommand.messageNewSkill(newSkills) + AddSkillCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), new GroupList());
        expectedModel.setCourseMate(lastCourseMate, editedCourseMate);

        assertCommandSuccess(addSkillCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, editedCourseMate);
    }

    @Test
    public void equals() {
        final AddSkillCommand standardCommand = new AddSkillCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), DESC_ADDSKILL_JAVA);

        // same values -> returns true
        AddSkillCommand.AddSkillDescriptor copyDescriptor = new AddSkillDescriptor(DESC_ADDSKILL_JAVA);
        AddSkillCommand commandWithSameValues = new AddSkillCommand(
                new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddSkillCommand(
                new QueryableCourseMate(INDEX_SECOND_COURSE_MATE), DESC_ADDSKILL_JAVA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new AddSkillCommand(new QueryableCourseMate(INDEX_FIRST_COURSE_MATE), DESC_ADDSKILL_CPP_CSHARP)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        AddSkillDescriptor addSkillDescriptor = new AddSkillDescriptor();
        AddSkillCommand addSkillCommand = new AddSkillCommand(new QueryableCourseMate(index), addSkillDescriptor);
        String expected = AddSkillCommand.class.getCanonicalName()
                + "{index=" + index + ", addSkillDescriptor="
                + addSkillDescriptor + "}";
        assertEquals(expected, addSkillCommand.toString());
    }

}
