package seedu.hirehub.logic.parser;

import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_VACANCY;

import java.util.stream.Stream;

import seedu.hirehub.logic.commands.AddJobCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.job.Job;

/**
 * Parses input arguments and creates a new AddJobCommand object
 */
public class AddJobCommandParser implements Parser<AddJobCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddJobCommand
     * and returns an AddJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddJobCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE,
                PREFIX_DESCRIPTION, PREFIX_VACANCY);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_VACANCY) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_VACANCY);
        String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("").trim();
        int vacancy = ParserUtil.parseVacancy(argMultimap.getValue(PREFIX_VACANCY).get());
        Job job = new Job(title, description, vacancy);
        return new AddJobCommand(job);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
