package tutorpro.testutil;

import static tutorpro.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static tutorpro.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tutorpro.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_LEVEL_P6;
import static tutorpro.logic.commands.CommandTestUtil.VALID_LEVEL_UNI;
import static tutorpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tutorpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tutorpro.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_SUBJECT_ENGLISH;
import static tutorpro.logic.commands.CommandTestUtil.VALID_SUBJECT_MATH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tutorpro.model.AddressBook;
import tutorpro.model.person.Person;
import tutorpro.model.person.student.Student;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withLevel("P6")
            .withSubjects("Math").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withLevel("S2").withSubjects("Finance")
            .build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withLevel("K1")
            .withSubjects("Reading").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withLevel("UNI").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withLevel("J2").build();
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withLevel("P1").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withLevel(VALID_LEVEL_P6)
            .withSubjects(VALID_SUBJECT_MATH).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withLevel(VALID_LEVEL_UNI)
            .withSubjects(VALID_SUBJECT_ENGLISH).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalStudents()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
