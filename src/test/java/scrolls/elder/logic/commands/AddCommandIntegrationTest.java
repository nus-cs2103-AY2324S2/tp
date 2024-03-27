package scrolls.elder.logic.commands;

import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandFailure;
import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrolls.elder.logic.Messages;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.model.person.Person;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalDatastore;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalDatastore.getTypicalDatastore(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getDatastore(), new UserPrefs());
        expectedModel.getMutableDatastore().getMutablePersonStore().addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getDatastore().getPersonStore().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
