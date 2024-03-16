package staffconnect.testutil;

import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_VENUE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_VENUE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import staffconnect.model.StaffBook;
import staffconnect.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withVenue("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withModule("CS1101S")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withVenue("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withModule("CS1231S")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withVenue("wall street").withModule("CS2030S").build();
    public static final Person CLARA = new PersonBuilder().withName("Clara Svarog").withPhone("9681384")
            .withEmail("svarog@example.com").withVenue("belobog avenue").withModule("CS2102")
            .withTags("classmate").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withVenue("10th street").withModule("CS2040S")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withVenue("michegan ave").withModule("CS2100").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withVenue("little tokyo").withModule("CS2101").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withVenue("4th street").withModule("CS2102").build();
    public static final Person KAFKA = new PersonBuilder().withName("Kafka Apache").withPhone("9452413")
            .withEmail("apache@example.com").withVenue("pteruges avenue").withModule("CS2102")
            .withTags("classmate").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withVenue("little india").withModule("CS2103").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withVenue("chicago ave").withModule("CS2103T").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withVenue(VALID_VENUE_AMY).withModule(VALID_MODULE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withVenue(VALID_VENUE_BOB).withModule(VALID_MODULE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code StaffBook} with all the typical persons.
     */
    public static StaffBook getTypicalStaffBook() {
        StaffBook ab = new StaffBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, CLARA, DANIEL, ELLE, FIONA, GEORGE, KAFKA));
    }
}
