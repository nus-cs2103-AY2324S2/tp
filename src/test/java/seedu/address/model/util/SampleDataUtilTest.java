package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersonsTest() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        // Test the number of sample persons
        assertTrue(samplePersons.length > 0);
    }

    @Test
    public void getSampleAddressBookTest() {
        assertEquals(SampleDataUtil.getSampleAddressBook().getPersonList().size(), 6);
    }

    @Test
    public void getEmptyTagSetTest() {
        assertEquals(SampleDataUtil.getEmptyTagSet().size(), 0);
    }
}
