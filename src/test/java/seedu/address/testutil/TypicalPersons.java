package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

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
            .withClassGroup("A-1").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTelegram("@alicepauline").withGithub("alicep").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withClassGroup("A-1").withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTelegram("@bensonmeier").withGithub("meierbenson").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withClassGroup("A-2").withEmail("heinz@example.com")
            .withPhone("95352563")
            .withTelegram("@carlkurz").withGithub("kurzcarl").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withClassGroup("A-2").withEmail("cornelia@example.com")
            .withPhone("87652533")
            .withTelegram("@danielmeier").withGithub("meierdaniel").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withClassGroup("B-1").withEmail("werner@example.com")
            .withPhone("9482224")
            .withTelegram("@ellemeyer").withGithub("meyerelle").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withClassGroup("B-1").withEmail("lydia@example.com")
            .withPhone("9482427")
            .withTelegram("@fionakunz").withGithub("kunzfiona").build();

    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withClassGroup("B-2").withEmail("anna@example.com")
            .withPhone("9482442")
            .withTelegram("@georgebest").withGithub("bestgeorge").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withClassGroup("C-1").withEmail("stefan@example.com")
            .withPhone("8482424")
            .withTelegram("@hoonmeier").withGithub("meierhoon").build();

    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withClassGroup("C-2").withEmail("hans@example.com")
            .withPhone("8482131")
            .withTelegram("@idamueller").withGithub("muellerida").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withClassGroup(VALID_CLASS_GROUP_AMY).withEmail(VALID_EMAIL_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withTelegram(VALID_TELEGRAM_AMY).build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withClassGroup(VALID_CLASS_GROUP_BOB).withEmail(VALID_EMAIL_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withTelegram(VALID_TELEGRAM_BOB).withGithub(VALID_GITHUB_BOB).build();

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
