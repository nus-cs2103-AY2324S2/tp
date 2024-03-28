package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Team;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TEAM = "A";
    public static final String DEFAULT_ROLE = "Manager";
    public static final String DEFAULT_UID = "1000";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Team team;
    private Role role;
    private Set<Tag> tags;
    private UniqueId uid;

    /**
     * Creates a {@code EmployeeBuilder} with the default details.
     */
    public EmployeeBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        team = new Team(DEFAULT_TEAM);
        role = new Role(DEFAULT_ROLE);
        tags = new HashSet<>();
        uid = new UniqueId(DEFAULT_UID);
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        phone = employeeToCopy.getPhone();
        email = employeeToCopy.getEmail();
        address = employeeToCopy.getAddress();
        team = employeeToCopy.getTeam();
        role = employeeToCopy.getRole();
        tags = new HashSet<>(employeeToCopy.getTags());
        uid = employeeToCopy.getUid();
    }

    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Employee} that we are building.
     */
    public EmployeeBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Team} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withTeam(String team) {
        this.team = new Team(team);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code UniqueId} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withUid(String uid) {
        this.uid = new UniqueId(uid);
        return this;
    }

    public Employee build() {
        return new Employee(name, phone, email, address, team, role, tags, uid);
    }

}
