package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MeetingBelongingToClientPredicateTest {
    @Test
    public void equals() {
        MeetingBelongingToClientPredicate firstPredicate = new MeetingBelongingToClientPredicate(ALICE);
        MeetingBelongingToClientPredicate secondPredicate = new MeetingBelongingToClientPredicate(BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingBelongingToClientPredicate firstPredicateCopy =
                new MeetingBelongingToClientPredicate(new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253")
                .withTags("friends").build());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
    @Test
    public void toStringMethod() {
        MeetingBelongingToClientPredicate predicate = new MeetingBelongingToClientPredicate(ALICE);

        String expected = ALICE.toString();
        assertEquals(expected, predicate.toString());
    }
}
