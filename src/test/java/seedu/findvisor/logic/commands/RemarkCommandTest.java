package seedu.findvisor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.commands.CommandTestUtil.REMARK;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.findvisor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.findvisor.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.logic.parser.ParserUtil;
import seedu.findvisor.model.AddressBook;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.ModelManager;
import seedu.findvisor.model.UserPrefs;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.Remark;
import seedu.findvisor.testutil.PersonBuilder;

public class RemarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemarkCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_nonEmptyRemark_updateRemarkSuccess() {
        Optional<Remark> remark = ParserUtil.parseRemark(REMARK);
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, remark);

        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(targetPerson);
        Person editedPerson = personInList.withRemark(remark).build();

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                targetPerson.getName(), REMARK);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyRemark_removeRemarkSuccess() {
        Optional<Remark> remark = ParserUtil.parseRemark("");
        // Second person has a remark
        RemarkCommand remarkCommand = new RemarkCommand(INDEX_SECOND_PERSON, remark);

        Person targetPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        PersonBuilder personInList = new PersonBuilder(targetPerson);
        Person editedPerson = personInList.withRemark(remark).build();

        String expectedMessage = String.format(RemarkCommand.MESSAGE_REMOVE_REMARK_SUCCESS, targetPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Optional<Remark> remark = ParserUtil.parseRemark(REMARK);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, remark);

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_PERSON, ParserUtil.parseRemark(REMARK));

        // same values -> returns true
        Optional<Remark> remark = ParserUtil.parseRemark(REMARK);
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_PERSON, remark);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_PERSON, remark)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_PERSON, ParserUtil.parseRemark("Different"))));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Optional<Remark> remark = ParserUtil.parseRemark(REMARK);
        RemarkCommand remarkCommand = new RemarkCommand(index, remark);
        String expected = RemarkCommand.class.getCanonicalName() + "{index=" + index + ", remark="
                + remark.map(r -> r.value).orElse("") + "}";
        assertEquals(expected, remarkCommand.toString());
    }
}
