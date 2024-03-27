package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import vitalconnect.logic.commands.CreateAptCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.person.identificationinformation.Nric;

/**
 * Parses input arguments and creates a new CreateAptCommand object.
 * This parser is responsible for handling the raw input arguments provided
 * for the creation of an appointment and ensuring they meet the expected format
 * which includes the patient's NRIC and the appointment date and time.
 */
public class CreateAptCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateAptCommand
     * and returns a CreateAptCommand object for execution.
     * <p>
     * This method expects the arguments to contain the NRIC of a patient followed by
     * the '/time' keyword and the appointment datetime in 'dd/MM/yyyy HHmm' format.
     * If the arguments do not conform to this expected format, a ParseException is thrown.
     *
     * @param userInput The input arguments to be parsed, including the patient's NRIC and
     *             the appointment datetime.
     * @return A new CreateAptCommand object encapsulating the parsed patient NRIC
     *         and appointment datetime.
     * @throws ParseException If the provided arguments do not conform to the expected
     *                        format or if other parsing errors occur.
     */
    public CreateAptCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_NRIC, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC)
            || !arePrefixesPresent(argMultimap, PREFIX_TIME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateAptCommand.MESSAGE_USAGE));
        }

        Nric ic = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        LocalDateTime dateTimeStr = null;
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            dateTimeStr = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        }
        if (dateTimeStr == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateAptCommand.MESSAGE_USAGE));
        }

        return new CreateAptCommand(ic, dateTimeStr);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
