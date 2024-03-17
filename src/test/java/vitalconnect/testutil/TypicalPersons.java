package vitalconnect.testutil;

import static vitalconnect.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vitalconnect.model.Clinic;
import vitalconnect.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
        .withNric("S1234567D")
        .withEmail("email@e.com")
        .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
        .withNric("S1234568B")
        .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
        .withNric("T1234567J").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
        .withNric("F1234567N")
        .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
        .withNric("G1234567X").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
        .withNric("M1234567K").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
        .withNric("F1234560R").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withNric("S1234569J").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withNric("S1234560G").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withNric(VALID_NRIC_AMY)
                .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();

    public static final Person BOB_CONTACT = new PersonBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB)
        .withEmail(VALID_EMAIL_BOB)
        .withPhone(VALID_PHONE_BOB)
        .withAddress(VALID_ADDRESS_BOB)
        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Clinic} with all the typical persons.
     */
    public static Clinic getTypicalClinic() {
        Clinic c = new Clinic();
        for (Person person : getTypicalPersons()) {
            c.addPerson(person);
        }
        return c;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
