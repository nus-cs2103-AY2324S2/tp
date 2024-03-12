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
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PatientBuilder().withNric("S1234567A").withName("Alice Pauline")
            .withDoB("2000-01-03").withPhone("94351253").build();
    public static final Person BENSON = new PatientBuilder().withNric("S8734985A").withName("Benson Chen")
            .withDoB("2002-01-03").withPhone("88927639").build();

    public static final Person CARL = new PatientBuilder().withNric("S2378593A").withName("Carl Sim")
            .withDoB("2005-01-03").withPhone("87436749").build();

    public static final Patient AMY = new PatientBuilder().withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY)
            .withDoB(VALID_DOB_AMY).withPhone(VALID_PHONE_AMY).build();
    public static final Patient BOB = new PatientBuilder().withNric(VALID_NRIC_BOB).withName(VALID_NAME_BOB)
            .withDoB(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB).build();

    // Doctors

    public static final Person JAMES = new DoctorBuilder().withNric("S1234567A").withName("Alice Pauline")
            .withDoB("2000-01-03").withPhone("94351253").build();
    public static final Person ERIC = new DoctorBuilder().withNric("S8734985A").withName("Benson Chen")
            .withDoB("2002-01-03").withPhone("88927639").build();

    public static final Person BROWN = new DoctorBuilder().withNric("S2378593A").withName("Carl Sim")
            .withDoB("2005-01-03").withPhone("87436749").build();

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL));
    }
}
