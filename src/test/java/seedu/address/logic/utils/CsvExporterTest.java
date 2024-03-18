package seedu.address.logic.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
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
                TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL,
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
        Person person = TypicalPersons.ALICE;
        String[] expectedArray = {"Alice Pauline", "94351253", "alice@example.com",
            "\"123, Jurong West Ave 6, #08-111\"", "", "\"friends\""};
        assertArrayEquals(expectedArray, csvExporter.convertPersonToStringArray(person));
    }
}
