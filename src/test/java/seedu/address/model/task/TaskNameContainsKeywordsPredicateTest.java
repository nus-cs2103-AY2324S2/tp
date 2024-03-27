package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TaskNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("project");
        List<String> secondPredicateKeywordList = Arrays.asList("project", "meeting");

        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different employee -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskNameContainsKeywords_returnsTrue() {
        Task testTask = new Task(new TaskName("project meeting"), new TaskId(2), new TaskStatus(false),
                new AssignedEmployees(""));
        // One keyword
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("project"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("project", "meeting"));
        assertTrue(predicate.test(testTask));

        // Only one matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("project"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("pRoJEct", "mEeTing"));
        assertTrue(predicate.test(testTask));
    }
    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        Task testTask = new Task(new TaskName("project meeting"), new TaskId(2), new TaskStatus(false),
                new AssignedEmployees("John"));
        // Zero keywords
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(testTask));

        // Non-matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(List.of("assignment"));
        assertFalse(predicate.test(testTask));

        // Keywords match TaskId, TaskStatus and AssignedEmployees, but does not match TaskName
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("2", "In", "Progress", "John"));
        assertFalse(predicate.test(testTask));
    }
    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(keywords);

        String expected = TaskNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

