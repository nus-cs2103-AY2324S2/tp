package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_PERSON_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsEmails;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code CopyCommand}.
 */
public class CopyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    private Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
    @Test
    public void equals() {
        CopyCommand copyCommand = new CopyCommand();

        // same object -> returns true
        assertTrue(copyCommand.equals(copyCommand));


        // different types -> returns false
        assertFalse(copyCommand.equals(1));

        // null -> returns false
        assertFalse(copyCommand.equals(null));
    }

    @Test
    public void execute_emptyList_noEmailsCopied() {
        assertCommandFailure(new CopyCommand(), emptyModel, MESSAGE_EMPTY_PERSON_LIST);
    }

    @Test
    public void execute_nonEmptyList_emailsCopied() {
        assertCommandSuccess(new CopyCommand(), model, CopyCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void getEmailsMethod() {
        List<Person> lastShownList = getTypicalPersons();
        CopyCommand copyCommand = new CopyCommand();
        StringSelection emails = copyCommand.getEmails(lastShownList);
        StringSelection expectedEmails = new StringSelection(getTypicalPersonsEmails());

        try {
            String data1 = (String) emails.getTransferData(DataFlavor.stringFlavor);
            String data2 = (String) expectedEmails.getTransferData(DataFlavor.stringFlavor);
            assertTrue(data1.equals(data2));
        } catch (UnsupportedFlavorException e) {
            throw new AssertionError("DataFlavor not supported");
        } catch (IOException e) {
            throw new AssertionError("IOException");
        }
    }
}
