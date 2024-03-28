package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
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
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder() // only ALICE participates in class
            .withName("Alice Pauline")
            .withMajor("Business")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withStar(5)
            .withBolt(5)
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder()
            .withName("Benson Meier")
            .withMajor("Computer Science")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withStar(0)
            .withBolt(0)
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder() // some people have no tags
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withMajor("Business")
            .withStar(0)
            .withBolt(0).build();
    public static final Student DANIEL = new StudentBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withMajor("Math")
            .withStar(0)
            .withBolt(0)
            .withTags("friends").build();
    public static final Student ELLE = new StudentBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withMajor("Business")
            .withStar(3).build();
    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withMajor("Science")
            .withStar(0)
            .withBolt(0).build();
    public static final Student GEORGE = new StudentBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withMajor("Math")
            .withStar(5)
            .withBolt(0).build();

    // Manually added
    public static final Student HOON = new StudentBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withMajor("English")
            .withStar(0)
            .withBolt(0).build();
    public static final Student IDA = new StudentBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withMajor("QF")
            .withStar(0)
            .withBolt(0).build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withMajor(VALID_MAJOR_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withStar(0)
            .withBolt(0).build();
    public static final Student BOB = new StudentBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withMajor(VALID_MAJOR_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withStar(0)
            .withBolt(0).build();

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
