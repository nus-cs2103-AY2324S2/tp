package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.Set;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_correctlyCreatesSamplePersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertNotNull(samplePersons);
        assertEquals(6, samplePersons.length); // Assuming there are 6 sample persons

        // Check details of the first sample person
        Person firstPerson = samplePersons[0];
        assertEquals("Alex Yeoh", firstPerson.getName().toString());
        assertEquals("87438807", firstPerson.getPhone().toString());
        assertEquals("alexyeoh@example.com", firstPerson.getEmail().toString());
        assertEquals("Blk 30 Geylang Street 29, #06-40", firstPerson.getAddress().toString());
        assertTrue(firstPerson.getTags().contains(new Tag("friends")));
        assertEquals(SampleDataUtil.DUMMY_LASTCONTACT, firstPerson.getLastcontact());
    }

    @Test
    public void getSampleAddressBook_correctlyPopulatesSampleAddressBook() {
        ReadOnlyAddressBook sampleAb = SampleDataUtil.getSampleAddressBook();
        assertNotNull(sampleAb);

        // The address book should have the same number of persons as the sample data
        assertEquals(6, sampleAb.getPersonList().size());

        // Verify some details of the first person in the address book
        Person firstPersonInAddressBook = sampleAb.getPersonList().get(0);
        assertEquals("Alex Yeoh", firstPersonInAddressBook.getName().toString());
        assertEquals("87438807", firstPersonInAddressBook.getPhone().toString());
        assertEquals("alexyeoh@example.com", firstPersonInAddressBook.getEmail().toString());
        assertEquals("Blk 30 Geylang Street 29, #06-40", firstPersonInAddressBook.getAddress().toString());
        assertTrue(firstPersonInAddressBook.getTags().contains(new Tag("friends")));
        assertEquals(SampleDataUtil.DUMMY_LASTCONTACT, firstPersonInAddressBook.getLastcontact());
    }

    @Test
    public void getTagSet_createsCorrectTagSet() {
        Set<Tag> tags = SampleDataUtil.getTagSet("friends", "colleagues");
        assertNotNull(tags);
        assertEquals(2, tags.size());
        assertTrue(tags.contains(new Tag("friends")));
        assertTrue(tags.contains(new Tag("colleagues")));
    }
}
