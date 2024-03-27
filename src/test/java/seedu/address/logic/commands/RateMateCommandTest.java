package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.assertRecentlyProcessedCourseMateEdited;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.Rating;

public class RateMateCommandTest {
    private final Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new RateMateCommand(null, null));
    }

    @Test
    public void execute_courseMateInList_runsNormally() {
        Rating rating = new Rating("5");
        RateMateCommand rateMateCommand = new RateMateCommand(new QueryableCourseMate(ALICE.getName()), rating);
        assertTrue(model.hasCourseMate(ALICE));
        assertDoesNotThrow(() -> rateMateCommand.execute(model));
    }

    @Test
    public void execute_courseMateNotInList_throwsCommandException() {
        Rating rating = new Rating("5");
        RateMateCommand rateMateCommand = new RateMateCommand(new QueryableCourseMate(new Name("Test")), rating);
        assertThrows(CommandException.class, () ->
                rateMateCommand.execute(model));
    }

    @Test
    public void execute_similarCourseMates() {
        showAllCourseMates(model, new Name("a"));
        RateMateCommand rateMateCommand = new RateMateCommand(new QueryableCourseMate(new Name("a")), new Rating("5"));
        String expectedMessage = String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME, 4, "a");

        Model expectedModel = new ModelManager(
                new ContactList(model.getContactList()), new UserPrefs(), getTypicalGroupList());

        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("a");
        expectedModel.updateFilteredCourseMateList(predicate);

        assertCommandSuccess(rateMateCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

}
