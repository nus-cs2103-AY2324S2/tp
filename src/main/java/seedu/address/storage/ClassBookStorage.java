package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyClassBook;

/**
 * Represents a storage for {@link seedu.address.model.ClassBook}.
 */
public interface ClassBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClassBookFilePath();

    /**
     * Returns ClassBook data as a {@link ReadOnlyClassBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClassBook> readClassBook() throws DataLoadingException;

    /**
     * @see #getClassBookFilePath()
     */
    Optional<ReadOnlyClassBook> readClassBook(Path filePath) throws DataLoadingException;

//    void createJsonFileForEachCC(Optional<JsonSerializableClassBook> classBook) throws IOException,
//            IllegalValueException;
//
//    void createJsonFileForEachCC(JsonSerializableClassBook classBook) throws IOException,
//            IllegalValueException;
    /**
     * Saves the given {@link ReadOnlyClassBook} to the storage.
     * @param classBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClassBook(ReadOnlyClassBook classBook) throws IOException, IllegalValueException;

    /**
     * @see #saveClassBook(ReadOnlyClassBook)
     */
    void saveClassBook(ReadOnlyClassBook classBook, Path filePath) throws IOException, IllegalValueException;
}
