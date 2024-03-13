package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static staffconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.DANIEL;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import staffconnect.model.Model;
import staffconnect.model.ModelManager;
import staffconnect.model.UserPrefs;
import staffconnect.model.person.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffBook(), new UserPrefs());

    @Test
    public void execute_personHasTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagsPredicate tagPredicate = prepareTagPredicate("hello");
        FilterCommand command = new FilterCommand(tagPredicate);
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagsPredicate tagPredicate = prepareTagPredicate("friends");
        FilterCommand command = new FilterCommand(tagPredicate);
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        PersonHasTagsPredicate firstTagPredicate = prepareTagPredicate("friend");
        PersonHasTagsPredicate secondTagPredicate = prepareTagPredicate("colleagues");

        FilterCommand filterTagFirstCommand = new FilterCommand(firstTagPredicate);
        FilterCommand filterTagSecondCommand = new FilterCommand(secondTagPredicate);

        // same object -> returns true
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommand));

        // same values -> returns true
        FilterCommand filterTagFirstCommandCopy = new FilterCommand(firstTagPredicate);
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterTagFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(filterTagFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void toStringMethod() {
        PersonHasTagsPredicate tagPredicate = prepareTagPredicate("hello");
        FilterCommand filterCommand = new FilterCommand(tagPredicate);
        String expected = FilterCommand.class.getCanonicalName() + "{tagPredicate=" + tagPredicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasTagPredicate}.
     */
    private PersonHasTagsPredicate prepareTagPredicate(String userInput) {
        List<Tag> tagList = Stream.of(userInput.split(" ")).map(str -> new Tag(str)).collect(Collectors.toList());
        for (String separatedTag : userInput.split(" ")) {
            tagList.add(new Tag(separatedTag));
        }
        return new PersonHasTagsPredicate(new HashSet<Tag>(tagList));
    }

}
