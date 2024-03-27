package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORT;

import java.nio.file.Path;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ImportExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ImportExamCommand} object
 */
public class ImportExamCommandParser implements Parser<ImportExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ImportExamCommand}
     * @param args the arguments to be parsed
     * @return a new {@code ImportExamCommand} object
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportExamCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IMPORT);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_IMPORT);
        if (!isPrefixPresent(
                argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportExamCommand.MESSAGE_USAGE));
        }

        Path path = ParserUtil.parseFilePath(argMultimap.getValue(PREFIX_IMPORT).orElse(""));
        if (!isCsvFile(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
        return new ImportExamCommand(path);
    }

    /**
     * Returns true if the given file is a CSV file.
     * @param path the path of the file
     * @return true if the file is a CSV file
     */
    private static boolean isCsvFile(Path path) {
        return path.toString().endsWith(".csv");
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(CliSyntax.PREFIX_IMPORT).isPresent();
    }
}
