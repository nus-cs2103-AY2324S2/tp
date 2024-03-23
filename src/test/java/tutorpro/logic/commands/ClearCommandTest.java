package tutorpro.logic.commands;

//import static tutorpro.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import tutorpro.model.AddressBook;
import tutorpro.model.Model;
import tutorpro.model.ModelManager;
import tutorpro.model.UserPrefs;
import tutorpro.testutil.TypicalStudents;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
