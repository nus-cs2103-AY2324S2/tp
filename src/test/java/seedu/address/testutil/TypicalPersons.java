package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTAKE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTAKE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withId("A0251893P")
            .withMajor("Computer Science").withIntake("2023")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withId("A0265901E")
            .withMajor("Physics").withIntake("2023")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withId("A3351893P")
            .withMajor("Computer Science").withIntake("2023")
            .withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withId("A1255893P")
            .withMajor("Computer Science").withIntake("2023").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withId("A7251893P")
            .withMajor("Computer Science").withIntake("2023").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withId("A9151893P")
            .withMajor("Computer Science").withIntake("2023").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withId("A9851893P")
            .withMajor("Computer Science").withIntake("2023")
            .withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withId("A0651893P")
            .withMajor("Computer Science").withIntake("2023").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withId("A0151893P")
            .withMajor("Computer Science").withIntake("2023").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    public static final Person Oliver1 = new PersonBuilder().withName("Oliver Tan").withId("A2327063Y")
            .withMajor("Computer Science").withIntake("2024").withPhone("8432131")
            .withEmail("tan@example.com").withAddress("new york ave").build();

    public static final Person Oliver2 = new PersonBuilder().withName("Oliver Pie").withId("A1237863R")
            .withMajor("Computer Science").withIntake("2022").withPhone("8481245")
            .withEmail("pie@example.com").withAddress("hk ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withId(VALID_ID_AMY)
            .withMajor(VALID_MAJOR_AMY).withIntake(VALID_INTAKE_AMY).withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withMajor(VALID_MAJOR_BOB)
            .withIntake(VALID_INTAKE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
        return new ArrayList<>(Arrays
                .asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA, Oliver1, Oliver2));
    }
}
