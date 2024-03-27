package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class SampleDataUtilTest {
    private SampleDataUtil sample = new SampleDataUtil();

    @Test
    public void testGetSamplePersonsNotEmpty() {
        Person[] samplePersons = sample.getSamplePersons();

        assertNotNull(samplePersons);
        assertTrue(samplePersons.length > 0);
    }

    @Test
    public void testGetSamplePersonsProperties() {
        Person[] samplePersons = sample.getSamplePersons();

        for (Person samplePerson : samplePersons) {
            assertNotNull(samplePerson.getName());
            assertNotNull(samplePerson.getPhone());
            assertNotNull(samplePerson.getEmail());
            assertNotNull(samplePerson.getRole());
            assertNotNull(samplePerson.getAddress());
            assertNotNull(samplePerson.getCourse());
            assertNotNull(samplePerson.getTags());
        }
    }

    @Test
    public void testGetSampleAddressBook() {
        assertNotNull(sample.getSampleAddressBook());
    }
}
