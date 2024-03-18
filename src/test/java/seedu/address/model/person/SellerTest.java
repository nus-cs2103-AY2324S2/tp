package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class SellerTest {
    @Test
    public void equals() {

        Seller sellerAlice =
                new Seller(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                        ALICE.getPostalCode(), ALICE.getTags());
        Seller sellerBob =
                new Seller(BOB.getName(), BOB.getPhone(), BOB.getEmail(), BOB.getAddress(),
                        BOB.getPostalCode(), BOB.getTags());

        // same object -> returns true
        assertTrue(sellerAlice.equals(sellerAlice));

        // null -> returns false
        assertFalse(sellerAlice.equals(null));

        // different type -> returns false
        assertFalse(sellerAlice.equals(5));

        // different person -> returns false
        assertFalse(sellerAlice.equals(sellerBob));
    }
}
