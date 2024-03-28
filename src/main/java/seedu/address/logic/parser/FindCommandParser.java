package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.predicates.BoltWithinBoundsPredicate;
import seedu.address.model.student.predicates.MajorContainsSubStringPredicate;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicates.StarWithinBoundsPredicate;
import seedu.address.model.student.predicates.TagContainsSubStringPredicate;

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
        try {
            String trimmedArgs = args.trim();
            String[] splitArgs = trimmedArgs.split(" ", 2);
            String field = splitArgs[0];
            String argsToMatch = splitArgs[1].trim();

            switch (field) {
            case "name":
                String[] nameKeywords = argsToMatch.split("\\s+");
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

            case "major":
                String mSubString = argsToMatch.trim();
                return new FindCommand(new MajorContainsSubStringPredicate(mSubString));

            case "star":
                String[] starArgs = argsToMatch.split(" ", 2);
                String sOperator = starArgs[0];
                Integer sOperand = Integer.parseInt(starArgs[1].trim());

                if (isInvalidOperator(sOperator)) {
                    throw new ParseException("Invalid operator.");
                }

                return new FindCommand(new StarWithinBoundsPredicate(sOperator, sOperand));

            case "bolt":
                String[] boltArgs = argsToMatch.split(" ", 2);
                String bOperator = boltArgs[0];
                Integer bOperand = Integer.parseInt(boltArgs[1].trim());

                if (isInvalidOperator(bOperator)) {
                    throw new ParseException("Invalid operator.");
                }

                return new FindCommand(new BoltWithinBoundsPredicate(bOperator, bOperand));

            case "tag":
                String tSubString = argsToMatch.trim();
                return new FindCommand(new TagContainsSubStringPredicate(tSubString));

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks if the operator is valid.
     *
     * @param operator
     * @return
     */
    public boolean isInvalidOperator(String operator) {
        return !operator.equals("<")
                && !operator.equals(">")
                && !operator.equals("<=")
                && !operator.equals(">=")
                && !operator.equals("=");
    }

}
