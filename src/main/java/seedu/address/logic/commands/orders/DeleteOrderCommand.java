package seedu.address.logic.commands.orders;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
            + "Example: " + COMMAND_WORD + " id/<UUID>";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order!";

    private final OrderId index;


    /**
     * Creates an DeleteOrderCommand to delete the specified {@code Order}.
     */
    public DeleteOrderCommand(OrderId index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        Order changeOrder = null;
        Person personToEdit = null;
        List<Person> personList = model.getAddressBook().getPersonList();
        for (Person person : personList) {
            if (changeOrder != null) {
                break;
            }
            if (person.getOrders() != null) {
                Set<Order> orders = person.getOrders();
                for (Order order : orders) {
                    if (order.checkId(this.index)) {
                        changeOrder = order;
                        personToEdit = person;
                        break;
                    }
                }
            }
        }
        if (changeOrder == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(),
                removeOrder(this.index, personToEdit.getOrders()));
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(generateSuccessMessage(editedPerson));
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

    /**
     * Generates a command execution success message based on whether
     * the order is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_SUCCESS, personToEdit);
    }
}
