package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagsAndFoundPredicate;
import seedu.address.testutil.TagBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagsOrCommand}.
 */
public class FindTagsAndCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void equals() {
        TagsAndFoundPredicate firstPredicate = preparePredicate("first");
        TagsAndFoundPredicate secondPredicate = preparePredicate("second");

        FindTagsAndCommand firstCommand = new FindTagsAndCommand(firstPredicate);
        FindTagsAndCommand secondCommand = new FindTagsAndCommand(secondPredicate);

        assertTrue(firstCommand.equals(firstCommand));
        FindTagsAndCommand firstCommandCopy = new FindTagsAndCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_zeroTags_allPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, getTypicalPersons().size());
        TagsAndFoundPredicate predicate = preparePredicate(" ");
        FindTagsAndCommand command = new FindTagsAndCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalPersons(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagsAndFoundPredicate predicate = preparePredicate("car");
        FindTagsAndCommand command = new FindTagsAndCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTagWrongCase_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagsAndFoundPredicate predicate = preparePredicate("Car");
        FindTagsAndCommand command = new FindTagsAndCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_onePersonWithAllTagsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        TagsAndFoundPredicate predicate = preparePredicate("car death");
        FindTagsAndCommand command = new FindTagsAndCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_onePersonWithNotAllTagsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        TagsAndFoundPredicate predicate = preparePredicate("flight covid");
        FindTagsAndCommand command = new FindTagsAndCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        TagsAndFoundPredicate predicate = preparePredicate("tag");
        FindTagsAndCommand command = new FindTagsAndCommand(predicate);
        String expected = FindTagsAndCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TagsAndFoundPredicate}.
     */
    private TagsAndFoundPredicate preparePredicate(String userInput) {
        List<String> tagList = List.of(userInput.split("\\s+"));
        return new TagsAndFoundPredicate(TagBuilder.build(tagList));
    }

}
