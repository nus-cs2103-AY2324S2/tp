package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import javax.swing.text.View;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        Name name = null;
        try {
            if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            }
        } catch (ParseException e) {
            System.out.println("aftername");
        }

        Id id = null;
        try {
            if (arePrefixesPresent(argMultimap, PREFIX_ID)) {
                id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
            }
        } catch (ParseException e) {
            System.out.println("after id");
        }
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (name == null && id == null) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        } else if (id == null) {
            return new ViewCommand(id);
        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
