package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAllCourseMates;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class SimilarNameCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());
        expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new GroupList());
    }

    @Test
    public void execute_moreThanOneSimilarName() {
        showAllCourseMates(model, new Name("a"));
        SimilarNameCommand similarNameCommand = new SimilarNameCommand(new QueryableCourseMate(new Name("a")));
        String expectedMessage = String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME, 4, "a");

        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("a");
        expectedModel.updateFilteredCourseMateList(predicate);

        assertCommandSuccess(similarNameCommand, model, expectedMessage, expectedModel, true);
    }

}
