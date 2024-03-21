package seedu.address.testutil;

import seedu.address.model.person.Department;
import seedu.address.model.person.Employee;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Skills;
import seedu.address.model.util.SampleDataUtil;

/**
 * The EmployeeBuilder class is responsible for building Employee objects for testing purposes.
 * It extends the PersonBuilder class and provides additional methods to set the department,
 * job title, and skills of an employee.
 */
public class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public static final String DEFAULT_DEPARTMENT = "IT";
    public static final String DEFAULT_JOB_TITLE = "Software Engineer";
    public static final String DEFAULT_SKILLS = "Java C++";

    private Department department;
    private JobTitle jobTitle;
    private Skills skills;

    /**
     * Constructs a new EmployeeBuilder with default values.
     */
    public EmployeeBuilder() {
        super();
        department = new Department(DEFAULT_DEPARTMENT);
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
        skills = new Skills(DEFAULT_SKILLS);
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employee) {
        super(employee);
        department = employee.getDepartment();
        jobTitle = employee.getJobTitle();
        skills = employee.getSkills();
    }

    /**
     * Sets the department of the employee that we are building.
     *
     * @param department The department of the employee.
     * @return The EmployeeBuilder with the department set to {@code department}.
     */
    public EmployeeBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    /**
     * Sets the job title of the employee that we are building.
     *
     * @param jobTitle The job title of the employee.
     * @return The EmployeeBuilder with the job title set to {@code jobTitle}.
     */
    public EmployeeBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    /**
     * Sets the skills of the employee that we are building.
     *
     * @param skills The skills of the employee.
     * @return The EmployeeBuilder with the skills set to {@code skills}.
     */
    public EmployeeBuilder withSkills(String... skills) {
        this.skills = SampleDataUtil.getSkills(skills);
        return this;
    }

    /**
     * Builds an Employee object with the specified attributes.
     *
     * @return The Employee object with the specified attributes.
     */
    @Override
    public Employee build() {
        return new Employee(id, name, phone, email, address, remark, tags, department, jobTitle, skills);
    }
}
