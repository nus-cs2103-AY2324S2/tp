package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_HEAD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssignPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AssignCommand.
 */
public class AssignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_assignRole_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person assignedPerson = personInList.withTags(VALID_ROLE_HEAD).build();
        AssignCommand.AssignPersonDescriptor descriptor = new AssignPersonDescriptorBuilder(VALID_ROLE_HEAD).build();
        AssignCommand assignCommand = new AssignCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_PERSON_SUCCESS,
                Messages.format(assignedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, assignedPerson);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AssignCommand.AssignPersonDescriptor descriptor = new AssignPersonDescriptorBuilder(VALID_ROLE_HEAD).build();
        AssignCommand assignCommand = new AssignCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


}
