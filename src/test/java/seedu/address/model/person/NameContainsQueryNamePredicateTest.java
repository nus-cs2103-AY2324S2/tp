package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
public class NameContainsQueryNamePredicateTest {

    @Test
    public void test_nameContainsQueryNames_returnsTrue() {
        // One keyword
        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate("John");
        assertTrue(predicate.test(new PersonBuilder().withName("John Doe").build()));

        // Multiple keywords
        predicate = new NameContainsQueryNamePredicate("John Doe");
        assertTrue(predicate.test(new PersonBuilder().withName("John Doe").build()));

        // Only partially matching keyword
        predicate = new NameContainsQueryNamePredicate("John");
        assertTrue(predicate.test(new PersonBuilder().withName("Johnathan Doe").build()));

        // Mixed-case keywords
        predicate = new NameContainsQueryNamePredicate("jOhN DOe");
        assertTrue(predicate.test(new PersonBuilder().withName("John Doe").build()));
    }

    @Test
    public void test_nameDoesNotContainQueryNames_returnsFalse() {
        // Zero keywords
        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate("John");
        assertFalse(predicate.test(new PersonBuilder().withName("Marry Jane").build()));

        // Matching substring but not in chronological order
        predicate = new NameContainsQueryNamePredicate("nathan");
        assertFalse(predicate.test(new PersonBuilder().withName("Johnathan Doe").build()));

        // Matching ID
        predicate = new NameContainsQueryNamePredicate("A1234567X");
        assertFalse(predicate.test(new PersonBuilder().withId("A1234567X").build()));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate("John");
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        NameContainsQueryNamePredicate predicate1 = new NameContainsQueryNamePredicate("John");
        NameContainsQueryNamePredicate predicate2 = new NameContainsQueryNamePredicate("John");
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        NameContainsQueryNamePredicate predicate1 = new NameContainsQueryNamePredicate("John");
        NameContainsQueryNamePredicate predicate2 = new NameContainsQueryNamePredicate("Doe");
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void equals_null_returnsFalse() {
        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate("John");
        assertFalse(predicate.equals(null));
    }

    @Test
    public void toString_validInput_returnsString() {
        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate("John");
        String expected = NameContainsQueryNamePredicate.class.getCanonicalName() + "{Query Name: =John}";
        assertEquals(expected, predicate.toString());
    }

}
