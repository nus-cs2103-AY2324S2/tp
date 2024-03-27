package seedu.address.model.exam;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.exam.exceptions.ExamNotFoundException;
import seedu.address.model.person.Score;

public class UniqueExamListTest {

    private final UniqueExamList uniqueExamList = new UniqueExamList();

    @Test
    public void contains_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExamList.contains(null));
    }

    @Test
    public void contains_examNotInList_returnsFalse() {
        Exam exam = new Exam("Midterm", new Score(100));
        assertFalse(uniqueExamList.contains(exam));
    }

    @Test
    public void contains_examInList_returnsTrue() {
        Exam exam = new Exam("Midterm", new Score(100));
        uniqueExamList.add(exam);
        assertTrue(uniqueExamList.contains(exam));
    }

    @Test
    public void add_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExamList.add(null));
    }

    @Test
    public void add_duplicateExam_throwsDuplicateExamException() {
        Exam exam = new Exam("Midterm", new Score(100));
        uniqueExamList.add(exam);
        assertThrows(DuplicateExamException.class, () -> uniqueExamList.add(exam));
    }

    @Test
    public void remove_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExamList.remove(null));
    }

    @Test
    public void remove_examDoesNotExist_throwsExamNotFoundException() {
        Exam exam = new Exam("Midterm", new Score(100));
        assertThrows(ExamNotFoundException.class, () -> uniqueExamList.remove(exam));
    }

    @Test
    public void remove_existingExam_removesExam() {
        Exam exam = new Exam("Midterm", new Score(100));
        uniqueExamList.add(exam);
        uniqueExamList.remove(exam);
        assertFalse(uniqueExamList.contains(exam));
    }

    @Test
    public void setExams_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExamList.setExams((List<Exam>) null));
    }

    @Test
    public void setExams_list_replacesOwnListWithProvidedList() {
        Exam examA = new Exam("Midterm", new Score(100));
        Exam examB = new Exam("Final", new Score(200));
        uniqueExamList.add(examA);
        List<Exam> examList = Collections.singletonList(examB);
        uniqueExamList.setExams(examList);
        assertFalse(uniqueExamList.contains(examA));
        assertTrue(uniqueExamList.contains(examB));
    }

    @Test
    public void setExams_listWithDuplicateExams_throwsDuplicateExamException() {
        Exam exam = new Exam("Midterm", new Score(100));
        List<Exam> listWithDuplicateExams = Arrays.asList(exam, exam);
        assertThrows(DuplicateExamException.class, () -> uniqueExamList.setExams(listWithDuplicateExams));
    }
}
