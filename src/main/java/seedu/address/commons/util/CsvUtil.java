package seedu.address.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import seedu.address.commons.core.LogsCenter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Converts a CSV file to a JSON file
     *
     * @param csvFilePath cannot be null.
     * @param jsonFilePath cannot be null.
     */
    public static void convertToJSON(String csvFilePath, String jsonFilePath) {
        try {
            // Step 1: Read CSV file
            FileReader fileReader = new FileReader(csvFilePath);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).build();

            // Step 2: Parse CSV data
            List<String[]> allData = csvReader.readAll();
            String[] header = allData.get(0);
            List<Map<String, String>> jsonData = new ArrayList<>();

            // Step 3: Convert CSV to JSON
            for (int i = 1; i < allData.size(); i++) {
                Map<String, String> row = new HashMap<>();
                String[] data = allData.get(i);
                for (int j = 0; j < header.length; j++) {
                    row.put(header[j], data[j]);
                }
                jsonData.add(row);
            }

            // Step 4: Write JSON data to file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(jsonFilePath), jsonData);

            logger.info("JSON file successfully created at " + jsonFilePath);
        } catch (IOException | CsvException e) {
            logger.warning("An error occurred during the conversion process: " + e.getMessage());
        }
    }
}
