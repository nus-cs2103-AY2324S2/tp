package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ORDER_ITEM_COOKIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_COOKIES;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.orders.Order;

public class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_nameAndOrderPresent_success() {
        NameContainsKeywordsPredicate expectedNamePred =
                new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Choo"));

        try {
            AddOrderCommand actualCommand = parser.parse(NAME_DESC_BOB + ORDER_ITEM_COOKIES);
            AddOrderCommand expectedCommand = new AddOrderCommand(expectedNamePred,
                    new Order(VALID_ORDER_COOKIES, actualCommand.order.orderDateTime));
            assertEquals(actualCommand, expectedCommand);
        } catch (ParseException pe) {
            fail("Invalid userInput");
        }

    }
}
