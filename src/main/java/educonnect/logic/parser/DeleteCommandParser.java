package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.stream.Stream;

import educonnect.logic.commands.DeleteCommand;
import educonnect.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_TAG);

        // No prefixes used
        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                PREFIX_TELEGRAM_HANDLE, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        }

        // Non-unique identifiers present
        if (areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.NO_UNIQUE_IDENTIFIER_MESSAGE));
        }

        // Multiple unique identifiers present
        if (arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL)
            || arePrefixesPresent(argMultimap, PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL)
            || arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_EMAIL)
            || arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_TELEGRAM_HANDLE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MULTIPLE_UNIQUE_IDENTIFIER_MESSAGE));
        }

        DeleteCommand.DeleteStudentDescriptor deletePersonDescriptor = new DeleteCommand.DeleteStudentDescriptor();

        // Find the unique identifier used only one of the guard clause will be triggered
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            deletePersonDescriptor.setStudentId(
                    ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            deletePersonDescriptor.setTelegramHandle(
                    ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            deletePersonDescriptor.setEmail(
                    ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        return new DeleteCommand(deletePersonDescriptor);

    }

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
