package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.person.IsSameIdPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand or ViewCommand object depending on the arguments.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

  /*
  public Command parse(String arguments) throws ParseException {
        if (arguments.trim().equals("-all")) {
            return new ListCommand();
        } else if (arguments.trim().equals("-statistics")) {
            return new ViewCommand();
        } else if (arguments.trim().equals("-stats")) {
            return new ViewCommand();
        } else {
            throw new ParseException("Invalid arguments for 'view' command");
        }
    }*/
    
    // TEMPORARY - Move to CliSyntax
    public static final PREFIX_STATS = "-stats";
    public static final PREFIX_STATSLONG = "-statistics";
    public static final PREFIX_ALL = "-all";
    // End of temporary
    
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_STATS, PREFIX_STATSLONG, PREFIX_ALL);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_ID)
                && !arePrefixesPresent(argMultimap, PREFIX_STATS)
                && !arePrefixesPresent(argMultimap, PREFIX_STATSLONG)
                && !arePrefixesPresent(argMultimap, PREFIX_ALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
      
        if (arePrefixPresent(argMultimap, PREFIX_ALL) {
            return new ListCommand();
        } else if (arePrefixPresent(argMultimap, PREFIX_STATS) || arePrefixPresent(argMultimap, PREFIX_STATSLONG)) {
            return new ViewCommand();
        }
            
        Name name = null;
        try {
            if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            }
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        Id id = null;
        try {
            if (arePrefixesPresent(argMultimap, PREFIX_ID)) {
                id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
            }
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
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
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            return new ViewCommand(new IsSameIdPredicate(id));
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
