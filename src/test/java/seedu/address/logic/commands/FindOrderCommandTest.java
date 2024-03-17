package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.COOKIES_ONLY;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.MatchingOrderIndexPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderCommand}.
 */
public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        MatchingOrderIndexPredicate firstPredicate =
                new MatchingOrderIndexPredicate(Collections.singletonList("1"));
        MatchingOrderIndexPredicate secondPredicate =
                new MatchingOrderIndexPredicate(Collections.singletonList("2"));

        FindOrderCommand findOrderFirstCommand = new FindOrderCommand(firstPredicate);
        FindOrderCommand findOrderSecondCommand = new FindOrderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findOrderFirstCommand.equals(findOrderFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
        assertTrue(findOrderFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findOrderFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findOrderFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findOrderFirstCommand.equals(findOrderSecondCommand));
    }

    @Test
    public void execute_zeroOrderIndex_noOrderFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        MatchingOrderIndexPredicate predicate = prepareOrderPredicate(" ");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleOrderIndex_singleOrderFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        MatchingOrderIndexPredicate predicate = prepareOrderPredicate("1");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CUPCAKES_ONLY), model.getFilteredOrderList());
    }

    @Test
    public void execute_multipleOrderIndexes_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        MatchingOrderIndexPredicate predicate = prepareOrderPredicate("1 2");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CUPCAKES_ONLY, COOKIES_ONLY), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code MatchingOrderIndexPredicate}.
     */
    private MatchingOrderIndexPredicate prepareOrderPredicate(String userInput) {
        return new MatchingOrderIndexPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void toStringMethod() {
        MatchingOrderIndexPredicate predicate = new MatchingOrderIndexPredicate(Arrays.asList("1"));
        FindOrderCommand findOrderCommand = new FindOrderCommand(predicate);
        String expected = FindOrderCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findOrderCommand.toString());
    }
}
