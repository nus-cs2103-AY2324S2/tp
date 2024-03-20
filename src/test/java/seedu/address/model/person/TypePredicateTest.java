package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.HOON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TypePredicateTest {
    private static final TypePredicate TEST_PREDICATE_CLIENT = new TypePredicate("client");
    private static final TypePredicate TEST_PREDICATE_CLIENT_2 = new TypePredicate("client");
    private static final TypePredicate TEST_PREDICATE_HOUSEKEEPER = new TypePredicate("housekeeper");

    @Test
    public void testTest() {
        //Person is a client and type predicate is "client" -> return true
        assertTrue(TEST_PREDICATE_CLIENT.test(AMY));

        //Person is a client and type predicate is "housekeeper" -> return false
        assertFalse(TEST_PREDICATE_HOUSEKEEPER.test(AMY));
    }

    @Test
    public void equalsTest() {
        // same type value -> returns true
        assertTrue(TEST_PREDICATE_CLIENT.equals(TEST_PREDICATE_CLIENT_2));

        // same object -> returns true
        assertTrue(TEST_PREDICATE_HOUSEKEEPER.equals(TEST_PREDICATE_HOUSEKEEPER));

        // null -> returns false
        assertFalse(TEST_PREDICATE_CLIENT.equals(null));

        // different type -> returns false
        assertFalse(TEST_PREDICATE_CLIENT_2.equals(5));

        // different object -> returns false
        assertFalse(TEST_PREDICATE_CLIENT.equals(TEST_PREDICATE_HOUSEKEEPER));
    }

    @Test
    public void toStringTest() {
        // prints correct type (client) -> return true
        assertTrue(TEST_PREDICATE_CLIENT.toString().equals("seedu.address.model.person.TypePredicate{type=client}"));

        // prints correct type (housekeeper) -> return true
        assertTrue(TEST_PREDICATE_HOUSEKEEPER.toString().equals("seedu.address.model.person.TypePredicate{type=housekeeper}"));

        //prints wrong type -> return false
        assertFalse(TEST_PREDICATE_CLIENT_2.toString().equals("seedu.address.model.person.TypePredicate{type=housekeeper}"));

        // prints wrong string -> return false
        assertFalse(TEST_PREDICATE_CLIENT.toString().equals("{type=client}"));
    }
}
