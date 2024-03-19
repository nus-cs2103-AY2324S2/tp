package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTaskMasterPro;
import seedu.address.model.TaskMasterPro;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.AssignedTasks;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TaskMasterPro} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        Employee.setUniversalEmployeeId(7);
        return new Employee[] {
            new Employee(new EmployeeId(1), new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new AssignedTasks(""), getTagSet("friends")),
            new Employee(new EmployeeId(2), new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new AssignedTasks(""), getTagSet("colleagues", "friends")),
            new Employee(new EmployeeId(3), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new AssignedTasks(""), getTagSet("neighbours")),
            new Employee(new EmployeeId(4), new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new AssignedTasks(""), getTagSet("family")),
            new Employee(new EmployeeId(5), new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new AssignedTasks(""), getTagSet("classmates")),
            new Employee(new EmployeeId(6), new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new AssignedTasks(""), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTaskMasterPro getSampleTaskMasterPro() {
        TaskMasterPro sampleAb = new TaskMasterPro();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
