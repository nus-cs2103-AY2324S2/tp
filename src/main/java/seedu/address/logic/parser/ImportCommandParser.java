package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;

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

        Path pathToImportFrom = FileUtil.convertFileNameToCsvPath(fileNameToImport, CSV_PATH);
        Path pathToImportTo = FileUtil.convertFileNameToJsonPathForImport(fileNameToImport, DATA_PATH);

        return new ImportCommand(pathToImportFrom, pathToImportTo, new ImportManager(pathToImportFrom, pathToImportTo));
    }

}
