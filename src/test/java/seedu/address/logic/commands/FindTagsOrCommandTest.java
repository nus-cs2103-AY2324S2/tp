package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagsOrFoundPredicate;
import seedu.address.testutil.TagBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagsOrCommand}.
 */
public class FindTagsOrCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void equals() {
        TagsOrFoundPredicate firstPredicate = preparePredicate("first");
        TagsOrFoundPredicate secondPredicate = preparePredicate("second");

        FindTagsOrCommand firstCommand = new FindTagsOrCommand(firstPredicate);
        FindTagsOrCommand secondCommand = new FindTagsOrCommand(secondPredicate);

        assertTrue(firstCommand.equals(firstCommand));
        FindTagsOrCommand firstCommandCopy = new FindTagsOrCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_zeroTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagsOrFoundPredicate predicate = preparePredicate(" ");
        FindTagsOrCommand command = new FindTagsOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagsOrFoundPredicate predicate = preparePredicate("car");
        FindTagsOrCommand command = new FindTagsOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTagWrongCase_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagsOrFoundPredicate predicate = preparePredicate("Car");
        FindTagsOrCommand command = new FindTagsOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonsFromOneTagFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagsOrFoundPredicate predicate = preparePredicate("car death test");
        FindTagsOrCommand command = new FindTagsOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonsFromDifferentTagsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TagsOrFoundPredicate predicate = preparePredicate("death health covid");
        FindTagsOrCommand command = new FindTagsOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON, CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        TagsOrFoundPredicate predicate = preparePredicate("tag");
        FindTagsOrCommand command = new FindTagsOrCommand(predicate);
        String expected = FindTagsOrCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TagsOrFoundPredicate}.
     */
    private TagsOrFoundPredicate preparePredicate(String userInput) {
        List<String> tagList = List.of(userInput.split("\\s+"));
        return new TagsOrFoundPredicate(TagBuilder.build(tagList));
    }

}
