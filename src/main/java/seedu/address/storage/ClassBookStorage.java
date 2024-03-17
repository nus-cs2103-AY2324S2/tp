package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyClassBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface ClassBookStorage {

    Path getClassBookFilePath();

    Optional<ReadOnlyClassBook> readClassBook() throws DataLoadingException;

    Optional<ReadOnlyClassBook> readClassBook(Path filePath) throws DataLoadingException;

    void saveClassBook(ReadOnlyClassBook classBook) throws IOException;

    void saveClassBook(ReadOnlyClassBook classBook, Path filePath) throws IOException;
}
