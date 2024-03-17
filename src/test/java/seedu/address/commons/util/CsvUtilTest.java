package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

/**
 * Test for reading csv files
 */
public class CsvUtilTest {
    private final String filePath = "addressbookdata/addressbook.csv";

    @Test
    public void readCsvFile_noExceptionThrown() throws IOException {
        Path path = Path.of(filePath);
        CsvUtil.readCsvFile(path);
        
    }

}
