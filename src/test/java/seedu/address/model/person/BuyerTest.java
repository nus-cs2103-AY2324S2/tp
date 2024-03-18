package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class BuyerTest {
    @Test
    public void equals() {

        Buyer buyerAlice =
                new Buyer(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                        ALICE.getPostalCode(), ALICE.getTags());
        Buyer buyerBob =
                new Buyer(BOB.getName(), BOB.getPhone(), BOB.getEmail(), BOB.getAddress(),
                        BOB.getPostalCode(), BOB.getTags());

        // same object -> returns true
        assertTrue(buyerAlice.equals(buyerAlice));

        // null -> returns false
        assertFalse(buyerAlice.equals(null));

        // different type -> returns false
        assertFalse(buyerAlice.equals(5));

        // different person -> returns false
        assertFalse(buyerAlice.equals(buyerBob));
    }
}
