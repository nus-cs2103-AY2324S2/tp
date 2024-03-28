package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUSID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withNusId("E0123456").withName("Alice Pauline")
            .withTag("Student").withEmail("alice@example.com")
            .withPhone("94351253").withGroups("friends")
            .withSchedule("10-09-2020").withRemark("").build();
    public static final Person BENSON = new PersonBuilder().withNusId("E9682156").withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTag("Student").withGroups("owesMoney", "friends")
            .withSchedule("01-01-2020").withRemark("Consultation at 3pm").build();
    public static final Person CARL = new PersonBuilder().withNusId("E0358435").withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com")
            .withTag("TA").withSchedule("02-01-2020").withRemark("").build();
    public static final Person DANIEL = new PersonBuilder().withNusId("E1237864").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withTag("Professor").withGroups("friends")
            .withSchedule("03/01/2020").withRemark("Lunch").build();
    public static final Person ELLE = new PersonBuilder().withNusId("E6546506").withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com")
            .withTag("TA").withSchedule("09/09/2020").withRemark("").build();
    public static final Person FIONA = new PersonBuilder().withNusId("E5419832").withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com").withTag("None")
            .withSchedule("04/01/2020").withRemark("Meet at MRT").build();
    public static final Person GEORGE = new PersonBuilder().withNusId("E3548135").withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withTag("Student")
            .withSchedule("01-01-2020").withRemark("").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withNusId("E3518132").withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com").withTag("Student")
            .withSchedule("08-08-2020").withRemark("").build();
    public static final Person IDA = new PersonBuilder().withNusId("E9842465").withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com").withTag("None")
            .withSchedule("05-01-2024").withRemark("Project meeting").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withNusId(VALID_NUSID_AMY).withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withTag(VALID_TAG_AMY).withGroups(VALID_GROUP_FRIEND)
            .withSchedule(VALID_SCHEDULE_AMY).withRemark(VALID_REMARK_AMY).build();
    public static final Person BOB = new PersonBuilder().withNusId(VALID_NUSID_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withTag(VALID_TAG_BOB).withGroups(VALID_GROUP_HUSBAND, VALID_GROUP_FRIEND)
            .withSchedule(VALID_SCHEDULE_BOB).withRemark(VALID_REMARK_BOB).build();

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
