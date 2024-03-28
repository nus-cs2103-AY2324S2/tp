package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAGE_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalApplicantBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.person.Person;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditApplicantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditApplicantCommandTest {

    private Model model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditApplicantCommand.EditApplicantDescriptor descriptor =
                new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                descriptor);

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditApplicantCommand.EditApplicantDescriptor());
        Person editedPerson =
                model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Applicant editedApplicant = new ApplicantBuilder(editedPerson).build();

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Applicant editedApplicant =
                new ApplicantBuilder(personInFilteredList).withRole(VALID_ROLE_CHLOE).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder().withRole(VALID_ROLE_CHLOE).build());

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedMessage, expectedModel);
    }



    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withStage(VALID_STAGE_CHLOE).build());

        assertCommandFailure(editApplicantCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


}
