package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.exceptions.IllegalValueException;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.AddProductCommand;
import static seedu.address.logic.parser.CliSyntax.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

import java.util.stream.Stream;


public class AddProductCommandParser {
    /**
     * Parses the user input to create a AddOrderCommand
     * @param args user input.
     * @return AddOrderCommand based on the user input.
     * @throws ParseException
     */
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
        }

        Product product;
        Quantity quantity;
        try {
            product = ParserUtil.parseProduct(argMultimap.getValue(PREFIX_PRODUCT_NAME).get());
            quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddOrderCommand.MESSAGE_USAGE), ive);
        }

        return new AddProductCommand(product, quantity);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
