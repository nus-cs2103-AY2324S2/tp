package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMISSION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.VersionedAddressBook;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withNote("i love dogs")
            .withTags("friends").withRating("0").build();

    public static final Staff ALICESTAFF = new StaffBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withNote("i love dogs")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withSalary("$50/hr")
            .withEmployment("part-time").withRating("0").build();

    public static final Supplier ALICESUPPLIER = new SupplierBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withNote("i love dogs")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withProduct("pooch food")
            .withPrice("$50/bag").build();

    public static final Maintainer ALICEMAINTAINER = new MaintainerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withNote("i love dogs")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withSkill("train dog")
            .withCommission("$50/hr").withRating("0").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withNote("meet on wednesday")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withRating("0").build();

    public static final Staff BENSONSTAFF = new StaffBuilder().withName("Benson Meier Staff")
            .withAddress("311, Clementi Ave 2, #02-25").withNote("meet on wednesday")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("staff")
            .withEmployment("part-time")
            .withSalary("$50/hr").withRating("0").build();

    public static final Supplier BENSONSUPPLIER = new SupplierBuilder().withName("Benson Meier Supplier")
            .withAddress("311, Clementi Ave 2, #02-25").withNote("meet on wednesday")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withProduct("pooch medicine")
            .withPrice("$50/injection").withRating("0").build();

    public static final Maintainer BENSONMAINTAINER = new MaintainerBuilder().withName("Benson Meier Maintainer")
            .withAddress("311, Clementi Ave 2, #02-25").withNote("meet on wednesday")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withSkill("train dog")
            .withCommission("$50/hr").withRating("0").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withNote("meet on wednesday").withRating("0")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withNote("meet on wednesday")
            .withTags("friends").withRating("0").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withNote("meet on wednesday")
            .withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withRating("0").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withNote("meet on wednesday").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withRating("0").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withNote("meet on wednesday").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withRating("0").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withNote("meet on wednesday")
            .withRating("0").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withNote("meet on wednesday")
            .withRating("0").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withNote(VALID_NOTE_AMY).withRating("0").build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withNote(VALID_NOTE_BOB).withRating("0").build();

    public static final Staff BOBSTAFF = new StaffBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withNote(VALID_NOTE_BOB)
            .withEmployment(VALID_EMPLOYMENT_BOB).withSalary(VALID_SALARY_BOB).withRating("0").build();

    public static final Supplier BOBSUPPLIER = new SupplierBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withNote(VALID_NOTE_BOB)
            .withProduct(VALID_PRODUCT_BOB).withPrice(VALID_PRICE_BOB).withRating("0").build();

    public static final Maintainer BOBMAINTAINER = new MaintainerBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withNote(VALID_NOTE_BOB)
            .withSkill(VALID_SKILL_BOB).withCommission(VALID_COMMISSION_BOB).withRating("0").build();

    // Manually added - used for tests in NoteCommandTest and RateCommandTest
    public static final Staff GEORGIASTAFF = new StaffBuilder().withName("Georgia Staff")
            .withAddress("123, Jurong West Ave 45, #08-131").withEmail("georgia@example.com")
            .withPhone("94355453")
            .withTags("friends")
            .withSalary("$50/hr")
            .withEmployment("part-time").build();

    public static final Supplier GEORGIASUPPLIER = new SupplierBuilder().withName("Georgia Supplier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("georgia@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withProduct("pooch medicine")
            .withPrice("$50/injection").build();

    public static final Maintainer GEORGIAMAINTAINER = new MaintainerBuilder().withName("Georgia Maintainer")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("georgia@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withSkill("train dog")
            .withCommission("$50/hr").build();

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

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalVersionedAddressBook() {
        AddressBook ab = new VersionedAddressBook(getTypicalAddressBook());
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
