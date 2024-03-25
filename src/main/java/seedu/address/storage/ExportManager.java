package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Manages exports for the application.
 */
public class ExportManager implements Export {

    private Path pathToExportTo;

    @Override
    public Path getPathToExportTo() {
        return this.pathToExportTo;
    }

    @Override
    public void exportStudentList(ObservableList<Person> studentList) throws IOException {
        setExportPath();

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
        FileUtil.writeToFile(pathToExportTo, csvContent.toString());
    }


    /**
     * Sets the path and directory for the exports.
     */
    private void setExportPath() throws IOException {
        String fileName = "export_" + StringUtil.timeStampString() + ".csv";
        String directoryName = "exports";
        Path pathToExportTo = Paths.get(directoryName, fileName);
        FileUtil.createIfMissing(pathToExportTo);
        this.pathToExportTo = pathToExportTo;
    }

    /**
     * Sets the path for testing
     */
    public void setExportPathForTesting(Path testPath) {
        this.pathToExportTo = testPath;
    }


}
