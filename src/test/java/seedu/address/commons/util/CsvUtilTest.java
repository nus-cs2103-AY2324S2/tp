package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Test for reading csv files
 */
public class CsvUtilTest {
    private final String filePath = "addressbookdata/addressbook.csv";

    @Test
    public void readCsvFile_noExceptionThrown() throws IOException {
        Path path = Path.of(filePath);
        Optional<AddressBookStorage> actualAddressBookStorage = CsvUtil.readCsvFile(path);
        ReadOnlyAddressBook newAddressBookStorage = SampleDataUtil.getSampleAddressBook();

    }

}
