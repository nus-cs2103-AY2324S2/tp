package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;


import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CreateEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;


public class CreateEventCommandParser implements Parser<CreateEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateEventCommand
     * and returns an CreateEventComand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateEventCommand parse(String args) throws ParseException {
         ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_NAME);

         if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateEventCommand.MESSAGE_USAGE));
        }
        EventName eventName =  ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT_NAME).get());

        Event event = new Event(eventName);

        return new CreateEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
