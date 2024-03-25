package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PinTest {
    @Test
    public void constructor_invalidPinConstructor_throwsIllegalArgumentException() {
        String invalidArg = "";

        assertThrows(IllegalArgumentException.class, () -> new Product(invalidArg));
    }

    @Test
    public void sucessfulSetPin() {
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        editedAlice.toPin();

        assertTrue(editedAlice.isPinned());
    }

    @Test
    public void sucessfuSetUnpin() {
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        editedAlice.toUnpin();

        assertFalse(editedAlice.isPinned());
    }

    @Test
    public void sucessfulGetPin() {
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        editedAlice.toPin();

        assertTrue(editedAlice.getPin().getIsPinned());

        assertTrue(editedAlice.getPin().toString().equals("true"));
    }
}
