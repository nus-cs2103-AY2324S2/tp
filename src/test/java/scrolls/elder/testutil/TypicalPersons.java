package scrolls.elder.testutil;

import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ROLE_BEFRIENDEE;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ROLE_VOLUNTEER;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scrolls.elder.model.AddressBook;
import scrolls.elder.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withId(0).withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRole("volunteer")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withId(1).withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withRole("volunteer")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withId(2).withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withRole("volunteer").build();
    public static final Person DANIEL = new PersonBuilder().withId(3).withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withRole("volunteer").build();
    public static final Person ELLE = new PersonBuilder().withId(4).withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withRole("befriendee").build();
    public static final Person FIONA = new PersonBuilder().withId(5).withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withRole("befriendee").build();
    public static final Person GEORGE = new PersonBuilder().withId(6).withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withRole("befriendee").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withRole("volunteer").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRole("befriendee").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    // AMY has role VOLUNTEER
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withRole(VALID_ROLE_VOLUNTEER).build();

    // BOB has role BEFRIENDEE
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withRole(VALID_ROLE_BEFRIENDEE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook(0);
        for (Person person : getTypicalPersons()) {
            person.setId(ab.getGlobalId());
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
