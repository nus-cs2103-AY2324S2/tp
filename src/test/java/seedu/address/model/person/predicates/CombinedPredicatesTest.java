package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CombinedPredicatesTest {
    @Test
    public void test_singlePredicate_returnsTrue() {
        Person person = new PersonBuilder().build();

        NameContainsSubstringPredicate predicate = new NameContainsSubstringPredicate(PersonBuilder.DEFAULT_NAME);

        CombinedPredicates combinedPredicate = new CombinedPredicates(predicate);

        assertTrue(combinedPredicate.test(person));
    }

    @Test
    public void test_singlePredicate_returnsFalse() {
        Person person = new PersonBuilder().build();

        NameContainsSubstringPredicate predicate = new NameContainsSubstringPredicate("invalid");

        CombinedPredicates combinedPredicate = new CombinedPredicates(predicate);

        assertFalse(combinedPredicate.test(person));
    }

    @Test
    public void test_multiplePredicates_returnsTrue() {
        Person person = new PersonBuilder().build();

        NameContainsSubstringPredicate namePredicate = new NameContainsSubstringPredicate(PersonBuilder.DEFAULT_NAME);
        PhoneContainsSubstringPredicate phonePredicate = new PhoneContainsSubstringPredicate(
                PersonBuilder.DEFAULT_PHONE);

        CombinedPredicates combinedPredicate = new CombinedPredicates(namePredicate, phonePredicate);

        assertTrue(combinedPredicate.test(person));
    }

    @Test
    public void test_multiplePredicates_returnsFalse() {
        // All mismatch
        Person person = new PersonBuilder().build();

        NameContainsSubstringPredicate namePredicate = new NameContainsSubstringPredicate("invalid");
        PhoneContainsSubstringPredicate phonePredicate = new PhoneContainsSubstringPredicate("invalid");

        CombinedPredicates combinedPredicate = new CombinedPredicates(namePredicate, phonePredicate);

        assertFalse(combinedPredicate.test(person));

        // Some match, some mismatch
        namePredicate = new NameContainsSubstringPredicate(PersonBuilder.DEFAULT_NAME);
        phonePredicate = new PhoneContainsSubstringPredicate("invalid");

        assertFalse(combinedPredicate.test(person));
    }

    @Test
    public void equals() {
        NameContainsSubstringPredicate namePredicate = new NameContainsSubstringPredicate("Alice");
        PhoneContainsSubstringPredicate phonePredicate = new PhoneContainsSubstringPredicate("11111111");

        NameContainsSubstringPredicate namePredicateClone = new NameContainsSubstringPredicate("Alice");
        PhoneContainsSubstringPredicate phonePredicateClone = new PhoneContainsSubstringPredicate("11111111");

        // Itself
        CombinedPredicates combinedPredicates = new CombinedPredicates(namePredicate);

        assertTrue(combinedPredicates.equals(combinedPredicates));

        // Single predicate - Valid equality
        assertTrue(combinedPredicates.equals(new CombinedPredicates(namePredicateClone)));

        // Multiple predicate - Valid equality
        combinedPredicates = new CombinedPredicates(namePredicate, phonePredicate);

        assertTrue(combinedPredicates.equals(new CombinedPredicates(namePredicateClone, phonePredicateClone)));

        // Single predicate - Invalid equality
        combinedPredicates = new CombinedPredicates(namePredicate);

        assertFalse(combinedPredicates.equals(new CombinedPredicates(phonePredicate)));

        // Multiple predicate - Invalid equality (partial)
        combinedPredicates = new CombinedPredicates(namePredicate, phonePredicate);
        assertFalse(combinedPredicates.equals(new CombinedPredicates(namePredicate)));

        // Multiple predicate - Invalid equality
        assertFalse(combinedPredicates
                .equals(new CombinedPredicates(namePredicate, new PhoneContainsSubstringPredicate("invalid"))));
    }
}
