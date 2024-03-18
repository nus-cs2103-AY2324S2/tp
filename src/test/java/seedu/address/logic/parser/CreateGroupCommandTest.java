package seedu.address.logic.parser;

import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CreateGroupCommand
 */
public class CreateGroupCommandTest {

    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs());
}
