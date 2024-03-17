package tutorpro.model.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tutorpro.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void newStudent_hasStudentTag() {
        Student student = new StudentBuilder().build();
        Assertions.assertTrue(student.getTags().contains(Student.STUDENT_TAG));
    }
}
