package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CsvUtil;

import java.util.Optional;
import java.util.logging.Logger;
import seedu.address.model.ReadOnlyAddressBook;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

public class CsvReadOnlyAddressBookStorage implements ReadOnlyAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(CsvReadOnlyAddressBookStorage.class);
    private final Path filePath;

    public CsvReadOnlyAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<AddressBookStorage> csvAddressBook = CsvUtil.readCsvFile(
                filePath, .class);
        if (!csvAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(csvAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }
}
