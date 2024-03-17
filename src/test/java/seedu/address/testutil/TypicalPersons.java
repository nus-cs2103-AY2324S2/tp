package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BANKDETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BANKDETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENTTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENTTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRSTNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRSTNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WAITER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORK_HOURS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORK_HOURS_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
        .withFirstName("Alice")
        .withLastName("Pauline")
        .withSex("f")
        .withEmploymentType("ft")
        .withAddress("123, Jurong West Ave 6, #08-111")
        .withPhone("94351253")
        .withTags("friends")
        .build();
    public static final Person BENSON = new PersonBuilder()
        .withFirstName("Benson")
        .withLastName("Meier")
        .withSex("m")
        .withEmploymentType("pt")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withPhone("98765432")
        .withTags("owesMoney", "friends")
        .build();
    public static final Person CARL = new PersonBuilder()
        .withFirstName("Carl")
        .withLastName("Kurz")
        .withPhone("95352563")
        .withSex("m")
        .withEmploymentType("ft")
        .withAddress("wall street")
        .build();
    public static final Person DANIEL = new PersonBuilder()
        .withFirstName("Daniel")
        .withLastName("Meier")
        .withPhone("87652533")
        .withSex("m")
        .withEmploymentType("ft")
        .withAddress("10th street")
        .withTags("friends")
        .build();
    public static final Person ELLE = new PersonBuilder()
        .withFirstName("Elle")
        .withLastName("Meyer")
        .withPhone("94823224")
        .withSex("f")
        .withEmploymentType("ft")
        .withAddress("michegan ave")
        .build();
    public static final Person FIONA = new PersonBuilder()
        .withFirstName("Fiona")
        .withLastName("Kunz")
        .withPhone("94823427")
        .withSex("f")
        .withEmploymentType("ft")
        .withAddress("little tokyo")
        .build();
    public static final Person GEORGE = new PersonBuilder()
        .withFirstName("George")
        .withLastName("Best")
        .withPhone("93482442")
        .withSex("m")
        .withEmploymentType("ft")
        .withAddress("4th street")
        .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
        .withFirstName("Hoon")
        .withLastName("Meier")
        .withPhone("84832424")
        .withSex("m")
        .withEmploymentType("ft")
        .withAddress("little india")
        .build();
    public static final Person IDA = new PersonBuilder()
        .withFirstName("Ida")
        .withLastName("Mueller")
        .withPhone("84821331")
        .withSex("f")
        .withEmploymentType("ft")
        .withAddress("chicago ave")
        .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
        .withFirstName(VALID_FIRSTNAME_AMY)
        .withLastName(VALID_LASTNAME_AMY)
        .withPhone(VALID_PHONE_AMY)
        .withSex(VALID_SEX_AMY)
        .withEmploymentType(VALID_EMPLOYMENTTYPE_AMY)
        .withAddress(VALID_ADDRESS_AMY)
        .withBankDetails(VALID_BANKDETAILS_AMY).withWorkedHours(VALID_WORK_HOURS_AMY)
        .withTags(VALID_TAG_COOK).build();
    public static final Person BOB = new PersonBuilder()
        .withFirstName(VALID_FIRSTNAME_BOB)
        .withLastName(VALID_LASTNAME_BOB)
        .withPhone(VALID_PHONE_BOB)
        .withSex(VALID_SEX_BOB)
        .withEmploymentType(VALID_EMPLOYMENTTYPE_BOB)
        .withAddress(VALID_ADDRESS_BOB)
        .withBankDetails(VALID_BANKDETAILS_BOB).withWorkedHours(VALID_WORK_HOURS_BOB)
        .withTags(VALID_TAG_WAITER, VALID_TAG_COOK)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

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
