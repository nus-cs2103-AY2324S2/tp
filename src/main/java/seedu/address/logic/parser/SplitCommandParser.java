package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SplitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MoneyOwed;

/**
 * Parses input arguments and creates a new SplitCommand object
 */
public class SplitCommandParser implements Parser<SplitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SplitCommand
     * and returns a SplitCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SplitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MONEY_OWED);
        List<Index> indexList = new ArrayList<>();
        MoneyOwed totalOwed;
        try {
            String[] indexArray = argMultimap.getPreamble().split(" ");
            for (String s : indexArray) {
                indexList.add(ParserUtil.parseIndex(s));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_MONEY_OWED).isPresent()) {
            totalOwed = ParserUtil.parseMoneyOwed(argMultimap.getValue(PREFIX_MONEY_OWED).get());
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitCommand.MESSAGE_MISSING_AMOUNT));
        }
        return new SplitCommand(indexList, totalOwed);
    }

}
