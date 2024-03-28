package seedu.address.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;

/**
 * Represents the database to store the previous state of command before the application is closed.
 */
public class StateStorage {
    private static final String filePath = "./data/state.txt";
    private static final Path DIRECTORY_PATH = Paths.get("./data");
    private static final Path FILE_PATH = Paths.get(filePath);
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Constructor for the storage.
     * <p>
     * The constructor creates a new file and/or directory if the filepath for ./data/state.txt does not exist.
     */
    public StateStorage() {
        assert !filePath.isBlank() : "the file path should not be blank";

        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            logger.info("Error clearing creating state storage: " + e.getMessage());
        }
    }

    /**
     * Clears all the text in the state storage file.
     */
    public static void clearState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (IOException e) {
            logger.info("Error clearing state text: " + e.getMessage());
            logger.info("Starting with a clean slate...");
            deleteStateStorage();
            StateStorage stateStorage = new StateStorage();
        }
    }

    /**
     * Checks if the state storage file exists.
     *
     * @return True if the file exists, else false.
     */
    public static boolean isStateStorageExists() {
        return Files.exists(FILE_PATH);
    }

    /**
     * Deletes the entire state storage file.
     */
    public static void deleteStateStorage() {
        try {
            Files.delete(FILE_PATH);
        } catch (IOException e) {
            logger.info("Error deleting state file: " + e.getMessage());
        }
    }

    /**
     * Saves the command to the state storage by writing to the file.
     *
     * @param input Updated command input (at every change) to be written to the storage file.
     */
    public static void writeState(String input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(input);
        } catch (IOException e) {
            logger.info("Error saving state to file: " + e.getMessage());
        }
    }

    /**
     * Retrieves the past state of the command box if found, else it will return an empty command.
     *
     * @return The last input in the command box, or and empty string if not found.
     * @throws DataLoadingException If the file is not found or cannot be read.
     */
    public static String loadState() throws DataLoadingException {
        logger.info("Loading state from " + FILE_PATH + "...");

        String lastCommand = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data = reader.readLine();

            while (data != null) {
                lastCommand = lastCommand + data;
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new DataLoadingException(e);
        }
        return lastCommand;
    }

    /**
     * Instantiates a stateStorage file if it does not exist. If it already exists, it will return the last command.
     *
     * @return The last input in the command box, or and empty string new file created.
     * @throws DataLoadingException If the file is not found or cannot be read.
     */
    public static String getLastCommand() throws DataLoadingException {
        StateStorage stateStorage = new StateStorage();
        String lastCommand = loadState();

        return lastCommand;
    }

    /**
     * Returns the location of the state file.
     *
     * @return The path of the state file.
     */
    public static Path getFilePath() {
        return FILE_PATH;
    }

    /**
     * Returns the location of the state file as a String.
     *
     * @return The path of the state file as a String.
     */
    public static String getFilePathString() {
        return filePath;
    }

    /**
     * Returns the location of the state directory.
     *
     * @return The path of the state directory.
     */
    public static Path getDirectoryPath() {
        return DIRECTORY_PATH;
    }
}
