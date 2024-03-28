package seedu.address.commons.util;

import static seedu.address.logic.Messages.MESSAGE_FILE_ALREADY_EXISTS;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FILE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Converts the filename into csv filepath to be imported from.
     *
     * @param fileName Name of file to be imported.
     * @path Path of the folder csv files are stored in.
     * @return Path of csv file to be imported from.
     * @throws ParseException File does not exist.
     */
    public static Path convertFileNameToCsvPath(String fileName, String csvFolder) throws ParseException {
        Path csvFilePath = Paths.get(csvFolder + fileName + ".csv");
        if (!FileUtil.isFileExists(csvFilePath)) {
            throw new ParseException(String.format(MESSAGE_MISSING_FILE));
        }
        return csvFilePath;
    }

    /**
     * Converts the filename into json filepath to be imported to.
     *
     * @param fileName Name of file to be imported.
     * @param jsonFolder Path of the folder json files are stored in.
     * @return Path of json file to be imported to.
     */
    public static Path convertFileNameToJsonPathForMigration(String fileName, String jsonFolder) {
        Path placeholderJsonFilePath = Paths.get(jsonFolder + fileName + ".json");
        return placeholderJsonFilePath;
    }

    /**
     * Converts the filename into json filepath to be imported to.
     *
     * @param fileName Name of file to be imported.
     * @param jsonFolder Path of the folder json files are stored in.
     * @return Path of json file to be imported to.
     * @throws ParseException There is already a json file that shares the new filepath.
     */
    public static Path convertFileNameToJsonPathForImport(String fileName, String jsonFolder) throws ParseException {
        Path jsonFilePath = Paths.get(jsonFolder + fileName + ".json");
        if (FileUtil.isFileExists(jsonFilePath)) {
            throw new ParseException(String.format(MESSAGE_FILE_ALREADY_EXISTS));
        }
        return jsonFilePath;
    }

}
