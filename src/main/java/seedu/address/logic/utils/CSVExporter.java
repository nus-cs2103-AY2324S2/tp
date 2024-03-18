package seedu.address.logic.utils;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class CSVExporter {

    private final String filename;
    private final UniquePersonList persons;
    public boolean isSuccessful = false;

    public CSVExporter(UniquePersonList persons, String filename) {
        this.persons = persons;
        this.filename = filename;
    }

    public void execute() {
        List<String[]> data = createDataList();
        try (Writer writer = new FileWriter(this.filename)) {
            for (String[] row : data) {
                writer.write(String.join(",", row) + System.lineSeparator());
            }
        } catch (IOException e) {
            isSuccessful = false;
        } finally {
            isSuccessful = true;
        }
    }

    private List<String[]> createDataList() {
        List<String[]> dataList = new ArrayList<>();
        String[] fieldNames = {"Name","Phone","Email","Address","Remark","Tags"};
        dataList.add(fieldNames);

        for (Person person : this.persons) {
            String[] personStringArray = convertPersonToStringArray(person);
            dataList.add(personStringArray);
        }

        return dataList;
    }

    private String[] convertPersonToStringArray(Person person) {
        String[] personStringArray = new String[6]; // Assuming there are 6 fields in Person

        // Convert each field of the Person object to a string
        personStringArray[0] = person.getName().toString();
        personStringArray[1] = person.getPhone().toString();
        personStringArray[2] = person.getEmail().toString();
        personStringArray[3] = "\"" + person.getAddress().toString() + "\"" ;
        personStringArray[4] = person.getRemark().toString();
        personStringArray[5] = "\"" + person.getTagsAsString() + "\"";

        return personStringArray;
    }

}
