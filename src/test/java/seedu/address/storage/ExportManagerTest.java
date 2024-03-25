package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ExportManagerTest {

    @Test
    public void exportStudentList_validList_success() throws IOException {
        ObservableList<Person> studentList = FXCollections.observableArrayList();
        studentList.add(new PersonBuilder().build());

        ExportManager exportManager = new ExportManager();


        exportManager.exportStudentList(studentList);
        Path tempFilePath = exportManager.getPathToExportTo();

        String expectedContent = "StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n"
                + "10001,Amy Bee,85355255,91234544,amy@gmail.com,123 Jurong West Ave 6 #08-111,";
        String actualContent = new String(Files.readAllBytes(tempFilePath));
        assertEquals(expectedContent.trim(), actualContent.trim());

        // Clean up: Delete the temporary file
        Files.deleteIfExists(tempFilePath);
    }

}
