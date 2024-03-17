package seedu.address.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import seedu.address.commons.core.LogsCenter;
import seedu.address.storage.AddressBookStorage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Converts a JSON to CSV
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    public static AddressBookStorage readCsvFile(Path filePath) throws IOException {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath.toString())).withSkipLines(1).build();
            List<String[]> rows = reader.readAll();
            List<Map<String, String>> data = new ArrayList<>();
            String[] header = rows.get(0);
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < header.length; j++) {
                    map.put(header[j], row[j]);
                }
                data.add(map);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(data);
            return objectMapper.readValue(json, classOfObjectToDeserialize);
        } catch (IOException | CsvException e) {
            logger.info("Error reading from " + filePath + ": " + e.getMessage());
            return null;
        }
    }
}
