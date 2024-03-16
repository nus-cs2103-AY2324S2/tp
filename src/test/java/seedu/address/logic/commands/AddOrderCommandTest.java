package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.orders.Order;
import seedu.address.testutil.PersonBuilder;

public class AddOrderCommandTest {

    @Test
    public void constructor_nullNameContainsKeywordsPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new AddOrderCommand(null, new Order("testitem")));
    }

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new AddOrderCommand(
                        new NameContainsKeywordsPredicate(Collections.singletonList("test")), null));
    }

    @Test
    public void execute_personNotFoundInFilteredPersonList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        List<String> predicate = Collections.singletonList("benson");
        NameContainsKeywordsPredicate bensonPredicate = new NameContainsKeywordsPredicate(predicate);
        Order orderToBeAdded = new Order("Kaya Toast x 1", LocalDateTime.parse("2024-01-01T07:00:00"));

        AddOrderCommand addOrderToSecondTypicalPerson = new AddOrderCommand(bensonPredicate, orderToBeAdded);

        Person bensonWithAddedOrder = new PersonBuilder(BENSON).withOrders(
                "Cookies x 5@2012-03-21T07:45:01",
                "Cupcake x 2@2014-12-11T13:01:02",
                "Kaya Toast x 1@2024-01-01T07:00:00").build();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(BENSON, bensonWithAddedOrder);

        assertCommandSuccess(addOrderToSecondTypicalPerson, model,
                "Added order to Person: Benson Meier", expectedModel);
    }

    @Test
    public void execute_personFoundInFilteredPersonList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        List<String> predicate = Collections.singletonList("nobody");
        NameContainsKeywordsPredicate nobodyPredicate = new NameContainsKeywordsPredicate(predicate);
        Order orderToBeAdded = new Order("Kaya Toast x 1", LocalDateTime.parse("2024-01-01T07:00:00"));

        AddOrderCommand addOrderToSecondTypicalPerson = new AddOrderCommand(nobodyPredicate, orderToBeAdded);

        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND,
                () -> addOrderToSecondTypicalPerson.execute(model));
    }

    @Test
    public void generateSuccessMessage_properPersonPassedIn_success() {
        Person personPassedIn = ALICE;
        String expectedMessage = "Added order to Person: Alice Pauline";
        assertEquals(AddOrderCommand.generateSuccessMessage(personPassedIn), expectedMessage);
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate johnny1 = new NameContainsKeywordsPredicate(Collections.singletonList("Johnny"));
        NameContainsKeywordsPredicate johnny2 = new NameContainsKeywordsPredicate(Collections.singletonList("Johnny"));
        NameContainsKeywordsPredicate walker = new NameContainsKeywordsPredicate(Collections.singletonList("Walker"));
        Order kayatoast1 = new Order("Kaya Toast x 1", LocalDateTime.parse("2024-01-01T07:00:00"));
        Order kayatoast2 = new Order("Kaya Toast x 1", LocalDateTime.parse("2024-01-01T07:00:00"));
        Order cookies = new Order("Cookies x 5", LocalDateTime.parse("2012-03-21T07:45:01"));

        AddOrderCommand addJohnnyKayatoastCommand1 = new AddOrderCommand(johnny1, kayatoast1);
        AddOrderCommand addJohnnyKayatoastCommand2 = new AddOrderCommand(johnny2, kayatoast2);
        AddOrderCommand addJohnnyCookiesCommand = new AddOrderCommand(johnny1, cookies);
        AddOrderCommand addWalkerCookiesCommand = new AddOrderCommand(walker, cookies);

        // same object -> returns true
        assertTrue(addJohnnyKayatoastCommand1.equals(addJohnnyKayatoastCommand1));

        // same items and orderDateTime -> returns true
        assertTrue(addJohnnyKayatoastCommand1.equals(addJohnnyKayatoastCommand2));

        // different types -> returns false
        assertFalse(addJohnnyKayatoastCommand1.equals(1));

        // null -> returns false
        assertFalse(addJohnnyKayatoastCommand1.equals(null));

        // different personNamePredicate -> returns false
        assertFalse(addJohnnyCookiesCommand.equals(addWalkerCookiesCommand));

        // different items -> returns false
        assertFalse(addJohnnyKayatoastCommand1.equals(addJohnnyCookiesCommand));
    }

}
