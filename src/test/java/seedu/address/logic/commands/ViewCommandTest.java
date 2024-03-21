package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.ViewPredicate;
import seedu.address.testutil.PersonBuilder;

public class ViewCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_viewValidPerson_success() {
        // Prepare our model and expected model
        Person personToView = new PersonBuilder().build();
        model.addPerson(personToView);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Assuming the person to view is the last one added
        Index lastPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size());

        // Set up expected model's filtered list according to ViewCommand's behavior
        expectedModel.updateFilteredPersonList(new ViewPredicate(lastPersonIndex, personToView));

        // Construct command to view the last added person
        ViewCommand viewCommand = new ViewCommand(lastPersonIndex);
        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_SUCCESS, personToView.getName());

        // Execute command and verify success
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Index indexFirst = Index.fromOneBased(1);
        Index indexSecond = Index.fromOneBased(2);

        ViewCommand viewFirstCommand = new ViewCommand(indexFirst);
        ViewCommand viewSecondCommand = new ViewCommand(indexSecond);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(indexFirst);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
