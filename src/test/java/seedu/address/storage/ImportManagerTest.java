package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.util.FileUtil;

public class ImportManagerTest {
    @TempDir
    public Path tempDir;

    private ImportManager importManager;

    @BeforeEach
    public void setUp() {
        Path pathToImportFrom = tempDir.resolve("test.csv");
        Path pathToImportTo = tempDir.resolve("test.json");
        ImportManager importManager = new ImportManager(pathToImportFrom, pathToImportTo);
        this.importManager = importManager;
    }

    @Test
    public void importCsvFileAndConvertToJsonFile_validCsv_convertsToJson() throws IOException {
        String csvContent = "StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n"
                + "00001,John Doe,91234567,97654321,johndoe@example.com,123 Example Street,classmates;family";

        FileUtil.writeToFile(importManager.getPathToImportFrom(), csvContent);

        importManager.importCsvFileAndConvertToJsonFile();

        String expectedJson = "{ \"persons\": [\n"
                + "  { \"studentId\": \"00001\", \"name\": \"John Doe\", \"parentPhoneNumberOne\": \"91234567\", "
                + "\"parentPhoneNumberTwo\": \"97654321\", \"email\": \"johndoe@example.com\", "
                + "\"address\": \"123 Example Street\", \"tags\": [\"Classmates\", \"Family\"] }\n]}";
        String actualJson = FileUtil.readFromFile(importManager.getPathToImportTo()).trim();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void importCsvFileAndAddToJsonFile_validCsv_addsToJson() throws IOException {
        String existingJson = "{ \"persons\": [\n"
                + "  { \"studentId\": \"00002\", \"name\": \"Jane Doe\", \"parentPhoneNumberOne\": \"81234567\", "
                + "\"parentPhoneNumberTwo\": \"87654321\", \"email\": \"janedoe@example.com\", "
                + "\"address\": \"124 Example Street\", \"tags\": [\"Friends\", \"Family\"] }\n]}";
        FileUtil.writeToFile(importManager.getPathToImportTo(), existingJson);

        String csvContent = "StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n"
                + "00003,Mike Smith,91234876,97654821,mikesmith@example.com,125 Example Street,sports;music";
        FileUtil.writeToFile(importManager.getPathToImportFrom(), csvContent);

        importManager.importCsvFileAndAddToJsonFile();

        String expectedJson = "{ \"persons\": [\n"
                + "  { \"studentId\": \"00002\", \"name\": \"Jane Doe\", \"parentPhoneNumberOne\": \"81234567\", "
                + "\"parentPhoneNumberTwo\": \"87654321\", \"email\": \"janedoe@example.com\", \"address\":"
                + " \"124 Example Street\", \"tags\": [\"Friends\", \"Family\"] }, { \"studentId\": \"00003\", "
                + "\"name\": \"Mike Smith\", \"parentPhoneNumberOne\": \"91234876\", \"parentPhoneNumberTwo\": "
                + "\"97654821\", \"email\": \"mikesmith@example.com\", \"address\": \"125 Example Street\", \"tags\": "
                + "[\"Sports\", \"Music\"] }\n]}";
        String actualJson = FileUtil.readFromFile(importManager.getPathToImportTo()).trim();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void importCsvFileAndAddToJsonFile_duplicateId_throwsIoException() throws IOException {
        String existingJson = "{ \"persons\": [\n"
                + "  { \"studentId\": \"00001\", \"name\": \"Jane Doe\", \"parentPhoneNumberOne\": \"81234567\", "
                + "\"parentPhoneNumberTwo\": \"87654321\", \"email\": \"janedoe@example.com\", "
                + "\"address\": \"124 Example Street\", \"tags\": [\"Friends\", \"Family\"] }\n]}";
        FileUtil.writeToFile(importManager.getPathToImportTo(), existingJson);

        String csvContent = "StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n"
                + "00001,Mike Smith,91234876,97654821,mikesmith@example.com,125 Example Street,sports;music";
        FileUtil.writeToFile(importManager.getPathToImportFrom(), csvContent);

        assertThrows(IOException.class, () -> importManager.importCsvFileAndAddToJsonFile());
    }

    @Test
    public void importCsvFileAndAddToJsonFile_internalDuplicateId_throwsIoException() throws IOException {
        String existingJson = "{ \"persons\": [\n"
                + "  { \"studentId\": \"00001\", \"name\": \"Jane Doe\", \"parentPhoneNumberOne\": \"81234567\", "
                + "\"parentPhoneNumberTwo\": \"87654321\", \"email\": \"janedoe@example.com\", "
                + "\"address\": \"124 Example Street\", \"tags\": [\"Friends\", \"Family\"] }\n]}";
        FileUtil.writeToFile(importManager.getPathToImportTo(), existingJson);

        String csvContent = "StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n"
                + "00001,Mike Smith,91234876,97654821,mikesmith@example.com,125 Example Street,sports;music\n"
                + "00001,Mike Smith,91234876,97654821,mikesmith@example.com,125 Example Street,sports;music";
        FileUtil.writeToFile(importManager.getPathToImportFrom(), csvContent);

        assertThrows(IOException.class, () -> importManager.importCsvFileAndAddToJsonFile());
    }

    @Test
    public void getPathToImportFrom_returnsCorrectPath() {
        Path expectedPath = tempDir.resolve("test.csv");
        assertEquals(expectedPath, importManager.getPathToImportFrom());
    }

    @Test
    public void getPathToImportTo_returnsCorrectPath() {
        Path expectedPath = tempDir.resolve("test.json");
        assertEquals(expectedPath, importManager.getPathToImportTo());
    }

    @Test
    public void setPathToImportFrom_setsNewPath() {
        Path newPath = tempDir.resolve("newTest.csv");
        importManager.setPathToImportFrom(newPath);
        assertEquals(newPath, importManager.getPathToImportFrom());
    }

    @Test
    public void setPathToImportTo_setsNewPath() {
        Path newPath = tempDir.resolve("newTest.json");
        importManager.setPathToImportTo(newPath);
        assertEquals(newPath, importManager.getPathToImportTo());
    }
}
