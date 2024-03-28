package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREE_TIME_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREE_TIME_TAG_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOMNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOMNUMBER_BOB;
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
            .withRoomNumber("nw-12-12").withTelegram("alicePauline").withBirthday("23/12/1990")
            .withEmail("alice@example.com")
            .withPhone("94351253").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withRoomNumber("WO-02-25").withTelegram("bensonMeier").withBirthday("25/02/2001")
            .withEmail("johnd@example.com").withPhone("98765432").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withRoomNumber("IW-03-12").withTelegram("carlKurz")
            .withBirthday("12/04/1995").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withRoomNumber("an-10-10").withTelegram("danielMeier")
            .withBirthday("10/10/2002").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822243")
            .withEmail("werner@example.com").withRoomNumber("tt-02-12B").withTelegram("elleMeyer")
            .withBirthday("14/02/1999").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94822427")
            .withEmail("lydia@example.com").withRoomNumber("od-05-12S").withTelegram("fionaKunz")
            .withBirthday("12/05/2002").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824142")
            .withEmail("anna@example.com").withRoomNumber("ie-23-12B").withTelegram("georgeBest")
            .withBirthday("22/12/1997").withFreeTimeTags("Mon:1000-1400").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84812424")
            .withEmail("stefan@example.com").withRoomNumber("wk-02-32A").withTelegram("hoonMeier")
            .withBirthday("06/01/1992").withFreeTimeTags("Mon:1000-1400").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821311")
            .withEmail("hans@example.com").withRoomNumber("kl-01-32D").withTelegram("idaMueller")
            .withBirthday("09/09/1998").withFreeTimeTags("Mon:1000-1400").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRoomNumber(VALID_ROOMNUMBER_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY).withFreeTimeTags(VALID_FREE_TIME_TAG_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRoomNumber(VALID_ROOMNUMBER_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withBirthday(VALID_BIRTHDAY_BOB).withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build();

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
