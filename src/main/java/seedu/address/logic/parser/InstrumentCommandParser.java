package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.InstrumentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Instrument;

/**
 * Parses input arguments and creates a new InstrumentCommand object
 */
public class InstrumentCommandParser implements Parser<InstrumentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InstrumentCommand
     * and returns a InstrumentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InstrumentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_INSTRUMENT);

        Set<Index> indexes;
        try {
            indexes = ParserUtil.parseIndexes(List.of(argMultimap.getPreamble().split(" ")));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InstrumentCommand.MESSAGE_USAGE), ive);
        }


        String instrument = argMultimap.getValue(PREFIX_INSTRUMENT).orElse("");

        return new InstrumentCommand(indexes, new Instrument(instrument));
    }

}
