package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ContactList;
import seedu.address.model.coursemate.CourseMate;

/**
 * A utility class containing a list of {@code CourseMate} objects to be used in tests.
 */
public class TypicalCourseMates {
    public static final CourseMate A = new CourseMateBuilder().withName("a")
            .withEmail("a@example.com")
            .withPhone("32183127")
            .withSkills("JS")
            .withTelegramHandle("")
            .withRating("0").build();
    public static final CourseMate ALICE = new CourseMateBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withSkills("Java")
            .withTelegramHandle("alicep")
            .withRating("0").build();
    public static final CourseMate BENSON = new CourseMateBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withSkills("React")
            .withTelegramHandle("bensonm")
            .withRating("5").build();
    public static final CourseMate CARL = new CourseMateBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com")
            .withTelegramHandle("carlk")
            .withRating("4").build();
    public static final CourseMate DANIEL = new CourseMateBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withSkills("C")
            .withTelegramHandle("danielm")
            .withRating("3").build();
    public static final CourseMate ELLE = new CourseMateBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withTelegramHandle("ellem")
            .withRating("5").build();
    public static final CourseMate FIONA = new CourseMateBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withTelegramHandle("")
            .withRating("4").build();
    public static final CourseMate GEORGE = new CourseMateBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withTelegramHandle("")
            .withRating("0").build();

    // Manually added
    public static final CourseMate HOON = new CourseMateBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final CourseMate IDA = new CourseMateBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - CourseMate's details found in {@code CommandTestUtil}.
    // Enforce that they are not in the typicalCourseMates
    public static final CourseMate AMY = new CourseMateBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withSkills(VALID_SKILL_CPP).build();
    public static final CourseMate BOB = new CourseMateBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withSkills(VALID_SKILL_JAVA, VALID_SKILL_CPP)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCourseMates() {} // prevents instantiation

    /**
     * Returns an {@code ContactList} with all the typical course mates.
     */
    public static ContactList getTypicalContactList() {
        ContactList ab = new ContactList();
        for (CourseMate courseMate : getTypicalCourseMates()) {
            ab.addCourseMate(courseMate);
        }
        return ab;
    }

    public static List<CourseMate> getTypicalCourseMates() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
