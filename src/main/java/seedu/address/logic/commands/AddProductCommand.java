package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;

public class AddProductCommand extends Command{
    public static final String COMMAND_WORD = "product";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds products to the last created order "
            + "Parameters: "
            + PREFIX_PRODUCT_NAME + "PRODUCT NAME "
            + PREFIX_PRODUCT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRODUCT_NAME + "cake "
            + PREFIX_PRODUCT_QUANTITY + "2";
    private static Order lastOrder;

    private final Product product;
    private final Quantity quantity;

    public AddProductCommand(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static void setLastOrder(Order order) {
        lastOrder = order;
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
        AddProductCommand e = (AddProductCommand) other;
        return (product.equals(e.product) && quantity.equals(e.quantity));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (lastOrder == null) {
            throw new CommandException(Messages.MESSAGE_ORDER_NOT_CREATED);
        }
        //Add ability to add product to order
        if (lastOrder.getProductMap().containsKey(product)) {
            lastOrder.changeQuantity(product, lastOrder.getQuantityValue(product) + quantity.getValue());
        } else {
            lastOrder.addProduct(product, quantity);
        }

        return new CommandResult(generateSuccessMessage());
    }
    private String generateSuccessMessage() {
        String message = "Successfully added " + product.toString() + " to the order.";
        return message;
    }

}
