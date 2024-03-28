package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PatientList;
import seedu.address.model.patient.Patient;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Patient ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withId(1)
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Patient BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withId(2)
            .withTags("owesMoney", "friends").build();
    public static final Patient CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withId(3).build();
    public static final Patient DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withId(4).withTags("friends").build();
    public static final Patient ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822240")
            .withEmail("werner@example.com").withId(5).build();
    public static final Patient FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824270")
            .withEmail("lydia@example.com").withId(6).build();
    public static final Patient GEORGE = new PersonBuilder().withName("George Best").withPhone("94824420")
            .withEmail("anna@example.com").withId(700).build();

    // Manually added
    public static final Patient HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824240")
            .withEmail("stefan@example.com").withId(708).build();
    public static final Patient IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821310")
            .withEmail("hans@example.com").withId(709).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withId(710).withTags(VALID_TAG_FRIEND).build();
    public static final Patient BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withId(711).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code PatientList} with all the typical persons.
     */
    public static PatientList getTypicalPatientList() {
        PatientList ab = new PatientList();
        for (Patient patient : getTypicalPersons()) {
            ab.addPerson(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
