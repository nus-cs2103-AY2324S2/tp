package educonnect.testutil;

import static educonnect.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static educonnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import educonnect.model.AddressBook;
import educonnect.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTelegramHandle("@Paulice").withEmail("alice@example.com")
            .withStudentId("A0077493U")
            .withTags("tutorial-1").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTelegramHandle("@benmeier")
            .withEmail("johnd@example.com").withStudentId("A1235678J")
            .withTags("tutorial-2", "strong-student").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withStudentId("A9876543N")
            .withEmail("heinz@example.com").withTelegramHandle("@wallstreet").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withStudentId("A1357924D")
            .withEmail("cornelia@example.com").withTelegramHandle("@danelia").withTags("tutorial-3").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withStudentId("A9482224Y")
            .withEmail("werner@example.com").withTelegramHandle("@michegan").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withStudentId("A9482427K")
            .withEmail("lydia@example.com").withTelegramHandle("@littletokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withStudentId("A6482442L")
            .withEmail("anna@example.com").withTelegramHandle("@georgie").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withStudentId("A8482424W")
            .withEmail("stefan@example.com").withTelegramHandle("@hoon").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withStudentId("A8482131M")
            .withEmail("hans@example.com").withTelegramHandle("@idaho").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
