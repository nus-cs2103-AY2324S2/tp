package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_CANNOT_DELETE_NAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parser class for deletecategorycommand
 */
public class DeleteCategoryCommandParser implements Parser<DeleteCategoryCommand> {
    /**
     * Creates the deletecategorycommand
     * @param args our arguments
     * @return the command
     * @throws ParseException if no index
     */
    @Override
    public DeleteCategoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCategoryCommand.MESSAGE_USAGE), pe);
        }

        String category = "";
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            category = argMultimap.getValue(PREFIX_CATEGORY).get();
        }

        if (category.equals("Name")) {
            throw new ParseException(MESSAGE_CANNOT_DELETE_NAME);
        }

        return new DeleteCategoryCommand(index, category);
    }
}
