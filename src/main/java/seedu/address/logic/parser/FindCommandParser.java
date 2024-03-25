package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.startup.FundingStageContainsKeywordsPredicate;
import seedu.address.model.startup.IndustryContainsKeywordsPredicate;
import seedu.address.model.startup.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        FindCommand findCommand = null;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_INDUSTRY,
                        CliSyntax.PREFIX_FUNDING_STAGE
                );

        String[] nameKeywords = new String[0];
        String[] industryKeywords = new String[0];
        String[] fundingStageKeywords = new String[0];
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            nameKeywords = argMultimap.getValue(CliSyntax.PREFIX_NAME).get().split("\\s+");
            findCommand = new FindCommand(new NameContainsKeywordsPredicate((Arrays.asList(nameKeywords))));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).isPresent()) {
            industryKeywords = argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).get().split("\\s+");
            findCommand = new FindCommand(new IndustryContainsKeywordsPredicate((Arrays.asList(industryKeywords))));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_FUNDING_STAGE).isPresent()) {
            fundingStageKeywords = argMultimap.getValue(CliSyntax.PREFIX_FUNDING_STAGE).get().split("\\s+");
            findCommand = new FindCommand(
                    new FundingStageContainsKeywordsPredicate((Arrays.asList(fundingStageKeywords))));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE));
        }

        return findCommand;
    }

}
