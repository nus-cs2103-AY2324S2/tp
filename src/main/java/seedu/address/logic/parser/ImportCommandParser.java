package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.address.logic.parser.ParserUtil.parseFiles;

import java.io.File;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser {

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
        Set<File> fileSet = parseFiles(argMultimap.getAllValues(PREFIX_FILENAME));
        return new ImportCommand(fileSet);
    }

    // Solution below adapted from
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
