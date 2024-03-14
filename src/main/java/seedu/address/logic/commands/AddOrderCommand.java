package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

import java.util.Optional;

public class AddOrderCommand extends Command{
    public static final String COMMAND_WORD = "order";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_ADD_ORDER_SUCCESS = "Added order to Person: %1$s";
    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Removed order from Person: %1$s";

    private final Phone phone;
    private Order order;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the order of the person identified "
            + "by the phone number of person. "
            + "Existing orders will be overwritten by the input.\n"
            + "Parameters: phone number (must be a positive integer) "
            + "p/ [PHONE_NUMBER]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "p/ 87438807.";

    public AddOrderCommand(Phone phone) {
        requireAllNonNull(phone);

        this.phone = phone;
        this.order = new Order();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!Phone.isValidPhone(phone.toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PHONE_NUMBER);
        }

        // To Check if phone number belongs to existing person
        Optional<Person> maybeEditablePerson = model.findPersonByPhoneNumber(this.phone.value);
        if (!maybeEditablePerson.isPresent()) {
            throw new CommandException(Messages.MESSAGE_PHONE_NUMBER_NOT_FOUND);
        }
        Person personToEdit = maybeEditablePerson.get();
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags());

        // CHANGE THIS!!!! Need add functionality for users to input products
        // THIS IS A PLACEHOLDER
        this.order.addProduct(new Product("cupcake"), new Quantity(2));

        editedPerson.addOrder(this.order);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person personToEdit) {
        String message = !order.isEmpty() ? MESSAGE_ADD_ORDER_SUCCESS : MESSAGE_DELETE_ORDER_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }
        AddOrderCommand e = (AddOrderCommand) other;
        return phone == e.phone;
    }
}
