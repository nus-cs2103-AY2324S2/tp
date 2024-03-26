package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edulink.testutil.TypicalPersons.ALICE;
import static seedu.edulink.testutil.TypicalPersons.BENSON;
import static seedu.edulink.testutil.TypicalPersons.DANIEL;
import static seedu.edulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.student.TagsContainQueryTagsPredicate;
import seedu.edulink.model.tag.Tag;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags1 = new HashSet<>();
        queryTags1.add(testTag1);
        queryTags1.add(testTag2);

        Tag testTag3 = new Tag("Hardworking");
        Set<Tag> queryTags2 = new HashSet<>();
        queryTags2.add(testTag3);

        Predicate<Student> firstPredicate =
                new TagsContainQueryTagsPredicate(queryTags1);
        Predicate<Student> secondPredicate =
                new TagsContainQueryTagsPredicate(queryTags2);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different name -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_nonExistentTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        Tag testTag1 = new Tag("Intelligent");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentTag_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentTags_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);

        Tag testTag1 = new Tag("friends");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }


    @Test
    public void toStringMethod() {
        Tag testTag1 = new Tag("TA");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);

        Predicate<Student> predicate = new TagsContainQueryTagsPredicate(queryTags);
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());

        Tag testTag2 = new Tag("Smart");
        queryTags.add(testTag2);
        predicate = new TagsContainQueryTagsPredicate(queryTags);
        filterCommand = new FilterCommand(predicate);
        expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}
