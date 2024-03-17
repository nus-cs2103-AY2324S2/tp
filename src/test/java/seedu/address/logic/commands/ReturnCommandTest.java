package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_JACKER;
import static seedu.address.testutil.TypicalPersons.JACKER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ReturnCommandTest {
    private static final String BOOKLIST_STUB = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_returnUnfilteredList_success() {
        Person editedPerson = new PersonBuilder(JACKER).withBook(BOOKLIST_STUB).withMeritScore(0).build();

        ReturnCommand returnCommand = new ReturnCommand(INDEX_JACKER);

        String expectedMessage = String.format(ReturnCommand.MESSAGE_RETURN_BOOK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(JACKER, editedPerson);

        assertCommandSuccess(returnCommand, model, expectedMessage, expectedModel);
    }
}
