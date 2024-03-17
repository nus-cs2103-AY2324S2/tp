package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

public class IdContainsQueryIdPredicateTest {

    @Test
    public void test_idContainsQueryIds_returnsTrue() {
        // Match
        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("A1234567X");
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567X").build()));

        // Only partially matching keyword
        predicate = new IdContainsQueryIdPredicate("A123");
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567X").build()));

        // Mixed-case keywords
        predicate = new IdContainsQueryIdPredicate("a1234567x");
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567X").build()));
    }

    @Test
    public void test_idDoesNotContainQueryIds_returnsFalse() {
        // Not matching
        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("A1234567X");
        assertFalse(predicate.test(new PersonBuilder().withId("B9876543Y").build()));

        // Matching substring but not in chronological order
        predicate = new IdContainsQueryIdPredicate("1234");
        assertFalse(predicate.test(new PersonBuilder().withId("B1234567X").build()));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("A1234567Z");
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        IdContainsQueryIdPredicate predicate1 = new IdContainsQueryIdPredicate("A1234567Z");
        IdContainsQueryIdPredicate predicate2 = new IdContainsQueryIdPredicate("A1234567Z");
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        IdContainsQueryIdPredicate predicate1 = new IdContainsQueryIdPredicate("A1234567Z");
        IdContainsQueryIdPredicate predicate2 = new IdContainsQueryIdPredicate("B9876543Y");
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentClasses_returnsFalse() {
        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("A1234567Z");
        assertFalse(predicate.equals("A1234567Z"));
    }

    @Test
    public void equals_null_returnsFalse() {
        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("A1234567Z");
        assertFalse(predicate.equals(null));
    }

    @Test
    public void toString_validInput_returnsString() {
        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("A1234567Z");
        String expected = IdContainsQueryIdPredicate.class.getCanonicalName() + "{Query ID:=A1234567Z}";
        assertEquals(expected, predicate.toString());
    }
}
