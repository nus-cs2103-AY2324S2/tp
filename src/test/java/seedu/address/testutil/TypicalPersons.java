package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_OUTSPOKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_SHY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

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
            .withMajor("Computer Science").withEmail("alice@example.com")
            .withYear("1").withTelegram("alicepauline")
            .withPhone("94351253").withRemark("shy")
            .withGroups("TUT10", "LAB05").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withMajor("Computer Science")
            .withYear("1").withTelegram("bensonmeier")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("always skip tutorials")
            .withGroups("TUT10", "LAB05").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563").withTelegram("carlkurz")
            .withMajor("Computer Science").withEmail("heinz@example.com").withRemark("")
            .withYear("1").withGroups("TUT10").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withMajor("Computer Engineering").withEmail("corelia@example.com")
            .withYear("1").withTelegram("danielmeier").withRemark("hardworking")
            .withGroups("TUT10").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com").withMajor("Computer Engineering")
            .withYear("2").withTelegram("ellemeyer").withRemark("strong foundations")
            .withGroups("TUT10").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com").withMajor("Business Analytics")
            .withYear("2").withTelegram("fionakunz1").withRemark("Always ask questions in class")
            .withGroups("TUT10").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com").withMajor("Business Analytics")
            .withYear("2").withTelegram("georgebest1").withRemark("")
            .withGroups("TUT10").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withYear("2").withTelegram("hoon").withMajor("Computer Science").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withYear("3").withTelegram("ida1").withMajor("Business Analytics").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withYear(VALID_YEAR_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withMajor(VALID_MAJOR_AMY).withRemark(VALID_REMARK_SHY).withGroups(VALID_GROUP_TUTORIAL).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withYear(VALID_YEAR_BOB).withTelegram(VALID_TELEGRAM_BOB)
             .withMajor(VALID_MAJOR_BOB).withRemark(VALID_REMARK_OUTSPOKEN).withGroups(VALID_GROUP_LAB,
                    VALID_GROUP_TUTORIAL)
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
