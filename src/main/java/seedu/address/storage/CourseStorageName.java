package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCourseName;
public interface CourseStorageName {

    /**
     * Returns the file path of the data file.
     */
    Path getCourseNameFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyCourseName}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCourseName> readCourse() throws DataLoadingException;

    /**
     * @see #getCourseNameFilePath()
     */
    Optional<ReadOnlyCourseName> readCourse(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCourseName} to the storage.
     * @param course cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCourse(ReadOnlyCourseName course) throws IOException;

    /**
     * @see #saveCourse(ReadOnlyCourseName)
     */
    void saveCourse(ReadOnlyCourseName course, Path filePath) throws IOException;


}
