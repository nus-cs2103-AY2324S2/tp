package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditOrderCommandParser implements Parser<EditOrderCommand> {
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDER_ID, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ORDER_ID).orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCustomerCommand.MESSAGE_USAGE), pe);
        }

        EditOrderCommand.EditOrderDescriptor editOrderDescriptor = new EditOrderCommand.EditOrderDescriptor();

        if (argMultimap.getValue(PREFIX_PRODUCT_NAME).isPresent()) {
            editOrderDescriptor.setProduct(ParserUtil.parseProduct(argMultimap.getValue(PREFIX_PRODUCT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).isPresent()) {
            editOrderDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).get()));
        }

        if (!editOrderDescriptor.isAllFieldsEdited()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
    }
}
