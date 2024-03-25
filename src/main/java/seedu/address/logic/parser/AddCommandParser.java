package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Entry;
import seedu.address.model.person.EntryList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        boolean hasEntry = true;
        EntryList entryList = new EntryList();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_CATEGORY, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).get().isEmpty()) {
            throw new ParseException("Name cannot be empty!");
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY) && !arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            hasEntry = false;
        } else if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                && arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            throw new ParseException("Category cannot be empty!");
        } else if (arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                && !arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            throw new ParseException("Description cannot be empty!");
        } else {
            String[] category = null;
            String[] description = null;
            if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
                String categoryString = argMultimap.getValue(PREFIX_CATEGORY).get().trim();
                if (!categoryString.isEmpty()) {
                    category = categoryString.split(",");
                } else {
                    throw new ParseException(AddCategoryCommand.ENTRY_NOT_ADDED);
                }
            }
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                String descriptionString = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
                if (!descriptionString.isEmpty()) {
                    description = descriptionString.split(",");
                } else {
                    throw new ParseException(AddCategoryCommand.ENTRY_NOT_ADDED);
                }
            }

            if (category != null && description != null) {
                if (category.length > description.length) {
                    throw new ParseException("Not enough description input.");
                } else if (category.length < description.length) {
                    throw new ParseException("Not enough category input.");
                } else {
                    for (int i = 0; i < category.length; i++) {
                        String catString = category[i].trim();
                        String desString = description[i].trim();
                        if (catString.isEmpty() || desString.isEmpty()) {
                            throw new ParseException(AddCategoryCommand.ENTRY_NOT_ADDED);
                        } else {
                            Entry entry = new Entry(catString, desString);
                            entryList.add(entry);
                        }
                    }
                }
            } else {
                throw new ParseException(AddCategoryCommand.ENTRY_NOT_ADDED);
            }
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);
        Entry name = ParserUtil.parse("Name", argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, tagList);
        if (hasEntry) {
            return new AddCommand(person, entryList);
        } else {
            return new AddCommand(person, null);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
