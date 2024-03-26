package staffconnect.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import staffconnect.model.person.Module;
import staffconnect.testutil.PersonBuilder;

public class PersonHasModulePredicateTest {

    @Test
    public void equals() {
        Module firstPredicateModule = new Module("MOD1000");
        Module secondPredicateModule = new Module("MOD2000");

        PersonHasModulePredicate firstPredicate = new PersonHasModulePredicate(firstPredicateModule);
        PersonHasModulePredicate secondPredicate = new PersonHasModulePredicate(secondPredicateModule);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasModulePredicate firstPredicateCopy = new PersonHasModulePredicate(firstPredicateModule);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different module -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personHasModule_returnsTrue() {
        // predicate set to track "MOD1000" module
        Module module = new Module("MOD1000");
        PersonHasModulePredicate predicate = new PersonHasModulePredicate(module);

        // person has "MOD1000" module
        assertTrue(predicate.test(new PersonBuilder().withModule("MOD1000").build()));
    }

    @Test
    public void test_personDoesNotHaveModule_returnsFalse() {
        // predicate set to track "MOD1000" module
        Module module = new Module("MOD1000");
        PersonHasModulePredicate predicate = new PersonHasModulePredicate(module);

        // person does not have module "MOD1000"
        assertFalse(predicate.test(new PersonBuilder().withModule("MOD2000").build()));
    }

    @Test
    public void toStringMethod() {
        Module module = new Module("MOD2000");
        PersonHasModulePredicate predicate = new PersonHasModulePredicate(module);

        String expected = PersonHasModulePredicate.class.getCanonicalName() + "{module=" + module + "}";
        assertEquals(expected, predicate.toString());
    }
}
