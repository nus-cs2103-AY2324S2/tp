package seedu.address.testutil;


import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ClassBook;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withStudentID("A1111111D").withEmail("alice@example.com")
            .withPhone("94351253")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withStudentID("A3333333D")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withStudentID("A4444444D")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withStudentID("A6666666D")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withStudentID("A7777777D")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withStudentID("A8888888D")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withStudentID("A9999999D")
            .withDate("03-05-2024", "02-05-2024").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withStudentID("A9516234E")
            .withDate("03-05-2024", "02-05-2024").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withStudentID("A9876336D")
            .withDate("03-05-2024", "02-05-2024").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withStudentID(VALID_STUDENT_ID_AMY)
            .withDate(VALID_DATE_1, VALID_DATE_2).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withStudentID(VALID_STUDENT_ID_BOB).withDate(VALID_DATE_1, VALID_DATE_2)
            .build();

    public static final Classes TYPICAL_CLASS_1 = new ClassBuilder().withCC("CS2103T").build();
    public static final Classes TYPICAL_CLASS_2 = new ClassBuilder().withCC("CS2103").build();

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

    public static ClassBook getTypicalClassBook() {
        ClassBook cb = new ClassBook();
        cb.createClass(new Classes(new CourseCode("cs2103")));
        return cb;
    }
}
