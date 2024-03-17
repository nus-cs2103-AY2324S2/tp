package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ILLNESS_GENETIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_FLU;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.note.Note;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getIllnesses().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same nric, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .withGender(VALID_GENDER_BOB)
                .withBirthDate(VALID_BIRTHDATE_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withIllnesses(VALID_ILLNESS_GENETIC).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different nric, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withNric(VALID_NRIC_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different person -> returns false
        assertNotEquals(ALICE, BOB);

        // different nric -> return false
        Person editedAlice = new PersonBuilder().withNric(VALID_NRIC_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different gender -> return false
        editedAlice = new PersonBuilder(ALICE).withGender(VALID_GENDER_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different birthdate -> return false
        editedAlice = new PersonBuilder(ALICE).withGender(VALID_GENDER_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different drugAllergy -> returns false
        editedAlice = new PersonBuilder(ALICE).withDrugAllergy(VALID_DRUG_ALLERGY_AMY).build();
        assertNotEquals(ALICE, editedAlice);

        // different illnesses -> returns false
        editedAlice = new PersonBuilder(ALICE).withIllnesses(VALID_ILLNESS_GENETIC).build();
        assertNotEquals(ALICE, editedAlice);

        // different notes -> returns false
        editedAlice = new PersonBuilder(ALICE).withNotes(new Note[] {VALID_NOTE_FLU}).build();
        assertNotEquals(ALICE, editedAlice);
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{nric=" + ALICE.getNric()
                + ", name=" + ALICE.getName()
                + ", gender=" + ALICE.getGender()
                + ", birthDate=" + ALICE.getBirthDate()
                + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail()
                + ", drugAllergy=" + ALICE.getDrugAllergy()
                + ", illnesses=" + ALICE.getIllnesses()
                + ", notes=" + ALICE.getNotes() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Nested
    class BuilderTests {
        @Test
        public void build_default_returnsPerson() {
            var builder = new Person.Builder(ALICE);
            assertEquals(ALICE, builder.build());
        }

        @Test
        public void build_updateValues_returnsPerson() {
            var builder = new Person.Builder(ALICE)
                    .setNric(BENSON.getNric())
                    .setName(BENSON.getName())
                    .setGender(BENSON.getGender())
                    .setBirthDate(BENSON.getBirthDate())
                    .setPhone(BENSON.getPhone())
                    .setEmail(BENSON.getEmail())
                    .setDrugAllergy(BENSON.getDrugAllergy())
                    .setIllnesses(BENSON.getIllnesses())
                    .setNotes(BENSON.getNotes());

            assertEquals(BENSON, builder.build());
        }
    }
}
