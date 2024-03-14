package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyCourse;
public interface CourseStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCourseNameFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyCourse}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCourse> read() throws DataLoadingException;

    /**
     * @see #getCourseNameFilePath()
     */
    Optional<ReadOnlyCourse> readCourseName(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCourse} to the storage.
     * @param course cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCourse(ReadOnlyCourse course) throws IOException;

    /**
     * @see #saveCourse(ReadOnlyCourse)
     */
    void saveCourse(ReadOnlyCourse course, Path filePath) throws IOException;


}
