package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.testutil.CourseMateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactList(), new UserPrefs());
    }

    @Test
    public void execute_newCourseMate_success() {
        CourseMate validCourseMate = new CourseMateBuilder().build();

        Model expectedModel = new ModelManager(model.getContactList(), new UserPrefs());
        expectedModel.addCourseMate(validCourseMate);

        assertCommandSuccess(new AddCommand(validCourseMate), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validCourseMate)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCourseMate_throwsCommandException() {
        CourseMate courseMateInList = model.getContactList().getCourseMateList().get(0);
        assertCommandFailure(new AddCommand(courseMateInList), model,
                AddCommand.MESSAGE_DUPLICATE_COURSE_MATE);
    }

}
