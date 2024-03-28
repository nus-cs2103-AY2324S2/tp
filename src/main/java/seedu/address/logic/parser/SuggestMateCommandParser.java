package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import seedu.address.logic.commands.SuggestMateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;

/**
 * Parses input arguments and creates a new {@code SuggestMateCommand} object.
 */
public class SuggestMateCommandParser implements Parser<SuggestMateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SuggestMateCommand
     * and returns a SuggestMateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuggestMateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE);

        Name groupName = ParserUtil.parseName(argMultiMap.getPreamble());
        return new SuggestMateCommand(groupName);
    }
}
