package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import vitalconnect.logic.commands.DeleteMedicalCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.person.identificationinformation.Nric;

/**
 * Parses user input into a {@code DeleteMedicalCommand}.
 * This class ensures that the user input contains the necessary prefix for deleting medical information.
 */
public class DeleteMedicalCommandParser implements Parser<DeleteMedicalCommand> {
    /**
     * Parses the user input and returns a {@code DeleteMedicalCommand} if the input is valid.
     * @param userInput The user input string.
     * @return A {@code DeleteMedicalCommand} representing the parsed user input.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public DeleteMedicalCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NRIC);
        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMedicalCommand.MESSAGE_USAGE));
        }
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        return new DeleteMedicalCommand(nric);
    }

    /**
     * Checks if all the specified prefixes are present and non-empty in the given {@code ArgumentMultimap}.
     * @param argumentMultimap The {@code ArgumentMultimap} containing the parsed arguments.
     * @param prefixes The prefixes to check.
     * @return {@code true} if all prefixes are present and non-empty, {@code false} otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
