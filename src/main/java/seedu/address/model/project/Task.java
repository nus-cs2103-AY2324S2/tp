package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a Task of Project
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name taskName;

    private Person person;

    private boolean status;
    private Integer progressCounter = 0;

    private LocalDate deadlineDate;

    private Integer priorityNumber;

    /**
     * Constructs a new task object
     * @param name the task name
     */
    public Task(String name) {
        requireAllNonNull(name);
        this.taskName = new Name(name);
        this.status = true;
    }

    /**
     * Assigns a Person to the task
     * @param person the person assigned to the task
     */
    public void assignPerson(Person person) {
        this.person = person;
    }

    /**
     * Sets the task status as complete
     */
    public void setComplete() {
        this.status = false;
    }

    /**
     * Sets the task status as incomplete
     */
    public void setIncomplete() {
        this.status = true;
        progressCounter = progressCounter + 1;
    }

    /**
     * Gets the status of the task as a string
     * @return the string represeting the status of the task
     */
    public String getStatus() {
        if (status) {
            if (progressCounter > 0) {
                return "In progress";
            } else {
                return "Incomplete";
            }
        } else {
            return "Complete";
        }
    }

    /**
     * Sets the deadline of the task
     * @param deadline the datetime string to be parsed and set as deadline
     */
    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline);
    }

    /**
     * Set the priority of the task
     * @param priorityNumber the priorityNumber of the task
     */
    public void setPriority(Integer priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    /**
     * Get the name of the task
     * @return
     */
    public Name getName() {
        return taskName;
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return taskName.equals(other.taskName);
    }


}
