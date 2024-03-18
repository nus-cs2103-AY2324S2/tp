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
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyOrderBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);
    private Path addressBookFilePath;
    private Path orderFilePath;

    //private Path filePath;

    /**
     * Constructs a {@code JsonAddressBookStorage} with the specified file paths.
     *
     * @param addressBookFilePath The file path for the address book data.
     */
    public JsonAddressBookStorage(Path addressBookFilePath) {
        this.addressBookFilePath = addressBookFilePath;
    }

    /**
     * Constructs a {@code JsonAddressBookStorage} with the specified file paths.
     *
     * @param addressBookFilePath The file path for the address book data.
     * @param orderFilePath The file path for the order book data.
     */
    public JsonAddressBookStorage(Path addressBookFilePath, Path orderFilePath) {
        this.addressBookFilePath = addressBookFilePath;
        this.orderFilePath = orderFilePath;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public Path getOrderBookFilePath() {
        return orderFilePath;
    }


    // ================ AddressBook methods ==============================

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookFilePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public Optional<ReadOnlyOrderBook> readOrderBook() throws DataLoadingException {
        return Optional.empty();
    }

    /**
     * Similar to {@link #readOrderBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyOrderBook> readOrderBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableOrderBook> jsonOrderBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableOrderBook.class);
        if (!jsonOrderBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonOrderBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookFilePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    // ================ OrderBook methods ==============================

    @Override
    public Optional<ReadOnlyOrderBook> readOrders() throws DataLoadingException {
        return readOrderBook(orderFilePath);
    }

    @Override
    public void saveOrderBook(ReadOnlyOrderBook orderBook) throws IOException {
        saveOrderBook(orderBook, orderFilePath);
    }

    /**
     * Similar to {@link #saveOrderBook(ReadOnlyOrderBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveOrderBook(ReadOnlyOrderBook orderBook, Path filePath) throws IOException {
        requireNonNull(orderBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrderBook(orderBook), filePath);
    }
}
