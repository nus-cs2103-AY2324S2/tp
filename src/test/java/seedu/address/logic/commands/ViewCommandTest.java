package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allView_success() {
        Person viewPerson = new PersonBuilder().build();
        model.addPerson(viewPerson);
        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(7));

        String expectedMessage = String.format(viewCommand.MESSAGE_VIEW_SUCCESS, viewPerson.getName());
        assertCommandSuccess(viewCommand, model, expectedMessage, model);
    }

}
