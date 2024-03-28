package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_KEPLER;
import static seedu.address.testutil.TypicalPersons.KEPLER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DonateCommandTest {
    private static final int MERIT_SCORE_STUB = 2;
    private static final String bookTitle = "Some book";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_returnUnfilteredList_success() {
        Person editedPerson = new PersonBuilder(KEPLER).withMeritScore(MERIT_SCORE_STUB).build();

        DonateCommand donateCommand = new DonateCommand(INDEX_KEPLER, new BookList(bookTitle));

        String expectedMessage = String.format(DonateCommand.MESSAGE_DONATE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(KEPLER, editedPerson);

        assertCommandSuccess(donateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void empty_bookTitle_failure() {
        DonateCommand donateCommand = new DonateCommand(INDEX_KEPLER, new BookList(""));
        String expectedMessage = Messages.MESSAGE_EMPTY_BOOK_INPUT_FIELD;

        assertCommandFailure(donateCommand, model, expectedMessage);
    }
}
