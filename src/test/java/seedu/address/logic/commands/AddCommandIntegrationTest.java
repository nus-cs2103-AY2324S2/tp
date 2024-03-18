package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.StartupBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newStartup_success() {
        Startup validStartup = new StartupBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStartup(validStartup);

        assertCommandSuccess(new AddCommand(validStartup), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validStartup)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStartup_throwsCommandException() {
        Startup startupInList = model.getAddressBook().getStartupList().get(0);
        assertCommandFailure(new AddCommand(startupInList), model,
                AddCommand.MESSAGE_DUPLICATE_STARTUP);
    }

}
