package seedu.address.testutil;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ImmuniMate;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withNric("T0139571B").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withPhone("94351253")
            .withSex("F").withStatus("HEALTHY").build();
    public static final Person BENSON = new PersonBuilder().withNric("T0439571C").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withPhone("98765432")
            .withSex("M").withStatus("UNWELL").build();
    public static final Person CARL = new PersonBuilder().withNric("T0284994B").withName("Carl Kurz")
            .withPhone("95352563").withAddress("wall street")
            .withSex("M").withStatus("PENDING").build();
    public static final Person DANIEL = new PersonBuilder().withNric("S9839571A")
            .withName("Daniel Meier").withPhone("87652533")
            .withAddress("10th street").withSex("M").withStatus("HEALTHY").build();
    public static final Person ELLE = new PersonBuilder().withNric("S8913957B")
            .withName("Elle Meyer").withPhone("94822224")
            .withAddress("michegan ave").withSex("F").withStatus("UNWELL").build();
    public static final Person FIONA = new PersonBuilder().withNric("T0536171Z")
            .withName("Fiona Kunz").withPhone("94824427")
            .withAddress("little tokyo").withSex("F").withStatus("PENDING").build();
    public static final Person GEORGE = new PersonBuilder().withNric("T0829102Z")
            .withName("George Best").withPhone("94824442")
            .withAddress("4th street").withSex("M").withStatus("HEALTHY").build();
    //TODO add date of birth and some other optional fields

    // Manually added
    public static final Person HOON = new PersonBuilder().withNric("T0123071C").withName("Hoon Meier")
            .withPhone("84824424").withAddress("little india")
            .withSex("M").withStatus("HEALTHY").build();
    public static final Person IDA = new PersonBuilder().withNric("T0239521A").withName("Ida Mueller")
            .withPhone("84821131").withAddress("chicago ave")
            .withSex("F").withStatus("PENDING").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY)
            .withSex(VALID_SEX_AMY).withStatus(VALID_STATUS_AMY).build();
    public static final Person BOB = new PersonBuilder().withNric(VALID_NRIC_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
            .withSex(VALID_SEX_BOB).withStatus(VALID_STATUS_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ImmuniMate getTypicalAddressBook() {
        ImmuniMate ab = new ImmuniMate();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
