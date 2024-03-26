package staffconnect.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import staffconnect.model.person.Faculty;
import staffconnect.testutil.PersonBuilder;

public class PersonHasFacultyPredicateTest {

    @Test
    public void equals() {
        Faculty firstPredicateFaculty = new Faculty("Business");
        Faculty secondPredicateFaculty = new Faculty("Computing");

        PersonHasFacultyPredicate firstPredicate = new PersonHasFacultyPredicate(firstPredicateFaculty);
        PersonHasFacultyPredicate secondPredicate = new PersonHasFacultyPredicate(secondPredicateFaculty);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasFacultyPredicate firstPredicateCopy = new PersonHasFacultyPredicate(firstPredicateFaculty);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different faculty -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personHasFaculty_returnsTrue() {
        // predicate set to track "Business" faculty
        Faculty faculty = new Faculty("Business");
        PersonHasFacultyPredicate predicate = new PersonHasFacultyPredicate(faculty);

        // person has "Business" faculty
        assertTrue(predicate.test(new PersonBuilder().withFaculty("Business").build()));
    }

    @Test
    public void test_personDoesNotHaveFaculty_returnsFalse() {
        // predicate set to track "Business" faculty
        Faculty faculty = new Faculty("Business");
        PersonHasFacultyPredicate predicate = new PersonHasFacultyPredicate(faculty);

        // person does not have "Business" faculty
        assertFalse(predicate.test(new PersonBuilder().withFaculty("Computing").build()));
    }

    @Test
    public void toStringMethod() {
        Faculty faculty = new Faculty("Engineering");
        PersonHasFacultyPredicate predicate = new PersonHasFacultyPredicate(faculty);

        String expected = PersonHasFacultyPredicate.class.getCanonicalName() + "{faculty=" + faculty + "}";
        assertEquals(expected, predicate.toString());
    }
}
