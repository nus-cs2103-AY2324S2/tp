package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonFieldsContainKeywordPredicate;


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
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        
        if (!argMultimap.getValue(PREFIX_NAME).isPresent() &&
            !argMultimap.getValue(PREFIX_PHONE).isPresent() &&
            !argMultimap.getValue(PREFIX_EMAIL).isPresent() &&
            !argMultimap.getValue(PREFIX_ADDRESS).isPresent() &&
            !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        
        
        List<String> combinedKeywords = new ArrayList<>();
        argMultimap.getValue(PREFIX_NAME).ifPresent(value -> combinedKeywords.addAll(Arrays.asList(value.split("\\s+"))));
        argMultimap.getValue(PREFIX_PHONE).ifPresent(value -> combinedKeywords.addAll(Arrays.asList(value.split("\\s+"))));
        argMultimap.getValue(PREFIX_EMAIL).ifPresent(value -> combinedKeywords.addAll(Arrays.asList(value.split("\\s+"))));
        argMultimap.getValue(PREFIX_ADDRESS).ifPresent(value -> combinedKeywords.addAll(Arrays.asList(value.split("\\s+"))));
        argMultimap.getValue(PREFIX_TAG).ifPresent(value -> combinedKeywords.addAll(Arrays.asList(value.split("\\s+"))));
    
        PersonFieldsContainKeywordPredicate predicate = new PersonFieldsContainKeywordPredicate(combinedKeywords);
    
        return new FindCommand(predicate);
    }

}
