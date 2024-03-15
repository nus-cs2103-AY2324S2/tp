package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyCourseName;

/**
 * A class to access Course data stored as a json file on the hard disk.
 */
public class JsonCourseNameStorage implements CourseStorageName {

    private static final Logger logger = LogsCenter.getLogger(JsonCourseNameStorage.class);

    private Path filePath;

    public JsonCourseNameStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCourseNameFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCourseName> readCourse() throws DataLoadingException {
        return readCourse(this.filePath);
    }


    /**
     * Similar to {@link #readCourse()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyCourseName> readCourse(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableCourseName> jsonCourse = JsonUtil.readJsonFile(
                filePath, JsonSerializableCourseName.class);
        if (!jsonCourse.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCourse.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveCourse(ReadOnlyCourseName course) throws IOException {
        saveCourse(course, filePath);
    }

    /**
     * Similar to {@link #saveCourse(ReadOnlyCourseName)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCourse(ReadOnlyCourseName course, Path filePath) throws IOException {
        requireNonNull(course);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCourseName(course), filePath);
    }

}
