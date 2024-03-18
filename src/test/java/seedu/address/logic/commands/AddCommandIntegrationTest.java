package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

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
    public void execute_newPerson_success() {
        Contact validContact = new ContactBuilder().build();

        Model expectedModel = new ModelManager(model.getCodeConnect(), new UserPrefs());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddCommand(validContact), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validContact)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Contact contactInList = model.getCodeConnect().getContactList().get(0);
        assertCommandFailure(new AddCommand(contactInList), model,
                AddCommand.MESSAGE_DUPLICATE_CONTACT);
    }

}
