
package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class Employee extends Person {
    private Department department;
    private Skills skills = new Skills(new HashSet<>());
    private JobTitle jobTitle;

    public Employee(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
            Department department, JobTitle jobTitle, Set<String> skills) {
                super(name, phone, email, address, remark, tags);
        this.department = department;
        this.jobTitle = jobTitle;
        this.skills.addSkills(skills);
    }

    public Department getDepartment() {
        return this.department;
    }

    public JobTitle getJobTitle() {
        return this.jobTitle;
    }

    public Skills getSkills() {
        return this.skills;
    }
}