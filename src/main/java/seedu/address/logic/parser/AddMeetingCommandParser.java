package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
    * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
    * and returns an AddMeetingCommand object for execution.
    * @throws ParseException if the user input does not conform the expected format
    */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_INDEX, PREFIX_DATETIME, PREFIX_DESCRIPTION);
        System.out.println(!arePrefixesPresent(argMultimap, PREFIX_CLIENT_INDEX, PREFIX_DATETIME, PREFIX_DESCRIPTION));
        System.out.println(!argMultimap.getPreamble().isEmpty());
        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_INDEX, PREFIX_DATETIME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLIENT_INDEX, PREFIX_DATETIME, PREFIX_DESCRIPTION);
        Index clientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).get());
        LocalDateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());


        return new AddMeetingCommand(dateTime, description, clientIndex);
    }

    /**
    * Returns true if none of the prefixes contains empty {@code Optional} values in the given
    * {@code ArgumentMultimap}.
    */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
