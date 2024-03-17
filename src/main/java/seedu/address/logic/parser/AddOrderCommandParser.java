package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.orders.AddOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Remark;
import seedu.address.model.order.Status;

/**
 * Parses input arguments and creates a new AddOrderCommand object.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderCommand
     * and returns an AddOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DETAILS, PREFIX_BY, PREFIX_PRICE);

        OrderId orderId = new OrderId();
        OrderDate orderDate = new OrderDate(getCurrentTime());
        Deadline deadline = new Deadline(argMultimap.getValue(PREFIX_BY).toString());
        Remark remark = new Remark(argMultimap.getValue(PREFIX_DETAILS).orElse(""));
        Amount amount = new Amount(argMultimap.getValue(PREFIX_PRICE).orElse("0"));
        Status status = new Status("Processing");

        Order order = new Order(orderId, orderDate, deadline, amount, remark, status);
        return new AddOrderCommand(order);
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return now.format(formatter);
    }

}
