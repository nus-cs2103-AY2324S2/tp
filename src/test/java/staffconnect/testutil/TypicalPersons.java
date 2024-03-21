package staffconnect.testutil;

import static staffconnect.logic.commands.CommandTestUtil.VALID_AVAILABILITY_MON;
import static staffconnect.logic.commands.CommandTestUtil.VALID_AVAILABILITY_THUR;
import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
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
            .withPhone("94351253").withEmail("alice@example.com").withModule("CS1101S")
            .withFaculty("Computing").withVenue("123, Jurong West Ave 6, #08-111")
            .withTags("friends").withAvailabilities("mon 12:00 14:00").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withPhone("98765432").withEmail("johnd@example.com").withModule("CS1231S")
            .withFaculty("Computing").withVenue("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").withAvailabilities("tues 12:00 14:00", "wed 12:00 14:00").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withModule("CS2030S")
            .withFaculty("Computing").withVenue("wall street").build();
    public static final Person CLARA = new PersonBuilder().withName("Clara Svarog")
            .withPhone("9681384").withEmail("svarog@example.com").withModule("CS2102")
            .withFaculty("Computing").withVenue("belobog avenue")
            .withTags("classmate").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withModule("CS2040S")
            .withFaculty("Computing").withVenue("10th street")
            .withTags("friends").withAvailabilities("thurs 12:00 14:00", "fri 12:00 14:00").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com").withModule("CS2100")
            .withFaculty("Computing").withVenue("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withModule("CS2101")
            .withFaculty("Computing").withVenue("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withModule("CS2102")
            .withFaculty("Computing").withVenue("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withModule("CS2103")
            .withFaculty("Computing").withVenue("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withModule("CS2103T")
            .withFaculty("Computing").withVenue("chicago ave").build();
    // End of manually added

    public static final Person KAFKA = new PersonBuilder().withName("Kafka Apache").withPhone("9452413")
            .withEmail("apache@example.com").withModule("CS2102").withFaculty("Business")
            .withVenue("pteruges avenue").withTags("classmate").withAvailabilities("FRIDAY 12:00 14:00").build();
    public static final Person LEONARDO = new PersonBuilder().withName("Leonardo DiCaprio").withPhone("88472234")
            .withEmail("lcaprio@gmail.com").withModule("TS2237")
            .withFaculty("Arts and Social Sciences").withVenue("LT13").build();
    public static final Person MICHAEL = new PersonBuilder().withName("Michael Jackson").withPhone("92347123")
            .withEmail("heehee@gmail.com").withModule("MUA1163")
            .withFaculty("Music").withVenue("YSTCM-SR9").build();
    public static final Person NATASHA = new PersonBuilder().withName("Natasha Harrower").withPhone("8019394")
            .withEmail("harrower@example.com").withModule("CS2102")
            .withFaculty("Computing").withVenue("underworld avenue")
            .withTags("classmate").withAvailabilities("FRIDAY 12:00 14:00").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY)
            .withFaculty(VALID_FACULTY_AMY).withVenue(VALID_VENUE_AMY)
            .withTags(VALID_TAG_FRIEND).withAvailabilities(VALID_AVAILABILITY_MON).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB)
            .withFaculty(VALID_FACULTY_BOB).withVenue(VALID_VENUE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withAvailabilities(VALID_AVAILABILITY_MON, VALID_AVAILABILITY_THUR).build();

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, CLARA, DANIEL, ELLE, FIONA, GEORGE, KAFKA,
                LEONARDO, MICHAEL, NATASHA));
    }
}
