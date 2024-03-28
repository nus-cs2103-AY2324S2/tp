package seedu.edulink.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.student.Student;
import seedu.edulink.testutil.TypicalPersons;

public class CsvUtilTest {



    @Test
    public void convertToCsv_typicalStudents_success() throws IOException {
        List<Student> typicalStudents = new ArrayList<>();
        typicalStudents.add(TypicalPersons.ALICE);
        typicalStudents.add(TypicalPersons.BOB);
        String fileName = "test";
        Path filePath = Paths.get(CsvUtil.FOLDER_PATH + fileName + CsvUtil.FILE_FORMAT);

        CsvUtil.convertToCsv(typicalStudents, fileName + CsvUtil.FILE_FORMAT);

        assertTrue(Files.exists(filePath));

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(3, lines.size()); // 1 header line + 2 students

        String expectedHeader = "id,name,phone,email,address,major,intake,tags";
        assertEquals(expectedHeader, lines.get(0));

        String expectedLine1 = "A0251893P,Alice Pauline,94351253,"
            + "alice@example.com,123 | Jurong West Ave 6 | #08-111,"
            + "Computer Science,2023,TA | Smart | friends";
        assertEquals(expectedLine1, lines.get(1));
        Files.delete(filePath);
    }

    @Test
    public void convertToCsv_emptyList_success() throws IOException {
        List<Student> emptyList = List.of();
        String fileName = "empty";
        Path filePath = Paths.get(CsvUtil.FOLDER_PATH + fileName + CsvUtil.FILE_FORMAT);
        CsvUtil.convertToCsv(emptyList, fileName + CsvUtil.FILE_FORMAT);

        assertTrue(Files.exists(filePath));

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size()); // Only header line

        String expectedHeader = "id,name,phone,email,address,major,intake,tags";
        assertEquals(expectedHeader, lines.get(0));
        Files.delete(filePath);
    }
}
