package seedu.address.model.student.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class MajorContainsSubStringPredicateTest {
    @Test
    public void equals() {
        MajorContainsSubStringPredicate firstPredicate = new MajorContainsSubStringPredicate("Math");
        MajorContainsSubStringPredicate secondPredicate = new MajorContainsSubStringPredicate("Computer Science");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MajorContainsSubStringPredicate firstPredicateCopy = new MajorContainsSubStringPredicate("Math");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_majorContainsSubString_returnsTrue() {
        MajorContainsSubStringPredicate predicate = new MajorContainsSubStringPredicate("Computer Science");
        assertTrue(predicate.test(new StudentBuilder().withMajor("Computer Science").build()));
    }

    @Test
    public void test_majorDoesNotContainSubString_returnsFalse() {
        MajorContainsSubStringPredicate predicate = new MajorContainsSubStringPredicate("Math");
        assertFalse(predicate.test(new StudentBuilder().withMajor("Computer Science").build()));
    }

    @Test
    public void toStringMethod() {
        String subString = "Math";
        MajorContainsSubStringPredicate predicate = new MajorContainsSubStringPredicate("Math");

        String expected = MajorContainsSubStringPredicate.class.getCanonicalName() + "{sub string=" + subString + "}";
        assertEquals(expected, predicate.toString());
    }
}

