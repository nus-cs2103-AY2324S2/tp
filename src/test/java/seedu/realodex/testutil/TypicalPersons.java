package seedu.realodex.testutil;

import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_AMY_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_BOB_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withIncome("10000")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withFamily("4")
            .withTags("buyer")
            .withRemark("this is a remark").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withIncome("20000")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withFamily("4")
            .withTags("seller", "buyer").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withIncome("30000")
            .withEmail("heinz@example.com").withAddress("wall street").withTags("buyer")
            .withRemark("Carl was supposed to start with a K, but the doctor misspelled when he was born").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withIncome("40000")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("buyer")
            .withRemark("White VANS").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withIncome("50000")
            .withEmail("werner@example.com").withAddress("michegan ave").withTags("buyer").build();

    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withIncome("60000")
            .withEmail("lydia@example.com").withAddress("little tokyo").withTags("seller").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withIncome("70000")
            .withEmail("anna@example.com").withAddress("4th street").withTags("buyer")
            .withRemark("Fun fact: George's brother's name is \"The\".").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withTags("buyer").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withTags("buyer").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY_NAME_CAPS = new PersonBuilder().withName(VALID_NAME_AMY_FIRST_LETTER_CAPS)
            .withPhone(VALID_PHONE_AMY)
            .withIncome(VALID_INCOME_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withFamily(VALID_FAMILY_AMY)
            .withTags(VALID_TAG_AMY).build();

    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB_FIRST_LETTER_CAPS)
            .withPhone(VALID_PHONE_BOB)
            .withIncome(VALID_INCOME_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withFamily(VALID_FAMILY_BOB)
            .withTags(VALID_TAG_BOB)
            .withRemark(VALID_REMARK_BOB)
            .build();

    public static final String KEYPHRASE_MATCHING_MEIER = "Meier"; // A keyphrase that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Realodex} with all the typical persons.
     */
    public static Realodex getTypicalRealodex() {
        Realodex ab = new Realodex();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
