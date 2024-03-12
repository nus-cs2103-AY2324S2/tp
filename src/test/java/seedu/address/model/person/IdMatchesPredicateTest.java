package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class IdMatchesPredicateTest {

    @Test
    void test_idMatchesPredicate_returnsTrue() {
        IdMatchesPredicate predicate = new IdMatchesPredicate(new StudentId("00001"));
        assertTrue(predicate.test(new PersonBuilder().withStudentId("00001").build()));
    }

    @Test
    void test_idMatchesPredicate_returnsFalse() {
        IdMatchesPredicate predicate = new IdMatchesPredicate(new StudentId("00001"));
        assertFalse(predicate.test(new PersonBuilder().withStudentId("00002").build()));
    }

    @Test
    void equals() {
        IdMatchesPredicate firstPredicateId = new IdMatchesPredicate(new StudentId("00001"));
        IdMatchesPredicate secondPredicateId = new IdMatchesPredicate(new StudentId("00002"));
        IdMatchesPredicate thirdPredicateId = new IdMatchesPredicate(new StudentId("00001"));

        assertTrue(firstPredicateId.equals(firstPredicateId));

        assertTrue(firstPredicateId.equals(thirdPredicateId));

        assertFalse(firstPredicateId.equals(secondPredicateId));

        assertFalse(firstPredicateId.equals(null));
    }

    @Test
    void toStringMethod() {
        String keywords = "00001";
        IdMatchesPredicate predicate = new IdMatchesPredicate(new StudentId(keywords));
        String expected = IdMatchesPredicate.class.getCanonicalName() + "{student id=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }

}
