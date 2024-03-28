package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONEY_OWED_FOR_SPLIT_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONEY_OWED_FOR_SPLIT_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.Person;

class SplitCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void getSplitAmount_with_validArgs() {
        Float totalAmount = (float) 10.20;
        int numPeople = 2;
        Float expectedAmount = (float) 5.10;
        assertEquals(SplitCommand.getSplitAmount(totalAmount, numPeople), expectedAmount);
    }
    @Test
    public void execute_withInvalidSplitAmount_throwsCommandException() {
        List<Index> indexList = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        MoneyOwed totalOwed = new MoneyOwed(INVALID_MONEY_OWED_FOR_SPLIT_COMMAND);
        SplitCommand splitCommand = new SplitCommand(indexList, totalOwed);
        String expectedMessage = SplitCommand.MESSAGE_INVALID_AMOUNT;
        assertCommandFailure(splitCommand, model, expectedMessage);
    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        List<Index> indexList = Arrays.asList(invalidIndex, INDEX_SECOND_PERSON);
        MoneyOwed totalOwed = new MoneyOwed(VALID_MONEY_OWED_FOR_SPLIT_COMMAND);
        SplitCommand splitCommand = new SplitCommand(indexList, totalOwed);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(splitCommand, model, expectedMessage);
    }

    @Test
    public void execute_splitBetweenTwoUnfilteredList_success() {
        // Creating splitCommand
        List<Index> indexList = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        MoneyOwed totalOwed = new MoneyOwed(VALID_MONEY_OWED_FOR_SPLIT_COMMAND);
        SplitCommand splitCommand = new SplitCommand(indexList, totalOwed);
        // Getting split amount for this split command
        Float splitAmount = SplitCommand.getSplitAmount(totalOwed.getAmount(), indexList.size());
        String expectedMessage = String.format("$" + VALID_MONEY_OWED_FOR_SPLIT_COMMAND
                        + " has been split among " + indexList.size() + " people!");
        // Get the expected model for this split command
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person first = model.getFilteredPersonList().get(0);
        Person second = model.getFilteredPersonList().get(1);
        Person expectedFirst = new Person(
                first.getName(), first.getPhone(), first.getEmail(),
                first.getAddress(), first.getRemark(), first.getTags(),
                first.getBirthday(), first.getMoneyOwed().addAmountOwed(splitAmount));
        Person expectedSecond = new Person(
                second.getName(), second.getPhone(), second.getEmail(),
                second.getAddress(), second.getRemark(), second.getTags(),
                second.getBirthday(), second.getMoneyOwed().addAmountOwed(splitAmount));
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedFirst);
        expectedModel.setPerson(model.getFilteredPersonList().get(1), expectedSecond);
        assertCommandSuccess(splitCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        List<Index> indexList = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        MoneyOwed totalOwed = new MoneyOwed(VALID_MONEY_OWED_FOR_SPLIT_COMMAND);
        final SplitCommand standardCommand = new SplitCommand(indexList, totalOwed);

        List<Index> differentIndexList = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON);
        MoneyOwed differentTotalOwed = new MoneyOwed("40.30");

        // same values -> returns true
        SplitCommand commandWithSameValues = new SplitCommand(indexList, totalOwed);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types of command -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index list -> returns false
        assertFalse(standardCommand.equals(new SplitCommand(differentIndexList, totalOwed)));

        // different MoneyOwed -> returns false
        assertFalse(standardCommand.equals(new SplitCommand(indexList, differentTotalOwed)));
    }

    @Test
    public void toStringMethod() {
        List<Index> indexList = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        MoneyOwed totalOwed = new MoneyOwed(VALID_MONEY_OWED_FOR_SPLIT_COMMAND);
        SplitCommand splitCommand = new SplitCommand(indexList, totalOwed);
        String expected = SplitCommand.class.getCanonicalName() + "{indexListToSplit=" + indexList
                + ", totalOwed="
                + totalOwed + "}";
        assertEquals(expected, splitCommand.toString());
    }
}
