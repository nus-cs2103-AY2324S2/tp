package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;

import seedu.address.commons.util.FileUtil;
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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MigrateCommand.MESSAGE_USAGE));
        }

        Path pathToImportFrom = FileUtil.convertFileNameToCsvPath(fileNameToImport, CSV_PATH);
        Path placeholderPathToImportTo =
                FileUtil.convertFileNameToJsonPathForMigration(fileNameToImport, PLACEHOLDER_DATA_PATH);

        return new MigrateCommand(pathToImportFrom, new ImportManager(pathToImportFrom,
                placeholderPathToImportTo));
    }

}
