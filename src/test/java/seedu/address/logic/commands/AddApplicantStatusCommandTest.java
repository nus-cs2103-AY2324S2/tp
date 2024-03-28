package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

public class AddApplicantStatusCommandTest {
    private static final String APPLICANT_STATUS_STUB = "resume review";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addStatusUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatus(APPLICANT_STATUS_STUB).build_applicant();
        AddApplicantStatusCommand addApplicantStatusCommand = new AddApplicantStatusCommand(firstPerson.getPhone(),
                new ApplicantStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = String.format(AddApplicantStatusCommand.MESSAGE_ADD_STATUS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addApplicantStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withStatus(APPLICANT_STATUS_STUB).build_applicant();

        AddApplicantStatusCommand addApplicantStatusCommand = new AddApplicantStatusCommand(firstPerson.getPhone(),
                new ApplicantStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = String.format(AddApplicantStatusCommand.MESSAGE_ADD_STATUS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addApplicantStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonPhoneUnfilteredList_failure() {
        AddApplicantStatusCommand addApplicantStatusCommand = new AddApplicantStatusCommand(new Phone("111"),
                new ApplicantStatus(APPLICANT_STATUS_STUB));

        assertCommandFailure(addApplicantStatusCommand, model, Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER);
    }

    @Test
    public void equals() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        final AddApplicantStatusCommand standardCommand = new AddApplicantStatusCommand(firstPerson.getPhone(),
                new ApplicantStatus(APPLICANT_STATUS_STUB));

        // same values -> returns true
        AddApplicantStatusCommand commandWithSameValues = new AddApplicantStatusCommand(firstPerson.getPhone(),
                new ApplicantStatus(APPLICANT_STATUS_STUB));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new RemarkCommand(INDEX_SECOND_PERSON,
                new Remark(VALID_REMARK_AMY)));

        // different remark -> returns false
        assertNotEquals(standardCommand, new RemarkCommand(INDEX_FIRST_PERSON,
                new Remark(VALID_REMARK_BOB)));
    }
}
