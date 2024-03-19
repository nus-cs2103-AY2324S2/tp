package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.AssignedTasks;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Employee}.
 */
class JsonAdaptedEmployee {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Employee's %s field is missing!";

    private final int employeeId;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private String tasks;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEmployee} with the given employee details.
     */
    @JsonCreator
    public JsonAdaptedEmployee(@JsonProperty("employeeId") int employeeId, @JsonProperty("name") String name,
                               @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                               @JsonProperty("address") String address,
                               @JsonProperty("tasks") String tasks,
                               @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tasks = tasks;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Employee} into this class for Jackson use.
     */
    public JsonAdaptedEmployee(Employee source) {
        employeeId = source.getEmployeeId().employeeId;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tasks = source.getTasks().getTasks();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted employee object into the model's {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted employee.
     */
    public Employee toModelType() throws IllegalValueException {
        final List<Tag> employeeTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            employeeTags.add(tag.toModelType());
        }

        // Consider to add in checks like the fields below this
        final EmployeeId modelEmployeeId = new EmployeeId(employeeId);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (!AssignedTasks.isValidTask(tasks)) {
            throw new IllegalValueException(AssignedTasks.MESSAGE_CONSTRAINTS);
        }
        final AssignedTasks modelTasks = new AssignedTasks(tasks);

        final Set<Tag> modelTags = new HashSet<>(employeeTags);
        return new Employee(modelEmployeeId, modelName, modelPhone, modelEmail, modelAddress, modelTasks, modelTags);
    }

}
