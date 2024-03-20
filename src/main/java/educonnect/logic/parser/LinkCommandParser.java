package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_LINK;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE;
import static java.util.Objects.requireNonNull;

import educonnect.logic.commands.LinkCommand;
import educonnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns a LinkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID,
                        PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE, PREFIX_TAG, PREFIX_LINK, PREFIX_TIMETABLE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE,
                PREFIX_LINK);

        // No prefixes used
        if (!argMultimap.areAnyPrefixesPresent(PREFIX_NAME, PREFIX_TAG, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                PREFIX_TELEGRAM_HANDLE, PREFIX_TAG, PREFIX_LINK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.MESSAGE_USAGE));
        }

        // Non-unique identifiers present
        if (argMultimap.getValue(PREFIX_NAME).isPresent()
                || argMultimap.getValue(PREFIX_TAG).isPresent()
                || argMultimap.getValue(PREFIX_TIMETABLE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.NON_UNIQUE_IDENTIFIER_MESSAGE));
        }

        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();

        // Parse unique identifiers
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            linkStudentDescriptor.setStudentId(
                    ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            linkStudentDescriptor.setTelegramHandle(
                    ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            linkStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        // Multiple unique identifiers present
        if (linkStudentDescriptor.countUniqueIdentifiers() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.MULTIPLE_UNIQUE_IDENTIFIER_MESSAGE));
        }
        // No unique identifiers present
        if (linkStudentDescriptor.countUniqueIdentifiers() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.NO_UNIQUE_IDENTIFIER_MESSAGE));
        }

        if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
            String link = argMultimap.getValue(PREFIX_LINK).get();
            if (!link.isBlank()) {
                linkStudentDescriptor.setLink(ParserUtil.parseLink(link));
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.NO_LINK_IDENTIFIER_MESSAGE));
        }

        return new LinkCommand(linkStudentDescriptor);

    }
}
