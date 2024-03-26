package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GradeSubjectFilterPredicateTest {

    @Test
    public void equals() {
        GradeSubjectFilterPredicate firstPredicate = new GradeSubjectFilterPredicate(
                new Grade(), new Subject());

        GradeSubjectFilterPredicate secondPredicate = new GradeSubjectFilterPredicate(
                new Grade("A"), new Subject("Maths"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GradeSubjectFilterPredicate firstPredicateCopy = new GradeSubjectFilterPredicate(
                new Grade(), new Subject());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different filter -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_filterContainsGrade_returnsTrue() {
        // Grade only
        GradeSubjectFilterPredicate predicate = new GradeSubjectFilterPredicate(new Grade("A"), new Subject());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_filterContainsSubject_returnsTrue() {
        // Subject only
        GradeSubjectFilterPredicate predicate = new GradeSubjectFilterPredicate(
                new Grade(), new Subject("English"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void test_filterContainsGradeSubject_returnsTrue() {
        // Matches Grade and Subject
        GradeSubjectFilterPredicate predicate = new GradeSubjectFilterPredicate(
                new Grade("A"), new Subject("English"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void test_filterContainsGradeSubject_returnsFalse() {
        // Doesn't match Grade and Subject
        GradeSubjectFilterPredicate predicate = new GradeSubjectFilterPredicate(
                new Grade("A-"), new Subject("English"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void toStringMethod() {
        String grade = "B-";
        String subject = "English";
        GradeSubjectFilterPredicate predicate = new GradeSubjectFilterPredicate(
                new Grade(grade), new Subject(subject));

        String expected = GradeSubjectFilterPredicate.class.getSimpleName() + "{grade=" + grade
                + ", subject=" + subject + "}";
        assertEquals(expected, predicate.toString());
    }
}
