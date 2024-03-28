package seedu.hirehub.logic.parser;

import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import seedu.hirehub.logic.commands.AddApplicationCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.status.Status;

/**
 * Parses input arguments and creates a new ApplicationCommand object
 */
public class AddApplicationCommandParser implements Parser<AddApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ApplicationCommand
     * and returns an ApplicationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddApplicationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EMAIL,
                PREFIX_TITLE, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_TITLE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EMAIL, PREFIX_TITLE, PREFIX_STATUS);
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            return new AddApplicationCommand(email, title, status);
        }

        return new AddApplicationCommand(email, title);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
