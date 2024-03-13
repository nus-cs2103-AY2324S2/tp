package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.exceptions.IllegalValueException;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.commands.AddOrderCommand;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;


public class AddOrderCommandParser {
    public AddOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_PHONE);

        Phone phone;
        try {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddOrderCommand.MESSAGE_USAGE), ive);
        }

        return new AddOrderCommand(phone);
    }
}
