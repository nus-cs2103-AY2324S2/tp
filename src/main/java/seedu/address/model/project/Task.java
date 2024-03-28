package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.person.Name;

/**
 * Represents a Task of Project
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name taskName;

    private Member member;

    private boolean status;
    private Integer progressCounter = 0;

    private LocalDate deadlineDate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

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
     * @param member the person assigned to the task
     */
    public void assignPerson(Member member) {
        this.member = member;
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
            return "Incomplete";
        } else {
            return "Complete";
        }
    }

    /**
     * Sets the deadline of the task
     * @param deadline the datetime string to be parsed and set as deadline
     */
    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline, formatter);
    }

    /**
     * Get the name of the task
     * @return
     */

    public Name getName() {
        return taskName;
    }

    public String getMemberName() {
        return member == null
            ? ""
            : member.getName().fullName;
    }

    public String getDeadlineString() {
        return deadlineDate == null
            ? ""
            : deadlineDate.format(formatter);
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

    @Override
    public String toString() {
        return taskName.toString();
    }

}
