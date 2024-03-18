package seedu.address.logic.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * A utility class for exporting data from an address book to a CSV file.
 * It provides methods to create a CSV file containing the data of persons in the address book.
 */
public class CsvExporter {

    private final String filename;

    private boolean isSuccessful = false;

    private final UniquePersonList persons;

    public CsvExporter(UniquePersonList persons, String filename) {
        this.persons = persons;
        this.filename = filename;
    }

    public void execute() {
        List<String[]> data = createDataList();
        try (Writer writer = new FileWriter(this.filename)) {
            for (String[] row : data) {
                writer.write(String.join(",", row) + System.lineSeparator());
            }
            isSuccessful = true;
        } catch (IOException e) {
            isSuccessful = false;
        }
    }

    /**
     * Creates a list of string arrays representing the data to be exported to a CSV file.
     * Each string array represents a row in the CSV file, with the first array containing field names.
     *
     * @return A list of string arrays representing the data to be exported.
     */
    private List<String[]> createDataList() {
        List<String[]> dataList = new ArrayList<>();
        String[] fieldNames = {"Name", "Phone", "Email", "Address", "Remark", "Tags"};
        dataList.add(fieldNames);

        for (Person person : this.persons) {
            String[] personStringArray = convertPersonToStringArray(person);
            dataList.add(personStringArray);
        }

        return dataList;
    }

    /**
     * Converts a Person object to a string array representing its data.
     *
     * @param person The Person object to be converted.
     * @return A string array representing the data of the Person object.
     */
    public String[] convertPersonToStringArray(Person person) {
        String[] personStringArray = new String[6]; // To be checked

        personStringArray[0] = person.getName().toString();
        personStringArray[1] = person.getPhone().toString();
        personStringArray[2] = person.getEmail().toString();
        personStringArray[3] = "\"" + person.getAddress().toString() + "\"" ;
        personStringArray[4] = (person.getRemark() != null) ? person.getRemark().toString() : "";
        personStringArray[5] = "\"" + person.getTagsAsString() + "\"";

        return personStringArray;
    }

    public boolean getIsSuccessful() {
        return isSuccessful;
    }
}
