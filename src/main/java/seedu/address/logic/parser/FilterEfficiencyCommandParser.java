package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterEfficiencyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Efficiency;
import seedu.address.model.person.PersonLessThanEfficiencyPredicate;


/**
 * Parses input arguments and creates a new FilterEfficiencyCommand object
 */
public class FilterEfficiencyCommandParser implements Parser<FilterEfficiencyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterEfficiencyCommand
     * and returns a FilterEfficiencyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterEfficiencyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        //check if it is an empty input
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEfficiencyCommand.MESSAGE_USAGE));
        }

        try {
            //check if the input is an integer
            int threshold = Integer.parseInt(trimmedArgs);

            //check if the input is in the range (0-100)
            if (!Efficiency.isValidEfficiency(trimmedArgs)) {
                throw new ParseException(Efficiency.MESSAGE_CONSTRAINTS);
            }
            return new FilterEfficiencyCommand(new PersonLessThanEfficiencyPredicate(threshold));

        } catch (NumberFormatException e) {
            throw new ParseException("The input efficiency should be an integer.");
        }
    }
}
