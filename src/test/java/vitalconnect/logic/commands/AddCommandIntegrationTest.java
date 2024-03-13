package vitalconnect.logic.commands;

import static vitalconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static vitalconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vitalconnect.logic.Messages;
import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.UserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClinic(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getClinic(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getClinic().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
