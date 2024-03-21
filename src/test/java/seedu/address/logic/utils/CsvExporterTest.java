package seedu.address.logic.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Client;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.TypicalPersons;


public class CsvExporterTest {

    private static final String TEST_FILENAME = "test_export.csv";

    private CsvExporter csvExporter;
    private UniquePersonList persons;

    @BeforeEach
    public void setUp() {
        UniquePersonList persons = new UniquePersonList();
        persons.setPersons(Arrays.asList(
                TypicalPersons.ALICE, TypicalPersons.BENSON,
                TypicalPersons.DANIEL, TypicalPersons.ELLE, TypicalPersons.FIONA
        ));
        this.persons = persons;
        csvExporter = new CsvExporter(persons, TEST_FILENAME);
    }

    @Test
    public void execute_exportSuccess() {
        csvExporter.execute();
        assertTrue(csvExporter.getIsSuccessful());
        File exportedFile = new File(TEST_FILENAME);
        assertTrue(exportedFile.exists());
        exportedFile.delete(); // Clean up after test
    }

    @Test
    public void execute_exportFailure() {
        // Simulate a situation where IOException occurs during file write
        csvExporter = new CsvExporter(persons, ""); // Provide an invalid filename to trigger IOException
        csvExporter.execute();
        assertFalse(csvExporter.getIsSuccessful());
    }

    @Test
    public void convertPersonToStringArray() {
        Client person = TypicalPersons.ALICE;
        String[] expectedArray = new String[]{
            person.getId().toString(),
            person.getName().toString(),
            person.getPhone().toString(),
            person.getEmail().toString(),
            "\"" + person.getAddress().toString() + "\"",
            person.getRemark().toString(),
            "\"" + person.getTagsAsString() + "\"",
            "",
            "",
            "",
            person.getProducts().toString(),
            person.getPreferences().toString(),
            ""
        };
        assertArrayEquals(expectedArray, csvExporter.convertPersonToStringArray(person));
    }
}
