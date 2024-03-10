package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_CLASS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_CLASS_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AddClassCommand.MESSAGE_NOT_IMPLEMENTED_YET;
import static seedu.address.logic.commands.AddClassCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.person.Name;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class AddClassCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {

        assertCommandFailure(new AddClassCommand(new Module(new Name(VALID_MODULE_CODE), new TutorialClass(new Name(VALID_TUTORIAL_CLASS)))), model,
                String.format(MESSAGE_ARGUMENTS, VALID_MODULE_CODE));
    }

    @Test
    public void equals() {
        final AddClassCommand standardCommand = new AddClassCommand(new Module(new Name(VALID_MODULE_CODE),
                new TutorialClass(new Name(VALID_TUTORIAL_CLASS))));

        // same values -> returns true
        AddClassCommand commandWithSameValues = new AddClassCommand(new Module(new Name(VALID_MODULE_CODE),
                new TutorialClass(new Name(VALID_TUTORIAL_CLASS))));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different module code -> returns false
        assertFalse(standardCommand.equals(new AddClassCommand(new Module(new Name(VALID_MODULE_CODE_2),
                new TutorialClass(new Name(VALID_TUTORIAL_CLASS))))));

        // different tutorial class -> returns false
        assertFalse(standardCommand.equals(new AddClassCommand(new Module(new Name(VALID_MODULE_CODE),
                new TutorialClass(new Name(VALID_TUTORIAL_CLASS_2))))));
    }
}

