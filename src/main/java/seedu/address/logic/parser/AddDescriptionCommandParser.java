package seedu.address.logic.parser;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddDescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Description;

import static java.util.Objects.requireNonNull;

public class AddDescriptionCommandParser implements Parser<AddDescriptionCommand> {
    public AddDescriptionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDescriptionCommand.MESSAGE_USAGE), ive);
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");

        return new AddDescriptionCommand(index, new Description(description));
    }
}
