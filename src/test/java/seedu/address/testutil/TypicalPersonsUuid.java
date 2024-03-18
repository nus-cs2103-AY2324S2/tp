package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects with UUID to be used in tests.
 */
public class TypicalPersonsUuid {

    public static final String ALICE_UUID = "00000000-0000-0000-0000-000000000001";
    public static final String BENSON_UUID = "00000000-0000-0000-0000-000000000002";
    public static final String CARL_UUID = "00000000-0000-0000-0000-000000000003";
    public static final String DANIEL_UUID = "00000000-0000-0000-0000-000000000004";
    public static final String ELLE_UUID = "00000000-0000-0000-0000-000000000005";
    public static final String FIONA_UUID = "00000000-0000-0000-0000-000000000006";
    public static final String GEORGE_UUID = "00000000-0000-0000-0000-000000000007";
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .buildWithUuid(ALICE_UUID);
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .buildWithUuid(BENSON_UUID);
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").buildWithUuid(CARL_UUID);
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").buildWithUuid(DANIEL_UUID);
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").buildWithUuid(ELLE_UUID);
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").buildWithUuid(FIONA_UUID);
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").buildWithUuid(GEORGE_UUID);

    private TypicalPersonsUuid() {} // prevents instantiation

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
