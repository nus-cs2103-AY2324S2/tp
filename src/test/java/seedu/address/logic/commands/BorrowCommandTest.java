package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.BookList;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class BorrowCommandTest {

    private static final String BORROW_STUB = "Some bookTitle";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addBorrowUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withBook(BORROW_STUB).withMeritScore(-1).build();

        BorrowCommand borrowCommand = new BorrowCommand(INDEX_FIRST_PERSON,
                new BookList(editedPerson.getBookList().value.bookTitle));

        String expectedMessage = String.format(BorrowCommand.MESSAGE_ADD_BORROW_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(borrowCommand, model, expectedMessage, expectedModel);
    }
}
