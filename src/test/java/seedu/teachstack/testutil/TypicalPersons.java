package seedu.teachstack.testutil;

import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_GROUP_GROUP1;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_GROUP_GROUP2B;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.teachstack.model.AddressBook;
import seedu.teachstack.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withStudentId("A0223456X")
            .withEmail("e0345678@u.nus.edu")
            .withGrade("A+")
            .withGroups("Group 1").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withStudentId("A0323456X")
            .withEmail("e1023456@u.nus.edu")
            .withGrade("A")
            .withGroups("Group 2B", "Group 1").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withStudentId("A0823456X")
            .withGrade("A-").withEmail("e1123456@u.nus.edu").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withStudentId("A0923456X").withGrade("B+").withEmail("e1234567@u.nus.edu").withGroups("Group 1").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withStudentId("A1023456X").withGrade("B").withEmail("e1345678@u.nus.edu").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withStudentId("A0123656X").withGrade("B-").withEmail("e1456789@u.nus.edu").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withStudentId("A0128956X").withGrade("C").withEmail("e0234567@u.nus.edu").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withStudentId("A1056438U").withEmail("e1034567@u.nus.edu").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withStudentId("A1078906H").withEmail("e1045678@u.nus.edu").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENTID_AMY)
            .withEmail(VALID_EMAIL_AMY).withGrade(VALID_GRADE_AMY)
            .withGroups(VALID_GROUP_GROUP2B).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENTID_BOB)
            .withEmail(VALID_EMAIL_BOB).withGrade(VALID_GRADE_BOB)
            .withGroups(VALID_GROUP_GROUP1, VALID_GROUP_GROUP2B).build();


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
