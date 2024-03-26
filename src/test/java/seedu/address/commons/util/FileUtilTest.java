package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.parser.exceptions.ParseException;

public class FileUtilTest {

    @TempDir
    Path tempDir;

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void convertFileNameToJsonPathForMigration_validInput_correctPath() {
        String fileName = "test";
        String jsonFolder = "data/";
        Path expectedPath = Paths.get("data/test.json");

        Path resultPath = FileUtil.convertFileNameToJsonPathForMigration(fileName, jsonFolder);

        assertEquals(expectedPath, resultPath);
    }

    @Test
    public void convertFileNameToJsonPathForImport_validInput_correctPath() throws ParseException {
        String fileName = "uniqueFile";
        String jsonFolder = tempDir.toString() + "/";
        Path expectedPath = Paths.get(tempDir.toString(), "uniqueFile.json");

        Path resultPath = FileUtil.convertFileNameToJsonPathForImport(fileName, jsonFolder);

        assertEquals(expectedPath, resultPath);
    }

    @Test
    public void convertFileNameToJsonPathForImport_fileExists_throwsParseException() throws IOException {
        String fileName = "existingFile";
        String jsonFolder = tempDir.toString() + "/";
        Path existingFilePath = Paths.get(jsonFolder, fileName + ".json");
        Files.createFile(existingFilePath);

        assertThrows(ParseException.class, () ->
                FileUtil.convertFileNameToJsonPathForImport(fileName, jsonFolder));

        Files.deleteIfExists(existingFilePath);
    }

}
