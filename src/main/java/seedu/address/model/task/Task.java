package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a Task in the address book.
 */

public class Task {
    private final String taskTitle;

    private final Deadline deadline;

    private Person personInCharge;
    private boolean isDone;


    /**
     * Constructs a {@code Task}
     * @param taskTitle A task name.
     * @param deadline Deadline for the specific task.
     */
    public Task(String taskTitle, Deadline deadline) {
        this.taskTitle = taskTitle;
        this.deadline = deadline;
    }


    /**
     * Constructs a {@code Task} with a specified status.
     * @param taskTitle A task name.
     * @param deadline Deadline for the specific task.
     * @param isDone The status of the task (true if done, false otherwise).
     */
    public Task(String taskTitle, Deadline deadline, Boolean isDone) {
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public String getTaskTitle() {
        return this.taskTitle;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public Person getPersonInCharge() {
        return this.personInCharge;
    }

    /**
     * Add person in charge to a task.
     * @param pic
     */
    public void setPersonInCharge(Person pic) {
        this.personInCharge = pic;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskTitle().equals(getTaskTitle());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return taskTitle.equals(otherTask.taskTitle);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskTitle, deadline);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return new ToStringBuilder(this)
                .add("title", taskTitle)
                .add("deadline", deadline)
                .add("personInCharge", personInCharge.getName())
                .toString();
    }

}
