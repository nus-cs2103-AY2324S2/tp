package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCTS_LAPTOP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCTS_SMARTPHONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILLS_COMMUNICATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILLS_NEGOTIATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.NetConnect;
import seedu.address.model.person.Client;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    // Using specific Builders for Clients, Employees, and Suppliers
    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends").withProducts("Product1", "Product2")
            .withPreferences("Vegan").build();

    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withTags("owesMoney", "friends").withProducts("Product3")
            .withPreferences("Gluten-Free").build();

    public static final Employee DANIEL = new EmployeeBuilder().withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends").withDepartment("Marketing").withJobTitle("Manager")
            .withSkills("Public Speaking", "Digital Marketing").build();

    public static final Employee ELLE = new EmployeeBuilder().withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
            .withDepartment("IT").withJobTitle("Developer").withSkills("Java", "C++").build();

    public static final Supplier FIONA = new SupplierBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
            .withProducts("Office Supplies", "Furniture").withTermsOfService("Delivery within 2 weeks").build();

    public static final Supplier GEORGE = new SupplierBuilder().withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
            .withProducts("Electronics", "Gadgets").withTermsOfService("Warranty for 1 year").build();

    // Manually added
    public static final Employee HOON = new EmployeeBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withDepartment("Tech Support").withJobTitle("Technician")
            .withSkills("Technical Support", "Customer Service").build();

    public static final Supplier IDA = new SupplierBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withProducts("Computer Hardware", "Printers").withTermsOfService("Returns within 30 days").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withRemark(VALID_REMARK_AMY).withProducts(VALID_PRODUCTS_LAPTOP, VALID_PRODUCTS_SMARTPHONE)
            .withPreferences(VALID_PREFERENCE_AMY).build();

    public static final Employee BOB = new EmployeeBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withRemark(VALID_REMARK_BOB)
            .withDepartment(VALID_DEPARTMENT_BOB).withJobTitle(VALID_JOB_TITLE_BOB)
            .withSkills(VALID_SKILLS_NEGOTIATION, VALID_SKILLS_COMMUNICATION).build();

    private TypicalPersons() {
    } // prevents instantiation

    public static NetConnect getTypicalNetConnect() {
        NetConnect nc = new NetConnect();
        for (Person person : getTypicalPersons()) {
            nc.addPerson(person);
        }
        return nc;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, DANIEL, ELLE, FIONA, GEORGE, AMY, BOB));
    }
}
