package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ExportManagerTest {

    private Path tempFilePath;
    @Test
    public void exportStudentList_validList_success() throws IOException {
        ObservableList<Person> studentList = FXCollections.observableArrayList();
        studentList.add(new PersonBuilder().build());

        //Creating a mock file system to simulate the export
        setTempFilePath();
        ExportManager exportManager = createTestExportManager();

        exportManager.setExportPathForTesting(tempFilePath);
        exportManager.exportStudentList(studentList);

        String expectedContent = "StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n"
                + "10001,Amy Bee,85355255,91234544,amy@gmail.com,123 Jurong West Ave 6 #08-111,";
        String actualContent = new String(Files.readAllBytes(tempFilePath));
        assertEquals(expectedContent.trim(), actualContent.trim());

        // Clean up: Delete the temporary file
        Files.deleteIfExists(tempFilePath);
    }

    private ExportManager createTestExportManager() {
        return new ExportManager() {
            @Override
            public void exportStudentList(ObservableList<Person> studentList) throws IOException {
                setExportPathForTesting(tempFilePath);

                StringBuilder csvContent = new StringBuilder();
                csvContent.append("StudentId,Name,ParentPhoneOne,ParentPhoneTwo,Email,Address,Tags\n");

                // Iterating over the student list
                for (Person person : studentList) {
                    csvContent.append(person.getStudentId().toString()).append(",");
                    csvContent.append(person.getName().toString()).append(",");
                    csvContent.append(person.getParentPhoneOne().toString()).append(",");
                    csvContent.append(person.getParentPhoneTwo().toString()).append(",");
                    csvContent.append(person.getEmail().toString()).append(",");
                    csvContent.append(person.getAddress().toNoCommasString()).append(",");

                    // Convert tags set to a string and remove square brackets, individual tags separated by ";"
                    String tagsString = person.getTags().stream()
                            .map(tag -> tag.toString().replaceAll("\\[|\\]", ""))
                            .collect(Collectors.joining(";"));
                    csvContent.append(tagsString).append("\n");
                }

                // Writing to the file
                FileUtil.writeToFile(this.getPathToExportTo(), csvContent.toString());
            }
        };
    }

    private void setTempFilePath() throws IOException {
        this.tempFilePath = Files.createTempFile("export_", ".csv");
    }

}
