package seedu.address.model.project;

import java.time.LocalDate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final Name projectName;
    private List<Task> tasks;

    private List<Person> team;
    private List<Milestone> milestones;

    private boolean status;

    private LocalDate deadlineDate;

    /**
     * Every field must be present and not null.
     */
    public Project(String name) {
        requireAllNonNull(name);
        this.projectName = new Name(name);
        this.status = true;
    }

    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline);
    }

    public void addMilestone(String description) {
        milestones.add(new Milestone(description));
    }

    public void closeProject() {
        this.status = false;
    }

    public void openProject() {
        this.status = true;
    }

    public Name getName() {
        return projectName;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addPerson(Person person) {
        team.add(person);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", projectName).toString();
    }

}
