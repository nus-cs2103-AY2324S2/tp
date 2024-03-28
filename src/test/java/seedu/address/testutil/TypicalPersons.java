package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REFLECTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REFLECTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDIO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDIO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Exam MIDTERM = new Exam("Midterm", new Score(100));
    public static final Exam FINAL = new Exam("Final", new Score(100));
    public static final Exam QUIZ = new Exam("Quiz", new Score(100));


    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends").withMatric("A1111111A")
            .withReflection("R1").withStudio("S1")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withMatric("A2222222A")
            .withReflection("R2").withStudio("S2")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withMatric("A3333333A")
            .withReflection("R3").withStudio("S3")
            .withScores(Collections.singletonMap(MIDTERM, new Score(30))).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends").withMatric("A4444444A")
            .withReflection("R4").withStudio("S4")
            .withScores(Collections.singletonMap(MIDTERM, new Score(40))).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withMatric("A5555555A").withReflection("R5").withStudio("S5")
            .withScores(Collections.singletonMap(MIDTERM, new Score(50))).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withMatric("A6666666A").withReflection("R6").withStudio("S6")
            .withScores(Collections.singletonMap(MIDTERM, new Score(60))).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withMatric("A7777777A").withReflection("R7").withStudio("S7")
            .withScores(Collections.singletonMap(MIDTERM, new Score(70))).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withMatric("A1234567X")
            .withReflection("R5").withStudio("S3").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withMatric("A1234567X")
            .withReflection("R5").withStudio("S3").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withMatric(VALID_MATRIC_NUMBER_AMY)
            .withReflection(VALID_REFLECTION_AMY).withStudio(VALID_STUDIO_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withMatric(VALID_MATRIC_NUMBER_BOB).withReflection(VALID_REFLECTION_BOB)
            .withStudio(VALID_STUDIO_BOB).build();

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
        for (Exam exam : Arrays.asList(MIDTERM, FINAL, QUIZ)) {
            ab.addExam(exam);
        }
        return ab;
    }

    /**
     * Returns a list of typical persons.
     */
    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns the emails of all the typical persons.
     */
    public static String getTypicalPersonsEmails() {
        StringBuilder emails = new StringBuilder();
        for (Person person : getTypicalPersons()) {
            emails.append(person.getEmail().value).append("; ");
        }
        return emails.toString().trim();
    }
}
