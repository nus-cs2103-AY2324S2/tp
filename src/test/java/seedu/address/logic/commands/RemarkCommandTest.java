package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.RemarkCommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class RemarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {

        Person targetPerson = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(targetPerson).withRemark("Updated Remark").build();

        RemarkCommand executeCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Updated Remark"));

        String expectedResult = String.format(executeCommand.MESSAGE_ADD_REMARK_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(executeCommand, model, expectedResult, expectedModel);

    }
}