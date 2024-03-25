package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FILE;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.MigrateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.ImportManager;

/**
 * Parses inputs and creates a new MigrateCommand object.
 */
public class MigrateCommandParser implements Parser<MigrateCommand> {

    public static final String CSV_PATH = "imports/";
    public static final String PLACEHOLDER_DATA_PATH = "data/";


    @Override
    public MigrateCommand parse(String args) throws ParseException {
        String fileNameToImport = args.trim();
        if (fileNameToImport.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        Path pathToImportFrom = convertFileNameToCsvPath(fileNameToImport);
        Path placeholderPathToImportTo = convertFileNameToJsonPath(fileNameToImport);

        return new MigrateCommand(pathToImportFrom, new ImportManager(pathToImportFrom,
                placeholderPathToImportTo));
    }

    /**
     * Converts the filename into csv filepath to be imported from.
     *
     * @param fileName Name of file to be imported.
     * @return Path of csv file to be imported from.
     * @throws ParseException File does not exist.
     */
    private Path convertFileNameToCsvPath(String fileName) throws ParseException {
        Path csvFilePath = Paths.get(CSV_PATH + fileName + ".csv");
        if (!FileUtil.isFileExists(csvFilePath)) {
            throw new ParseException(String.format(MESSAGE_MISSING_FILE));
        }
        return csvFilePath;
    }

    /**
     * Converts the filename into json filepath to be imported to.
     *
     * @param fileName Name of file to be imported.
     * @return Path of json file to be imported to.
     */
    private Path convertFileNameToJsonPath(String fileName) {
        Path placeholderJsonFilePath = Paths.get(PLACEHOLDER_DATA_PATH + fileName + ".json");
        return placeholderJsonFilePath;
    }

}
