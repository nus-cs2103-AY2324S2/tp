package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddCategoryCommandParser implements Parser<AddCategoryCommand>{
    public AddCategoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CATEGORY, PREFIX_DESCRIPTION);
        String category = "";
        String description = "";
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            category = argMultimap.getValue(PREFIX_CATEGORY).get();
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        }
        Entry entry = new Entry(category, description);
        return new AddCategoryCommand(index, entry);

    }

}
