package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * API of the export component
 */
public interface Export {

    /**
     * Returns the path of exports.
     */
    Path getPathToExportTo();

    /**
     * Exports the list of students to CSV.
     */
    void exportStudentList(ObservableList<Person> studentList) throws IOException;
}
