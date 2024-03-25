package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_FILE_ALREADY_EXISTS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FILE;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.ImportManager;

/**
 * Parses inputs and creates a new ImportCommand object.
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String DATA_PATH = "data/";
    public static final String CSV_PATH = "./imports/";
    @Override
    public ImportCommand parse(String args) throws ParseException {
        String fileNameToImport = args.trim();
        if (fileNameToImport.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        Path pathToImportFrom = convertFileNameToCsvPath(fileNameToImport);
        Path pathToImportTo = convertFileNameToJsonPath(fileNameToImport);

        return new ImportCommand(pathToImportFrom, pathToImportTo, new ImportManager(pathToImportFrom, pathToImportTo));
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
     * @throws ParseException There is already a json file that shares the new filepath.
     */
    private Path convertFileNameToJsonPath(String fileName) throws ParseException {
        Path jsonFilePath = Paths.get(DATA_PATH + fileName + ".json");
        if (FileUtil.isFileExists(jsonFilePath)) {
            throw new ParseException(String.format(MESSAGE_FILE_ALREADY_EXISTS));
        }
        return jsonFilePath;
    }
}
