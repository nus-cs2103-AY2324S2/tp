package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyClassBook;
import seedu.address.model.person.Classes;

/**
 * A class to access ClassBook data stored as a json file on the hard disk.
 */
public class JsonClassBookStorage implements ClassBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClassBookStorage.class);

    private Path filePath;

    public JsonClassBookStorage(Path filePath) {
        this.filePath = filePath;
    }


    public Path getClassBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClassBook> readClassBook() throws DataLoadingException {
        return readClassBook(filePath);
    }

    /**
     * Similar to {@link #readClassBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyClassBook> readClassBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableClassBook> jsonClassBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableClassBook.class);
        if (!jsonClassBook.isPresent()) {
            return Optional.empty();
        }

        try {
//            createJsonFileForEachCC(jsonClassBook);
            return Optional.of(jsonClassBook.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataLoadingException(e);
        }
    }


//    @Override
//    public void createJsonFileForEachCC(Optional<JsonSerializableClassBook> classBook) throws IOException,
//            IllegalValueException {
//        List<Classes> classList = classBook.get().toModelType().getClassList();
//        for (Classes classes : classList) {
//            String cc = classes.getCourseCode().getCourseCode();
//            String jsonFileName = "data/classbook/" + cc + ".json";
//            FileUtil.createIfMissing(jsonFileName);
//        }
//    }
//
//    @Override
//    public void createJsonFileForEachCC(JsonSerializableClassBook classBook) throws IOException,
//            IllegalValueException {
//        List<Classes> classList = classBook.toModelType().getClassList();
//        for (Classes classes : classList) {
//            String cc = classes.getCourseCode().getCourseCode();
//            String jsonFileName = "data/classbook/" + cc + ".json";
//            FileUtil.createIfMissing(jsonFileName);
//        }
//    }

    @Override
    public void saveClassBook(ReadOnlyClassBook classBook) throws IOException, IllegalValueException {
        saveClassBook(classBook, filePath);
    }

    /**
     * Similar to {@link #saveClassBook(ReadOnlyClassBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClassBook(ReadOnlyClassBook classBook, Path filePath) throws IOException, IllegalValueException {
        requireNonNull(classBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
//        createJsonFileForEachCC(new JsonSerializableClassBook(classBook));
        JsonUtil.saveJsonFile(new JsonSerializableClassBook(classBook), filePath);
    }


}
