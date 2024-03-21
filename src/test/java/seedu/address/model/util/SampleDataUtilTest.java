package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Person;

public class SampleDataUtilTest {

    @Test
    void getSamplePersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertEquals(6, samplePersons.length);
    }

    @Test
    void getSampleAddressBook() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();
        assertNotNull(addressBook);
    }

    @Test
    void getLessonSet() {
        Set<Lesson> lessonSet = SampleDataUtil.getLessonSet("Maths|10-05-2004|12:29|0", "English|10-05-2004|12:29|1");
        assertEquals(2, lessonSet.size());
        assertTrue(lessonSet.contains(new Lesson("Maths|10-05-2004|12:29|0")));
        assertTrue(lessonSet.contains(new Lesson("English|10-05-2004|12:29|1")));
    }
}
