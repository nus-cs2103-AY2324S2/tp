package tutorpro.model.person;

import static org.junit.jupiter.api.Assertions.fail;
import static tutorpro.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tutorpro.model.tag.Tag;
import tutorpro.testutil.Assert;
import tutorpro.testutil.PersonBuilder;
import tutorpro.testutil.StudentBuilder;
import tutorpro.testutil.TypicalPersons;

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

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(TypicalPersons.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalPersons.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(5));

        // different person -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + TypicalPersons.ALICE.getName()
                + ", phone=" + TypicalPersons.ALICE.getPhone() + ", email=" + TypicalPersons.ALICE.getEmail()
                + ", address=" + TypicalPersons.ALICE.getAddress() + ", tags=" + TypicalPersons.ALICE.getTags() + "}";
        Assertions.assertEquals(expected, TypicalPersons.ALICE.toString());
    }

    @Test
    public void addTags() {
        Person person = new PersonBuilder().build();
        Tag tag = new Tag("TAG");
        person.addTags(tag);
        Assertions.assertTrue(person.getTags().contains(tag));
    }

    @Test
    public void getCard() {
        try {
            new StudentBuilder().build().getCard(1);
            fail();
        } catch (ExceptionInInitializerError e) {
            return;
        } catch (NoClassDefFoundError e) {
            return;
        }
    }
}
