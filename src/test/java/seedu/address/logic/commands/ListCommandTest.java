package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalClassBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalClassBook());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getClassBook());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        model.selectClass(new Classes(new CourseCode("class1")));
        expectedModel.selectClass(new Classes(new CourseCode("class1")));
        assertCommandSuccess(new ListCommand(), model,
                ListCommand.MESSAGE_SUCCESS + "class1", expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.selectClass(new Classes(new CourseCode("class1")));
        expectedModel.selectClass(new Classes(new CourseCode("class1")));
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model,
                ListCommand.MESSAGE_SUCCESS + "class1", expectedModel);
    }
}
