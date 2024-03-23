package seedu.address.logic.commands.orders;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderId;
import seedu.address.model.person.Person;


/**
 * Removes an existing order in the address book.
 */
public class DeleteOrderCommand extends Command {
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_SUCCESS = "Deleted Order: %1$s";

    public static final String COMMAND_WORD = "deleteOrder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            // TODO change this to use index rather than UUID
            + "Example: " + COMMAND_WORD + " id/<UUID>";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order!";

    private final Index targetIndex;


    /**
     * Creates an DeleteOrderCommand to delete the specified {@code Order}.
     */
    public DeleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Order orderToDelete = lastShownList.get(targetIndex.getZeroBased());
        deleteOrderHelper(model, orderToDelete);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, Messages.format(orderToDelete)
                )
        );
    }

    private void deleteOrderHelper(Model model, Order orderToDelete) throws CommandException {
        List<Person> personList = model.getAddressBook().getPersonList();
        for (Person person : personList) {
            if (person.getOrders().contains(orderToDelete)) {
                Person editedPerson = new Person(
                        person.getName(), person.getPhone(), person.getEmail(),
                        person.getAddress(), person.getTags(),
                        removeOrder(orderToDelete.getOrderId(), person.getOrders()));
                model.setPerson(person, editedPerson);
                model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
                break;
            }
        }
    }

    private Set<Order> removeOrder(OrderId orderId, Set<Order> orders) {
        orders = new HashSet<>(orders);
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order currentOrder = iterator.next();
            if (currentOrder.getOrderId().equals(orderId)) {
                iterator.remove();
                break;
            }
        }
        return orders;
    }

}
