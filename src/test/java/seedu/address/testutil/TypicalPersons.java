package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADMISSION_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADMISSION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FALL_RISK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTags("Diabetes").withDob("20/02/2000").withIc("A1234567B")
            .withAdmissionDate("01/03/2024").withWard("A1").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("Dementia", "FallRisk").withDob("20/02/2001")
            .withIc("A2345678B").withAdmissionDate("01/02/2024").withWard("B1").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withDob("20/02/2002")
            .withIc("A3456789B").withAdmissionDate("05/03/2024").withWard("C1").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withTags("HearingImpaired")
            .withDob("20/02/2003").withIc("A5678901B").withAdmissionDate("10/03/2024")
            .withWard("D1").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withDob("20/02/2004")
            .withIc("A4756976B").withAdmissionDate("05/02/2024").withWard("E1").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withDob("20/02/2006")
            .withIc("A5739485B").withAdmissionDate("11/03/2024").withWard("F1").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withDob("20/05/2000")
            .withIc("A0987654B").withAdmissionDate("16/03/2024").withWard("G1").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_DIABETES)
            .withDob(VALID_DOB_AMY).withIc(VALID_IC_AMY).withAdmissionDate(VALID_ADMISSION_DATE_AMY)
            .withWard(VALID_WARD_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_DIABETES, VALID_TAG_FALL_RISK)
            .withDob(VALID_DOB_BOB).withIc(VALID_IC_BOB).withAdmissionDate(VALID_ADMISSION_DATE_BOB)
            .withWard(VALID_WARD_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
