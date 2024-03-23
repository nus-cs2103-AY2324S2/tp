package scm.address.logic.parser;

import static scm.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static scm.address.logic.parser.CliSyntax.PREFIX_FILENAME;
import static scm.address.logic.parser.ParserUtil.parseFiles;

import java.io.File;
import java.util.Set;
import java.util.stream.Stream;

import scm.address.logic.commands.ImportCommand;
import scm.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {
    public static final String IMPORT_INVALID_COMMAND_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ImportCommand.MESSAGE_USAGE);
    /**
     * Parses a given String of args into an ImportCommand.
     *
     * @param args String to be parsed.
     * @return An ImportCommand containing the parsed information.
     * @throws ParseException If prefixes are not present or the preamble is empty.
     */
    public ImportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILENAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_FILENAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(IMPORT_INVALID_COMMAND_FORMAT);
        }
        Set<File> fileSet = parseFiles(argMultimap.getAllValues(PREFIX_FILENAME));
        return new ImportCommand(fileSet);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
