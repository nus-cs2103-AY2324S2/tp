package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StateStorage {
    private static String filePath = "./data/state.txt";
    private static final Path DIRECTORY_PATH = Paths.get("./data");
    private static final Path FILE_PATH = Paths.get(filePath);

    public StateStorage() {
        assert !filePath.isBlank() : "the file path should not be blank";

        this.filePath = filePath;

        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeState(String input) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(input);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static String loadState() {
        String lastCommand = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data = reader.readLine();

            while (data != null) {
                lastCommand = lastCommand + data;
                if (lastCommand == null) {
                    break;
                }
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
//            throw new DataLoadingException(e);
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
//            throw new DataLoadingException(e);
        }
        return lastCommand;
    }
}
