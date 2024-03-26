package staffconnect.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import staffconnect.model.availability.Availability;
import staffconnect.testutil.PersonBuilder;

public class PersonHasAvailabilitiesPredicateTest {

    @Test
    public void equals() {
        Set<Availability> firstPredicateAvailability = new HashSet<Availability>(
                Arrays.asList(new Availability("mon 12:00 13:00")));
        Set<Availability> secondPredicateAvailability = new HashSet<Availability>(
                Arrays.asList(new Availability("wed 10:00 14:00")));

        PersonHasAvailabilitiesPredicate firstPredicate = new PersonHasAvailabilitiesPredicate(
                firstPredicateAvailability);
        PersonHasAvailabilitiesPredicate secondPredicate = new PersonHasAvailabilitiesPredicate(
                secondPredicateAvailability);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasAvailabilitiesPredicate firstPredicateCopy = new PersonHasAvailabilitiesPredicate(
                firstPredicateAvailability);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different availabilities -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personHasAvailability_returnsTrue() {
        // predicate set to track "mon 12:00 13:00" availability
        Set<Availability> availability = new HashSet<Availability>(Arrays.asList(new Availability("mon 12:00 13:00")));
        PersonHasAvailabilitiesPredicate predicate = new PersonHasAvailabilitiesPredicate(availability);

        // person only has availability "mon 12:00 13:00"
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("mon 12:00 13:00").build()));

        // person has multiple availabilities and has "mon 12:00 13:00"
        assertTrue(
                predicate.test(new PersonBuilder().withAvailabilities("mon 12:00 13:00", "tues 12:00 13:00").build()));

        // case-insensitivity checks
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("MOn 12:00 13:00").build()));
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("mON 12:00 13:00").build()));
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("MON 12:00 13:00").build()));
    }

    @Test
    public void test_personHasMultipleAvailabilities_returnsTrue() {
        // predicate set to track "mon 12:00 13:00", "wed 12:00 13:00" availabilities
        Set<Availability> multipleAvailabilities = new HashSet<Availability>(
                Arrays.asList(new Availability("mon 12:00 13:00"), new Availability("wed 12:00 13:00")));
        PersonHasAvailabilitiesPredicate predicate = new PersonHasAvailabilitiesPredicate(multipleAvailabilities);

        // person has multiple availabilities and has "mon 12:00 13:00", "wed 12:00
        // 13:00"
        assertTrue(
                predicate.test(new PersonBuilder().withAvailabilities("mon 12:00 13:00", "wed 12:00 13:00").build()));

        // case-insensitivity checks
        assertTrue(
                predicate.test(new PersonBuilder().withAvailabilities("mON 12:00 13:00", "WEd 12:00 13:00").build()));
        assertTrue(
                predicate.test(new PersonBuilder().withAvailabilities("MOn 12:00 13:00", "wED 12:00 13:00").build()));
        assertTrue(
                predicate.test(new PersonBuilder().withAvailabilities("MON 12:00 13:00", "wEd 12:00 13:00").build()));
    }

    @Test
    public void test_personDoesNotHaveAvailability_returnsFalse() {
        // predicate set to track "wed 12:00 13:00" availability
        Set<Availability> availability = new HashSet<Availability>(Arrays.asList(new Availability("wed 12:00 13:00")));
        PersonHasAvailabilitiesPredicate predicate = new PersonHasAvailabilitiesPredicate(availability);

        // person does not have availability "wed 12:00 13:00"
        assertFalse(predicate.test(new PersonBuilder().withAvailabilities("tues 14:00 16:00").build()));
    }

    @Test
    public void test_personDoesNotHaveMultipleAvailabilities_returnsFalse() {
        // predicate set to track "tues 12:00 14:00", "wed 14:00 16:00", "thurs 16:00
        // 18:00" availabilities
        Set<Availability> multipleAvailabilities = new HashSet<Availability>(
                Arrays.asList(new Availability("tues 12:00 14:00"), new Availability("wed 14:00 16:00"),
                        new Availability("thurs 16:00 18:00")));
        PersonHasAvailabilitiesPredicate predicate = new PersonHasAvailabilitiesPredicate(multipleAvailabilities);

        // person only has 1 availability
        assertFalse(predicate.test(new PersonBuilder().withAvailabilities("tues 12:00 14:00").build()));

        // case-insensitivity checks
        assertFalse(
                predicate.test(new PersonBuilder().withAvailabilities("tUES 12:00 14:00", "wED 14:00 16:00").build()));
        assertFalse(
                predicate.test(new PersonBuilder().withAvailabilities("TUEs 12:00 14:00", "WEd 14:00 16:00").build()));
        assertFalse(
                predicate.test(new PersonBuilder().withAvailabilities("tUeS 12:00 14:00", "wEd 14:00 16:00").build()));
    }

    @Test
    public void toStringMethod() {
        Set<Availability> availability = new HashSet<Availability>(
                Arrays.asList(new Availability("monday 12:00 13:00")));
        PersonHasAvailabilitiesPredicate predicate = new PersonHasAvailabilitiesPredicate(availability);

        String expected = PersonHasAvailabilitiesPredicate.class.getCanonicalName() + "{availabilities=" + availability
                + "}";
        assertEquals(expected, predicate.toString());
    }
}
