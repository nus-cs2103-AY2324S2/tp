package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import educonnect.logic.commands.LinkCommand;
import educonnect.logic.parser.exceptions.ParseException;


public class LinkCommandParser implements Parser<LinkCommand> {
    public LinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID,
                        PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE, PREFIX_TAG, PREFIX_LINK);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE, PREFIX_LINK);

        // No prefixes used
        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG, PREFIX_STUDENT_ID, PREFIX_EMAIL,
                PREFIX_TELEGRAM_HANDLE, PREFIX_TAG, PREFIX_LINK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.MESSAGE_USAGE));
        }

        // No link provided
        if (!arePrefixesPresent(argMultimap, PREFIX_LINK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.NO_LINK_IDENTIFIER_MESSAGE));
        }

        // Non-unique identifiers present
        if (areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.NO_UNIQUE_IDENTIFIER_MESSAGE));
        }

        // Multiple unique identifiers present
        if (arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL)
                || arePrefixesPresent(argMultimap, PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL)
                || arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_EMAIL)
                || arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_TELEGRAM_HANDLE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.MULTIPLE_UNIQUE_IDENTIFIER_MESSAGE));
        }

        LinkCommand.LinkStudentDescriptor editPersonDescriptor = new LinkCommand.LinkStudentDescriptor();


        // Find the unique identifier used only one of the guard clause will be triggered
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editPersonDescriptor.setStudentId(
                    ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            editPersonDescriptor.setTelegramHandle(
                    ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(
                    ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
            editPersonDescriptor.setLink(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).get()));
        }

        if (areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.NO_UNIQUE_IDENTIFIER_MESSAGE));
        }

        return new LinkCommand(editPersonDescriptor);

    }
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }



}
