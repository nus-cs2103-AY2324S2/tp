package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Role;
import seedu.address.model.applicant.Stage;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_STAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE) && (!arePrefixesPresent(argMultimap, PREFIX_STAGE)
                || !argMultimap.getPreamble().isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROLE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STAGE);
        Optional<Stage> filteredStage = Optional.empty();
        Optional<Role> filteredRole = Optional.empty();
        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE)) {
            filteredStage = Optional.ofNullable((ParserUtil.parseStage(argMultimap.getValue(PREFIX_STAGE).get())));
        } else if (!arePrefixesPresent(argMultimap, PREFIX_STAGE)) {
            filteredRole = Optional.ofNullable((ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get())));
        }

        return new FilterCommand(filteredRole, filteredStage);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
