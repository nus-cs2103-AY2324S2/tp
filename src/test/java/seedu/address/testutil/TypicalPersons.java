package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PatientBuilder().withNric("S1234567A").withName("Alice Pauline")
            .withDoB("2000-01-03").withPhone("94351253").build();

    public static final Person BENSON = new PatientBuilder().withNric("S8734985A").withName("Benson Chen")
            .withDoB("2002-01-03").withPhone("88927639").build();
    public static final Person CAM = new PatientBuilder().withNric("S8834985A").withName("CAM SENG")
            .withDoB("2002-01-03").withPhone("88927639").build();
    public static final Person DANNY = new PatientBuilder().withNric("S0734985A").withName("DANNY WOH")
            .withDoB("2002-01-03").withPhone("88927639").build();

    //Patients

    public static final Person CARL = new PatientBuilder().withNric("S2378593A").withName("Carl Sim")
            .withDoB("2005-01-03").withPhone("87436749").build();
    public static final Person AMY = new PatientBuilder().withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY)
            .withDoB(VALID_DOB_AMY).withPhone(VALID_PHONE_AMY).build();
    public static final Person BOB = new PatientBuilder().withNric(VALID_NRIC_BOB).withName(VALID_NAME_BOB)
            .withDoB(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB).build();
    public static final Person CARL_GOH = new PatientBuilder().withNric("S1234343B").withName("Carl Goh")
            .withDoB("2001-02-24").withPhone("98173241").build();

    // Doctors

    public static final Person BROWN = new DoctorBuilder().withNric("S2378593A").withName("Brown Goh")
            .withDoB("2005-01-03").withPhone("87436749").build();
    public static final Person DAMES = new DoctorBuilder().withNric("S1234367A").withName("Dames Tan")
            .withDoB("2020-01-03").withPhone("94351223").build();
    public static final Person DAMES_GOH = new DoctorBuilder().withNric("S1124133A").withName("Dames Goh")
            .withDoB("2010-01-04").withPhone("94353243").build();
    public static final Person ERIN = new DoctorBuilder().withNric("T0232948D").withName("Erin Cher")
            .withDoB("2002-01-04").withPhone("98765433").build();
    public static final Person FROWN = new DoctorBuilder().withNric("S2378493A").withName("Frown Lee")
            .withDoB("2004-01-03").withPhone("87433749").build();
    public static final Person GON = new DoctorBuilder().withNric("S2378393A").withName("Gon Tan")
            .withDoB("2005-01-02").withPhone("87432349").build();

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

    /**
     * Returns an {@code AddressBook} with shorter list of typical persons.
     */
    public static AddressBook getShortTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getShortTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(
                ALICE,
                BENSON,
                BOB,
                CARL,
                CARL_GOH,
                DAMES,
                DAMES_GOH,
                ERIN,
                GON
        ));
    }

    public static List<Person> getShortTypicalPersons() {
        return new ArrayList<>(Arrays.asList(
                ALICE,
                BENSON
        ));
    }

}
