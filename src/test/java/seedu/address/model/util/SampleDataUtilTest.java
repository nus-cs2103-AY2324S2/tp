package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

public class SampleDataUtilTest {

    @Test
    void getSampleStudents() {
        Student[] sampleStudents = SampleDataUtil.getSampleStudents();
        assertEquals(6, sampleStudents.length);
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
