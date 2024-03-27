package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersonsWithLoans.LOAN_RECORDS;
import static seedu.address.testutil.TypicalPersonsWithLoans.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LoanRecords;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkLoanCommand.
 */
public class MarkLoanCommandTest {
    private static LoanRecords LOAN_RECORDS_STUB = LOAN_RECORDS;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_markLoanSuccess() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LOAN_RECORDS_STUB.markLoan(0);
        Person editedPerson = new PersonBuilder(firstPerson).withLoanRecords(LOAN_RECORDS_STUB).build();

        MarkLoanCommand markLoanCommand = new MarkLoanCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(MarkLoanCommand.MESSAGE_SUCCESS, editedPerson.getName(),
                LOAN_RECORDS_STUB.getLoan(0));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(markLoanCommand, model, expectedMessage, expectedModel);
    }
}
