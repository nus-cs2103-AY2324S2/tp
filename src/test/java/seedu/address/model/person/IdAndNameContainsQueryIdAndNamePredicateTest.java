package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdAndNameContainsQueryIdAndNamePredicateTest {

    @Test
    public void test_idAndNameContainsQueryIdsAndNames_returnsTrue() {
        // Match
        IdAndNameContainsQueryIdAndNamePredicate predicate =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John Doe");
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567X").withName("John Doe").build()));

        // Only partially matching id and name
        predicate = new IdAndNameContainsQueryIdAndNamePredicate("A123", "Joh");
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567X").withName("John Doe").build()));

        // Mixed-case keywords
        predicate = new IdAndNameContainsQueryIdAndNamePredicate("a1234567x", "JoHn dOe");
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567X").withName("John Doe").build()));
    }

    @Test
    public void test_idAndNameDoesNotContainQueryIdsAndNames_returnsFalse() {
        // Not matching id
        IdAndNameContainsQueryIdAndNamePredicate predicate =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        assertFalse(predicate.test(new PersonBuilder().withId("B9876543Y").withName("John").build()));

        // Not matching name
        predicate = new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        assertFalse(predicate.test(new PersonBuilder().withId("A1234567X").withName("Joh").build()));

        // Matching substring but not in chronological order
        predicate = new IdAndNameContainsQueryIdAndNamePredicate("A123", "n Doe");
        assertFalse(predicate.test(new PersonBuilder().withId("A1234567X").withName("John Doe").build()));

        predicate = new IdAndNameContainsQueryIdAndNamePredicate("34567X", "John Doe");
        assertFalse(predicate.test(new PersonBuilder().withId("A1234567X").withName("John Doe").build()));
    }
    @Test
    public void equals_sameObject_returnsTrue() {
        IdAndNameContainsQueryIdAndNamePredicate predicate =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        IdAndNameContainsQueryIdAndNamePredicate predicate1 =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        IdAndNameContainsQueryIdAndNamePredicate predicate2 =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        IdAndNameContainsQueryIdAndNamePredicate predicate1 =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        IdAndNameContainsQueryIdAndNamePredicate predicate2 =
                new IdAndNameContainsQueryIdAndNamePredicate("B9876543Y", "Doe");
        assertFalse(predicate1.equals(predicate2));

        predicate1 = new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        predicate2 = new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "Doe");
        assertFalse(predicate1.equals(predicate2));

        predicate1 = new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        predicate2 = new IdAndNameContainsQueryIdAndNamePredicate("B9876543Y", "John");
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentClasses_returnsFalse() {
        IdAndNameContainsQueryIdAndNamePredicate predicate =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        assertFalse(predicate.equals("A1234567X"));
    }

    @Test
    public void equals_null_returnsFalse() {
        IdAndNameContainsQueryIdAndNamePredicate predicate =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        assertFalse(predicate.equals(null));
    }

    @Test
    public void toString_validInput_returnsString() {
        IdAndNameContainsQueryIdAndNamePredicate predicate =
                new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        String expected = IdAndNameContainsQueryIdAndNamePredicate.class
                .getCanonicalName() + "{Query ID & Name: =[A1234567X, John]}";
        assertEquals(expected, predicate.toString());
    }

}
