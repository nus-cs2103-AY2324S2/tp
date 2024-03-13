package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Date;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddOrderCommandTest {
    private static final Date DATE_STUB = new Date("2020-01-01");
    private static final String DESCRIPTION_STUB = "100 chicken wings";
    private static final ArrayList<Order> ORDERS_STUB = new ArrayList<>(
            List.of(new Order(new Date("2020-01-01"), "100 chicken wings")));

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, DATE_STUB, DESCRIPTION_STUB));
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(Index.fromOneBased(1),
                null, DESCRIPTION_STUB));
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(Index.fromOneBased(1),
                DATE_STUB, null));
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, null, null));
    }

    @Test
    public void execute_addOrder_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withOrderList(ORDERS_STUB).build();
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST_PERSON, DATE_STUB, DESCRIPTION_STUB);

        String expectedMessage = String.format(AddOrderCommand.MESSAGE_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddOrderCommand addOrderCommand = new AddOrderCommand(outOfBoundIndex, DATE_STUB, DESCRIPTION_STUB);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                addOrderCommand.execute(model));
    }

    @Test
    public void equals() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST_PERSON, DATE_STUB, DESCRIPTION_STUB);

        // same object -> returns true
        assert (addOrderCommand.equals(addOrderCommand));

        // same values -> returns true
        AddOrderCommand addOrderCommandCopy = new AddOrderCommand(INDEX_FIRST_PERSON, DATE_STUB, DESCRIPTION_STUB);
        assert (addOrderCommand.equals(addOrderCommandCopy));

        // different types -> returns false
        assert (!addOrderCommand.equals(1));

        // null -> returns false
        assert (!addOrderCommand.equals(null));

        // different person -> returns false
        AddOrderCommand differentOrderCommand = new AddOrderCommand(Index.fromOneBased(2), DATE_STUB, DESCRIPTION_STUB);
        assert (!addOrderCommand.equals(differentOrderCommand));
    }

    @Test
    public void toStringMethod() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST_PERSON, DATE_STUB, DESCRIPTION_STUB);
        String expected = AddOrderCommand.class.getCanonicalName() + "{index=" + INDEX_FIRST_PERSON
                + ", arrivalDate=" + DATE_STUB + ", description=" + DESCRIPTION_STUB + "}";
        assertEquals(expected, addOrderCommand.toString());
    }

}
