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
import educonnect.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withTelegramHandle("@Paulice").withEmail("alice@example.com")
            .withStudentId("A0077493U")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withTags("tutorial-1").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withTelegramHandle("@benmeier")
            .withEmail("johnd@example.com").withStudentId("A1235678J")
            .withLink("https://www.google.com/")
            .withTags("tutorial-2", "strong-student").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withStudentId("A9876543N")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("heinz@example.com").withTelegramHandle("@wallstreet").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withStudentId("A1357924D")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("cornelia@example.com").withTelegramHandle("@danelia").withTags("tutorial-3").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withStudentId("A9482224Y")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("werner@example.com").withTelegramHandle("@michegan").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withStudentId("A9482427K")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("lydia@example.com").withTelegramHandle("@littletokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withStudentId("A6482442L")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("anna@example.com").withTelegramHandle("@georgie").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withStudentId("A8482424W")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("stefan@example.com").withTelegramHandle("@hoon").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withStudentId("A8482131M")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James")
            .withEmail("hans@example.com").withTelegramHandle("@idaho").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY)
            .withLink("https://en.wikipedia.org/wiki/LeBron_James").withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
            .withLink("https://en.wikipedia.org/wiki/LeBron_James").withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    // Not added to address
    public static final Student JOHHNY = new StudentBuilder().withName("Johnny Jenson").withStudentId("A9345680M")
            .withEmail("johnhojmjogn@example.com").withTelegramHandle("@johnnyjjohnny")
            .withLink("https://en.wikipedia.org/wiki/LeBron_James").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
