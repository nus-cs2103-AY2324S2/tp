package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.BOBSUPPLIER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SupplierBuilder;

public class SupplierTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Supplier person = new SupplierBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameStaff() {
        // same object -> returns true
        assertTrue(ALICESUPPLIER.isSamePerson(ALICESUPPLIER));

        // null -> returns false
        assertFalse(ALICESUPPLIER.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new SupplierBuilder(ALICESUPPLIER).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICESUPPLIER.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new SupplierBuilder(ALICESUPPLIER).withName(VALID_NAME_BOB).build();
        assertFalse(ALICESUPPLIER.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new SupplierBuilder(BOBSUPPLIER).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOBSUPPLIER.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new SupplierBuilder(BOBSUPPLIER).withName(nameWithTrailingSpaces).build();
        assertFalse(BOBSUPPLIER.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new SupplierBuilder(ALICESUPPLIER).build();
        assertTrue(ALICESUPPLIER.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICESUPPLIER.equals(ALICESUPPLIER));

        // null -> returns false
        assertFalse(ALICESUPPLIER.equals(null));

        // different type -> returns false
        assertFalse(ALICESUPPLIER.equals(5));

        // different person -> returns false
        assertFalse(ALICESUPPLIER.equals(BOBSUPPLIER));

        // different name -> returns false
        Person editedAlice = new SupplierBuilder(ALICESUPPLIER).withName(VALID_NAME_BOB).build();
        assertFalse(ALICESUPPLIER.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new SupplierBuilder(ALICESUPPLIER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICESUPPLIER.equals(editedAlice));

        // different email -> returns false
        editedAlice = new SupplierBuilder(ALICESUPPLIER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICESUPPLIER.equals(editedAlice));

        // different address -> returns false
        editedAlice = new SupplierBuilder(ALICESUPPLIER).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICESUPPLIER.equals(editedAlice));

        /*
        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
         */
    }

    @Test
    public void toStringMethod() {
        String expected = Supplier.class.getCanonicalName() + "{name=" + ALICESUPPLIER.getName() + ", phone="
                + ALICESUPPLIER.getPhone()
                + ", email=" + ALICESUPPLIER.getEmail() + ", address="
                + ALICESUPPLIER.getAddress()
                + ", tags=" + ALICESUPPLIER.getTags()
                + ", product=" + ALICESUPPLIER.getProduct()
                + ", price=" + ALICESUPPLIER.getPrice()
                + "}";
        assertEquals(expected, ALICESUPPLIER.toString());
    }
}
