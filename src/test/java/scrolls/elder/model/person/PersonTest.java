package scrolls.elder.model.person;

import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalPersons;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(null));

        // same id, all other attributes different -> returns true
        Person editedAlice =
                new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB)
                        .withPhone(VALID_PHONE_BOB)
                        .withEmail(VALID_EMAIL_BOB)
                        .withAddress(VALID_ADDRESS_BOB)
                        .withTags(VALID_TAG_HUSBAND)
                        .build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withId(VALID_ID_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertEquals(TypicalPersons.ALICE, aliceCopy);

        // same object -> returns true
        Assertions.assertEquals(TypicalPersons.ALICE, TypicalPersons.ALICE);

        // null -> returns false
        Assertions.assertNotEquals(null, TypicalPersons.ALICE);

        // different type -> returns false
        Assertions.assertNotEquals(5, TypicalPersons.ALICE);

        // different person -> returns false
        Assertions.assertNotEquals(TypicalPersons.ALICE, TypicalPersons.BOB);

        // different name -> returns false
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // different address -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertNotEquals(TypicalPersons.ALICE, editedAlice);
    }

    @Test
    public void toStringMethod() {
        String expected = Volunteer.class.getCanonicalName() + "{name=" + TypicalPersons.ALICE.getName() + ", phone="
                + TypicalPersons.ALICE.getPhone()
                + ", email=" + TypicalPersons.ALICE.getEmail() + ", address=" + TypicalPersons.ALICE.getAddress()
                + ", tags=" + TypicalPersons.ALICE.getTags() + ", role=" + TypicalPersons.ALICE.getRole() + ", pairedWith=" + (TypicalPersons.ALICE.getPairedWith() == null ? "None" : TypicalPersons.ALICE.getPairedWith().getName()) + "}";
        Assertions.assertEquals(expected, TypicalPersons.ALICE.toString());
    }
}
