package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

/**
 * Parses input arguments and creates a new AddExamCommand object
 */
public class AddExamCommandParser implements Parser<AddExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddExamCommand
     * and returns an AddExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SCORE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SCORE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_SCORE);

        String name = ParserUtil.parseExamName(argMultimap.getValue(PREFIX_NAME).get());
        Score maxScore = ParserUtil.parseExamScore(argMultimap.getValue(PREFIX_SCORE).get());

        Exam exam = new Exam(name, maxScore);

        return new AddExamCommand(exam);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
