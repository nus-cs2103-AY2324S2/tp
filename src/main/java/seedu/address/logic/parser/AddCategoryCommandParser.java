package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.stream.Stream;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EntryList;

/**
 * Parser class for addcategorycommand
 */
public class AddCategoryCommandParser implements Parser<AddCategoryCommand> {
    /**
     * Creates the addcategorycommand
     * @param args our arguments
     * @return the command
     * @throws ParseException if no index
     */
    public AddCategoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, AddCategoryCommand.MESSAGE_USAGE), pe);
        }

        List<String> categories = argMultimap.getAllValues(PREFIX_CATEGORY);
        List<String> descriptions = argMultimap.getAllValues(PREFIX_DESCRIPTION);
        if (categories.size() == 0 || descriptions.size() == 0) {
            throw new ParseException(AddCategoryCommand.ENTRY_NOT_ADDED);
        }

        if (categories.size() != descriptions.size()) {
            throw new ParseException(AddCategoryCommand.DIFFERENT_NUMBER_CATEGORIES_DESCRIPTIONS);
        }

        EntryList entrylist = ParserUtil.parseEntries(categories, descriptions);
        return new AddCategoryCommand(index, entrylist);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

