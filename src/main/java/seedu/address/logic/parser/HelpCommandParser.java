package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_HELP;

import java.util.stream.Stream;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_HELP);
        String commandType;
        if (!arePrefixesPresent(argMultimap, PREFIX_HELP) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(HelpMessages.MESSAGE_HELP_MISSING_COMMAND,
                    HelpCommand.MESSAGE_USAGE));
        }
        commandType = ParserUtil.parseHelp(argMultimap.getValue(PREFIX_HELP).get());
        return new HelpCommand(commandType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
