package seedu.address.model.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Score;

public class ExamTest {

    @Test
    public void isValidName() {
        assertTrue(Exam.isValidName("Midterm")); // alphanumeric characters
        assertTrue(Exam.isValidName("Midterm Exam")); // alphanumeric characters with spaces
        assertFalse(Exam.isValidName("")); // empty string
        assertFalse(Exam.isValidName(" ")); // spaces only
        assertFalse(Exam.isValidName("^")); // contains non-alphanumeric characters
        assertFalse(Exam.isValidName("Midterm*")); // contains non-alphanumeric characters
    }

    @Test
    public void isValidExamScore() {
        assertTrue(Exam.isValidExamScore(1)); // positive score
        assertFalse(Exam.isValidExamScore(0)); // zero score
        assertFalse(Exam.isValidExamScore(-1)); // negative score
    }

    @Test
    public void isSameExam() {
        Exam exam1 = new Exam("Midterm", new Score(100));
        Exam exam2 = new Exam("Midterm", new Score(100));
        Exam exam3 = new Exam("Final", new Score(100));

        assertTrue(exam1.isSameExam(exam2)); // same name, same score
        assertFalse(exam1.isSameExam(exam3)); // different name, same score
    }

    @Test
    public void equals() {
        Exam exam1 = new Exam("Midterm", new Score(100));
        Exam exam2 = new Exam("Midterm", new Score(100));
        Exam exam3 = new Exam("Final", new Score(100));

        assertEquals(exam1, exam2); // same name, same score
        assertFalse(exam1.equals(exam3)); // different name, same score
    }

    @Test
    public void hashCodeTest() {
        Exam exam = new Exam("Midterm", new Score(100));
        int expectedHashCode = exam.getName().hashCode() + exam.getMaxScore().hashCode();
        assertEquals(expectedHashCode, exam.hashCode());
    }
}
