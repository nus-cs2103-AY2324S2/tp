package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CombinedPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PolicyContainsKeywordsPredicate;
import seedu.address.model.person.RelationshipContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */


    @Override
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RELATIONSHIP,
                PREFIX_TAG, PREFIX_POLICY);


        if (args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }


        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        List<String> relationshipKeywords = argMultimap.getAllValues(PREFIX_RELATIONSHIP);
        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        List<String> policyKeywords = argMultimap.getAllValues(PREFIX_POLICY);

        CombinedPredicate combinedPredicate =
                new CombinedPredicate(
                        new NameContainsKeywordsPredicate(nameKeywords),
                        new RelationshipContainsKeywordsPredicate(relationshipKeywords),
                        new TagContainsKeywordsPredicate(tagKeywords),
                        new PolicyContainsKeywordsPredicate(policyKeywords));

        return new FindCommand(combinedPredicate);
    }
}

