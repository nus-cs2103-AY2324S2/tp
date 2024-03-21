package seedu.address.logic.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Client;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.UniquePersonList;

/**
 * A utility class for exporting data from an address book to a CSV file.
 * It provides methods to create a CSV file containing the data of persons in the address book.
 */
public class CsvExporter {

    private final String filename;

    private boolean isSuccessful = false;

    private final UniquePersonList persons;

    /**
     * Constructs a CSVExporter object with the specified list of persons and filename.
     *
     * @param persons  The list of persons to be exported to CSV.
     * @param filename The filename for the CSV file to be created.
     */
    public CsvExporter(UniquePersonList persons, String filename) {
        this.persons = persons;
        this.filename = filename;
    }

    /**
     * Executes the CSV export process by creating a CSV file with the data from the list of contacts.
     * If the export process is successful, sets the {@code isSuccessful} flag to true; otherwise, sets it to false.
     */
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
        String[] fieldNames = {"ID", "Name", "Phone", "Email", "Address", "Remark", "Tags", "Department",
            "Job Title", "Skills", "Products", "Preferences", "Terms of Service"};
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
        String[] personStringArray = new String[13];

        personStringArray[0] = person.getId().toString();
        personStringArray[1] = person.getName().toString();
        personStringArray[2] = person.getPhone().toString();
        personStringArray[3] = person.getEmail().toString();
        personStringArray[4] = "\"" + person.getAddress().toString() + "\"";
        personStringArray[5] = (person.getRemark() != null) ? person.getRemark().toString() : "";
        personStringArray[6] = "\"" + person.getTagsAsString() + "\"";
        if (person instanceof Employee) {
            Employee employee = (Employee) person;
            personStringArray[7] = employee.getDepartment().toString();
            personStringArray[8] = employee.getJobTitle().toString();
            personStringArray[9] = employee.getSkills().toString();
            personStringArray[10] = "";
            personStringArray[11] = "";
            personStringArray[12] = "";
        } else if (person instanceof Client) {
            Client client = (Client) person;
            personStringArray[7] = "";
            personStringArray[8] = "";
            personStringArray[9] = "";
            personStringArray[10] = client.getProducts().toString();
            personStringArray[11] = client.getPreferences();
            personStringArray[12] = "";
        } else if (person instanceof Supplier) {
            Supplier supplier = (Supplier) person;
            personStringArray[7] = "";
            personStringArray[8] = "";
            personStringArray[9] = "";
            personStringArray[10] = supplier.getProducts().toString();
            personStringArray[11] = "";
            personStringArray[12] = supplier.getTermsOfService().toString();
        }

        return personStringArray;
    }

    public boolean getIsSuccessful() {
        return isSuccessful;
    }
}
