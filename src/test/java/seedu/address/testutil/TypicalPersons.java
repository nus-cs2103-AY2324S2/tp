package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TWO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TWO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

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
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withFirstParentPhone("94351253")
            .withSecondParentPhone("88312868")
            .withStudentId("00001")
            .withTags("Friends").withClass("6 A").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withFirstParentPhone("98765432")
            .withSecondParentPhone("83789072")
            .withStudentId("00002")
            .withTags("OwesMoney", "Friends").withClass("6 A").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withFirstParentPhone("95352563")
            .withSecondParentPhone("86070439")
            .withEmail("heinz@example.com")
            .withAddress("Wall Street")
            .withStudentId("00003").withClass("6 A").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withFirstParentPhone("87652533")
            .withSecondParentPhone("93690682")
            .withEmail("cornelia@example.com")
            .withStudentId("00004")
            .withAddress("10th street")
            .withTags("Friends").withClass("6 A").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withFirstParentPhone("94822224")
            .withSecondParentPhone("93846702")
            .withStudentId("00005")
            .withEmail("werner@example.com")
            .withAddress("Michegan Ave").withClass("6 A").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withFirstParentPhone("94821427")
            .withSecondParentPhone("85777773")
            .withStudentId("00006")
            .withEmail("lydia@example.com")
            .withAddress("Little Tokyo").withClass("6 A").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withFirstParentPhone("94822442")
            .withSecondParentPhone("92136499")
            .withStudentId("00007")
            .withEmail("anna@example.com")
            .withAddress("4th Street").withClass("6 A").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withFirstParentPhone("84842424")
            .withSecondParentPhone("89058882")
            .withStudentId("00008")
            .withEmail("stefan@example.com")
            .withAddress("Little India").withClass("6 A").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withFirstParentPhone("84821531")
            .withSecondParentPhone("90434552")
            .withStudentId("00009")
            .withEmail("hans@example.com")
            .withAddress("Chicago Ave").withClass("6 A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withFirstParentPhone(VALID_PHONE_ONE_AMY)
            .withSecondParentPhone(VALID_PHONE_TWO_AMY)
            .withStudentId(VALID_STUDENT_ID_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withClass("6 A")
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withFirstParentPhone(VALID_PHONE_ONE_BOB)
            .withSecondParentPhone(VALID_PHONE_TWO_BOB)
            .withStudentId(VALID_STUDENT_ID_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withClass("6 A")
            .build();

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
