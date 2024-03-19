package vitalconnect.logic.parser;

import vitalconnect.logic.commands.DeleteMedicalCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.person.identificationinformation.Nric;

import java.util.stream.Stream;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;

public class DeleteMedicalCommandParser implements Parser<DeleteMedicalCommand> {
    /**
     * @param userInput
     * @return
     * @throws ParseException
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
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
