package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in
 * tests.
 */
public class TypicalEmployees {
    public static final Employee ALICE = new EmployeeBuilder().withName("Alice Pauline")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withTeam("A").withRole("Manager")
                        .withTags("friends").withUid("1000").build();
    public static final Employee BENSON = new EmployeeBuilder().withName("Benson Meier")
                        .withAddress("311, Clementi Ave 2, #02-25")
                        .withEmail("johnd@example.com").withPhone("98765432")
                        .withTeam("B").withRole("Engineer")
                        .withTags("owesMoney", "friends").withUid("1001").build();
    public static final Employee CARL = new EmployeeBuilder().withName("Carl Kurz").withPhone("95352563")
                        .withEmail("heinz@example.com").withAddress("wall street").withTeam("C").withRole("IT")
                        .withUid("1002").build();
    public static final Employee DANIEL = new EmployeeBuilder().withName("Daniel Meier").withPhone("87652533")
                        .withEmail("cornelia@example.com").withAddress("10th street")
                        .withTeam("1").withRole("CEO").withUid("1003").withTags("friends").build();
    public static final Employee ELLE = new EmployeeBuilder().withName("Elle Meyer").withPhone("9482224")
                        .withEmail("werner@example.com").withAddress("michegan ave")
                        .withTeam("2").withRole("Cleaner").withUid("1004").build();
    public static final Employee FIONA = new EmployeeBuilder().withName("Fiona Kunz").withPhone("9482427")
                        .withEmail("lydia@example.com").withAddress("little tokyo")
                        .withTeam("3").withRole("Cook").withUid("1005").build();
    public static final Employee GEORGE = new EmployeeBuilder().withName("George Best").withPhone("9482442")
                        .withEmail("anna@example.com").withAddress("4th street")
                        .withTeam("4").withRole("Planner").withUid("1006").build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withName("Hoon Meier").withPhone("8482424")
                        .withEmail("stefan@example.com").withAddress("little india")
                        .withTeam("A").withRole("Creator").withUid("1007").build();
    public static final Employee IDA = new EmployeeBuilder().withName("Ida Mueller").withPhone("8482131")
                        .withEmail("hans@example.com").withAddress("chicago ave")
                        .withTeam("A").withRole("Developer").withUid("1008").build();

    // Manually added - Employee's details found in {@code CommandTestUtil}
    public static final Employee AMY = new EmployeeBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                        .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                        .withTeam("A").withRole("Manager").withTags(VALID_TAG_FRIEND).withUid(VALID_UID_AMY).build();
    public static final Employee BOB = new EmployeeBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                        .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                        .withTeam("B").withRole("Engineer").withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                        .withUid(VALID_UID_BOB)
                        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEmployees() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical employees.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Employee employee : getTypicalEmployees()) {
            ab.addEmployee(employee);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical employees.
     * @return AddressBook
     */
    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
